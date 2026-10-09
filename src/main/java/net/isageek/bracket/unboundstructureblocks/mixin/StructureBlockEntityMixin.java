package net.isageek.bracket.unboundstructureblocks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.world.level.block.entity.StructureBlockEntity;

@Mixin(StructureBlockEntity.class)
public class StructureBlockEntityMixin {
	@ModifyConstant(method = "loadAdditional", constant = @Constant(intValue = 48))
	private int positive(int value) {
		return Integer.MAX_VALUE;
	}

	@ModifyConstant(method = "loadAdditional", constant = @Constant(intValue = -48))
	private int negative(int value) {
		return Integer.MIN_VALUE;
	}
}