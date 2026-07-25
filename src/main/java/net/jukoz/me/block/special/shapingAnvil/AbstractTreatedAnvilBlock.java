package net.jukoz.me.block.special.shapingAnvil;

import net.jukoz.me.compat.neoforge.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.jukoz.me.item.items.SmithingHammerItem;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTreatedAnvilBlock extends BaseEntityBlock implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public AbstractTreatedAnvilBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(((this.stateDefinition.any()).setValue(FACING, Direction.NORTH)));
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TreatedAnvilBlockEntity treatedAnvilBlockEntity) {
                Containers.dropContents(world, pos, treatedAnvilBlockEntity);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {

        return super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        Container blockEntity = (Container) world.getBlockEntity(pos);

        if (!world.isClientSide) {
            if (player.getMainHandItem().isEmpty() && !blockEntity.getItem(0).isEmpty()) {
                player.setItemSlot(EquipmentSlot.MAINHAND, blockEntity.getItem(0));
                blockEntity.removeItemNoUpdate(0);
            } else {
                MenuProvider screenHandlerFactory = state.getMenuProvider(world, pos);
                if (screenHandlerFactory != null) {
                    if (screenHandlerFactory instanceof ExtendedScreenHandlerFactory<?> extendedScreenHandlerFactory && player instanceof ServerPlayer serverPlayer) {
                        ExtendedScreenHandlerFactory.open(serverPlayer, castOpeningFactory(extendedScreenHandlerFactory), BlockPos.STREAM_CODEC.cast());
                    } else {
                        player.openMenu(screenHandlerFactory);
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("unchecked")
    private static ExtendedScreenHandlerFactory<BlockPos> castOpeningFactory(ExtendedScreenHandlerFactory<?> factory) {
        return (ExtendedScreenHandlerFactory<BlockPos>) factory;
    }

    @Override
    protected void attack(BlockState state, Level world, BlockPos pos, Player player) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClientSide) {
            if (stack.getItem() instanceof SmithingHammerItem hammer && player.getAttackStrengthScale(0.5f) > 0.9f) {
                player.awardStat(Stats.ITEM_USED.get(hammer));
                stack.use(world, player, player.getUsedItemHand());
                player.getItemInHand(player.getUsedItemHand()).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                if (blockEntity instanceof TreatedAnvilBlockEntity shapingAnvilBlockEntity) {
                    shapingAnvilBlockEntity.bonk(shapingAnvilBlockEntity);
                }
            }
        }
    }
}
