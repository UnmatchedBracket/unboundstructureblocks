package net.isageek.bracket.unboundstructureblocks.client.mixin;

import net.minecraft.client.renderer.blockentity.BlockEntityWithBoundingBoxRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockEntityWithBoundingBoxRenderer.class)
public class BlockEntityWithBoundingBoxRendererMixin {
	@ModifyConstant(method = "getViewDistance", constant = @Constant())
	private int getViewDistance(int value) {
		return Integer.MAX_VALUE;
	}
}