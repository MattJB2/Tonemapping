package matt.tonemap.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenPhotoMode;
import net.minecraft.client.render.shader.PhotoModeRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.util.helper.ScreenShot;

import matt.tonemap.TonemapPhotoModeRenderer;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(ScreenPhotoMode.class)
public abstract class PhotoModeMixin {
	@Redirect(
		method = "<init>",
		at = @At(
			value = "NEW",
			target = "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/ScreenPhotoMode;)Lnet/minecraft/client/render/shader/PhotoModeRenderer;"
		)
	)
    public PhotoModeRenderer ScreenPhotoModeHijack(Minecraft mc, ScreenPhotoMode screen) {
		System.out.println("Color Photo Mode!!!!");
		return new TonemapPhotoModeRenderer(mc, screen);
   }
}

