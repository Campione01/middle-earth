#!/usr/bin/env python3
"""Audit structure NBT files for custom blocks using vanilla block entities."""

from __future__ import annotations

import argparse
import gzip
import struct
import sys
from collections import Counter, defaultdict
from pathlib import Path
from typing import Any


TAG_END = 0
TAG_BYTE = 1
TAG_SHORT = 2
TAG_INT = 3
TAG_LONG = 4
TAG_FLOAT = 5
TAG_DOUBLE = 6
TAG_BYTE_ARRAY = 7
TAG_STRING = 8
TAG_LIST = 9
TAG_COMPOUND = 10
TAG_INT_ARRAY = 11
TAG_LONG_ARRAY = 12

BARREL_BLOCKS = {"me:thin_barrel", "me:small_crate"}
ALLOWED_CUSTOM_VANILLA_BLOCK_ENTITY_PAIRS = {
    ("me:thin_barrel", "minecraft:barrel"),
    ("me:small_crate", "minecraft:barrel"),
}


class NbtError(ValueError):
    pass


class NbtReader:
    def __init__(self, data: bytes, source: Path) -> None:
        self.data = memoryview(data)
        self.offset = 0
        self.source = source

    def _require(self, length: int) -> memoryview:
        end = self.offset + length
        if end > len(self.data):
            raise NbtError(f"{self.source}: truncated NBT at byte {self.offset}")
        chunk = self.data[self.offset:end]
        self.offset = end
        return chunk

    def read_byte(self) -> int:
        return struct.unpack(">b", self._require(1))[0]

    def read_unsigned_byte(self) -> int:
        return struct.unpack(">B", self._require(1))[0]

    def read_short(self) -> int:
        return struct.unpack(">h", self._require(2))[0]

    def read_unsigned_short(self) -> int:
        return struct.unpack(">H", self._require(2))[0]

    def read_int(self) -> int:
        return struct.unpack(">i", self._require(4))[0]

    def read_long(self) -> int:
        return struct.unpack(">q", self._require(8))[0]

    def read_float(self) -> float:
        return struct.unpack(">f", self._require(4))[0]

    def read_double(self) -> float:
        return struct.unpack(">d", self._require(8))[0]

    def read_string(self) -> str:
        length = self.read_unsigned_short()
        raw = self._require(length)
        return raw.tobytes().decode("utf-8")

    def read_root(self) -> dict[str, Any]:
        tag_id = self.read_unsigned_byte()
        if tag_id != TAG_COMPOUND:
            raise NbtError(f"{self.source}: root tag is {tag_id}, expected TAG_Compound")
        self.read_string()
        value = self.read_payload(tag_id)
        if not isinstance(value, dict):
            raise NbtError(f"{self.source}: root payload is not a compound")
        return value

    def read_payload(self, tag_id: int) -> Any:
        if tag_id == TAG_BYTE:
            return self.read_byte()
        if tag_id == TAG_SHORT:
            return self.read_short()
        if tag_id == TAG_INT:
            return self.read_int()
        if tag_id == TAG_LONG:
            return self.read_long()
        if tag_id == TAG_FLOAT:
            return self.read_float()
        if tag_id == TAG_DOUBLE:
            return self.read_double()
        if tag_id == TAG_BYTE_ARRAY:
            length = self.read_int()
            return self._require(length).tobytes()
        if tag_id == TAG_STRING:
            return self.read_string()
        if tag_id == TAG_LIST:
            element_type = self.read_unsigned_byte()
            length = self.read_int()
            if length < 0:
                raise NbtError(f"{self.source}: negative list length {length}")
            return [self.read_payload(element_type) for _ in range(length)]
        if tag_id == TAG_COMPOUND:
            result: dict[str, Any] = {}
            while True:
                child_type = self.read_unsigned_byte()
                if child_type == TAG_END:
                    return result
                name = self.read_string()
                result[name] = self.read_payload(child_type)
        if tag_id == TAG_INT_ARRAY:
            length = self.read_int()
            if length < 0:
                raise NbtError(f"{self.source}: negative int array length {length}")
            return [self.read_int() for _ in range(length)]
        if tag_id == TAG_LONG_ARRAY:
            length = self.read_int()
            if length < 0:
                raise NbtError(f"{self.source}: negative long array length {length}")
            return [self.read_long() for _ in range(length)]
        raise NbtError(f"{self.source}: unsupported NBT tag {tag_id}")


