package io.github.hydos.lint.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ReturnHomeBlock extends Block {

    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    public ReturnHomeBlock(Settings settings) {
        super(settings);

        this.setDefaultState(this.stateManager.getDefaultState().with(ACTIVATED, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(ACTIVATED)) {
            if (world instanceof ServerWorld) {
                world.getEntitiesByClass(LivingEntity.class, new Box(pos).expand(5.0), le -> true).forEach(le -> {
//					TeleportUtils.teleport(le, DimensionType.OVERWORLD, le.getBlockPos());
                });
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}