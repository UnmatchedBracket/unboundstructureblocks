package net.isageek.bracket.unboundstructureblocks.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import net.minecraft.util.Mth;

@Debug(export = true)
@Mixin(ServerboundSetStructureBlockPacket.class)
public class ServerboundSetStructureBlockPacketMixin {
	@ModifyConstant(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", constant = @Constant(intValue = 48))
	private int positive(int value) {
		return Integer.MAX_VALUE;
	}

	@ModifyConstant(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", constant = @Constant(intValue = -48))
	private int negative(int value) {
		return Integer.MIN_VALUE;
	}

	@Redirect(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"))
	private int correctBytes(int value, int min, int max) {
		if (min == 0 && value <= 0) { // it's the size and we have a byte rolled over. reasonable to assume that a size of 0 is useless and allow 256 to be used
			value += 256;
		} 
		return Mth.clamp(value, min, max);
	}
}