def read_nbt(path: Path) -> dict[str, Any]:
    raw = path.read_bytes()
    if raw.startswith(b"\x1f\x8b"):
        raw = gzip.decompress(raw)
    reader = NbtReader(raw, path)
    return reader.read_root()


def structure_files(paths: list[Path]) -> list[Path]:
    files: list[Path] = []
    for path in paths:
        if path.is_file() and path.suffix == ".nbt":
            files.append(path)
        elif path.is_dir():
            files.extend(sorted(path.glob("*.nbt")))
    return sorted(dict.fromkeys(files))


def block_name_from_state(palette: list[Any], state: Any) -> str | None:
    if not isinstance(state, int) or state < 0 or state >= len(palette):
        return None
    entry = palette[state]
    if not isinstance(entry, dict):
        return None
    name = entry.get("Name")
    return name if isinstance(name, str) else None


def audit_file(path: Path) -> tuple[set[str], Counter[tuple[str, str]], list[str]]:
    root = read_nbt(path)
    palette = root.get("palette")
    if palette is None and isinstance(root.get("palettes"), list) and root["palettes"]:
        palette = root["palettes"][0]
    blocks = root.get("blocks")
    if not isinstance(palette, list) or not isinstance(blocks, list):
        raise NbtError(f"{path}: missing palette or blocks list")

    used_barrel_blocks: set[str] = set()
    block_entity_pairs: Counter[tuple[str, str]] = Counter()
    errors: list[str] = []

    for block in blocks:
        if not isinstance(block, dict):
            continue
        block_name = block_name_from_state(palette, block.get("state"))
        if block_name in BARREL_BLOCKS:
            used_barrel_blocks.add(block_name)

        nbt = block.get("nbt")
        if not isinstance(nbt, dict):
            continue
        block_entity_id = nbt.get("id")
        if not isinstance(block_entity_id, str) or block_name is None:
            continue

        pair = (block_name, block_entity_id)
        block_entity_pairs[pair] += 1
        if (
            block_name.startswith("me:")
            and block_entity_id.startswith("minecraft:")
            and pair not in ALLOWED_CUSTOM_VANILLA_BLOCK_ENTITY_PAIRS
        ):
            errors.append(f"{path.name}: unapproved pair {block_name} -> {block_entity_id}")

    return used_barrel_blocks, block_entity_pairs, errors


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "paths",
        nargs="*",
        type=Path,
        default=[Path("src/main/resources/data/me/structure")],
        help="NBT file or directory paths to audit",
    )
    args = parser.parse_args()

    files = structure_files(args.paths)
    if not files:
        print("ERROR: no .nbt structure files found", file=sys.stderr)
        return 2

    structures_by_barrel_block: dict[str, set[str]] = defaultdict(set)
    all_pairs: Counter[tuple[str, str]] = Counter()
    errors: list[str] = []

    for path in files:
        try:
            used_barrel_blocks, pairs, file_errors = audit_file(path)
        except Exception as exc:
            errors.append(f"{path}: {exc}")
            continue
        for block_name in used_barrel_blocks:
            structures_by_barrel_block[block_name].add(path.name)
        all_pairs.update(pairs)
        errors.extend(file_errors)

    custom_barrel_structures = set().union(*structures_by_barrel_block.values()) if structures_by_barrel_block else set()

    print(f"structures_total={len(files)}")
    print(f"custom_barrel_structures={len(custom_barrel_structures)}")
    for block_name in sorted(BARREL_BLOCKS):
        print(f"{block_name}_structures={len(structures_by_barrel_block.get(block_name, set()))}")
    print("allowed_custom_vanilla_pairs=" + ",".join(f"{block}->{entity}" for block, entity in sorted(ALLOWED_CUSTOM_VANILLA_BLOCK_ENTITY_PAIRS)))

    custom_vanilla_pairs = {
        pair: count
        for pair, count in all_pairs.items()
        if pair[0].startswith("me:") and pair[1].startswith("minecraft:")
    }
    if custom_vanilla_pairs:
        for (block_name, block_entity_id), count in sorted(custom_vanilla_pairs.items()):
            print(f"custom_vanilla_pair={block_name}->{block_entity_id} count={count}")
    else:
        print("custom_vanilla_pair_count=0")

    if errors:
        print("errors=" + str(len(errors)), file=sys.stderr)
        for error in errors[:20]:
            print("ERROR: " + error, file=sys.stderr)
        if len(errors) > 20:
            print(f"ERROR: truncated {len(errors) - 20} additional errors", file=sys.stderr)
        return 1

    print("errors=0")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
