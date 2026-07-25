package net.jukoz.me.block.special;

import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MirkwoodSpiderEggBlock extends Block {
    private static final DirectionProperty FACING;

    public MirkwoodSpiderEggBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.EAST));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection());
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return world.getBlockState(blockPos).isRedstoneConductor(world, blockPos);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if(!entity.minorHorizontalCollision && entity.getType() != ModEntities.MIRKWOOD_SPIDER){
            this.breakEgg(world, pos, state);
            super.stepOn(world, pos, state, entity);
        }
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(world, state, pos, entity, fallDistance);
        breakEgg(world, pos, state);
    }

    private void breakEgg(Level world, BlockPos pos, BlockState state) {
        world.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
        world.levelEvent(2001, pos, Block.getId(state));
        Random random = new Random();
        int amountOfSpider = random.nextInt(1, 4);
        for(int i = 0; i < amountOfSpider; i++)
            SpawnSpider(pos, world);
        world.removeBlock(pos, false);
    }

    private void SpawnSpider(BlockPos pos, Level world){
        MirkwoodSpiderEntity entity = new MirkwoodSpiderEntity(ModEntities.MIRKWOOD_SPIDER, world);
        entity.tickCount = 0;
        entity.setPosRaw(pos.getX(), pos.getY() + 1, pos.getZ());
        world.addFreshEntity(entity);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Block.box(5.0, 0.0, 5.0, 11.0, 7.0, 11.0));
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
    }
}

