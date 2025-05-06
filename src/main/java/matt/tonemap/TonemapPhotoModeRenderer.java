package matt.tonemap;

import java.io.File;

import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL20;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenPhotoMode;
import net.minecraft.client.render.OpenGLHelper;
import net.minecraft.client.render.shader.PhotoModeRenderer;
import net.minecraft.client.render.shader.Shader;
import net.minecraft.client.render.shader.ShaderProvider;
import net.minecraft.client.render.shader.ShaderProviderExternal;
import net.minecraft.client.render.shader.Shaders;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public class TonemapPhotoModeRenderer extends PhotoModeRenderer {
    
	private static final Logger LOGGER = LogUtils.getLogger();

    public TonemapPhotoModeRenderer(Minecraft minecraft, ScreenPhotoMode screen) {
        super(minecraft, screen);
        if (!Shaders.enableShaders) {
            throw new RuntimeException("Shaders disabled!");
        }
    }

    public ShaderProvider getNewShader() {
		String override = (String)this.mc.gameSettings.shaderOverride.value;
		return (ShaderProvider)(override != null && !override.isEmpty() ? new ShaderProviderExternal(new File("shaders/")) : this.internal);
	}

    @Override
	public void reload() {
		LOGGER.info("Reloading Shaders...");
		GL20.glUseProgram(0);
		ARBFramebufferObject.glBindFramebuffer(36160, 0);
		OpenGLHelper.checkError("pre shader reload");
		this.postShader.delete();
		this.finalShader.delete();
		this.cloudsShader.delete();
		ShaderProvider shaderProvider = this.getShader();
		ShaderProvider newShaderProvider = this.getNewShader();
		if (shaderProvider != null) {
			this.finalShader.compile(newShaderProvider, "Tonemap");
			this.postShader.compile(shaderProvider, "post");
			this.cloudsShader.compile(shaderProvider, "clouds");
		}

		OpenGLHelper.checkError("shader reload");
	}
}
