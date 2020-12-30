/*
 * Lint
 * Copyright (C) 2020 hYdos, Valoeghese, ramidzkh
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.hydos.lint.client.render.block;

import com.mojang.blaze3d.platform.GlStateManager;
import me.hydos.lint.Lint;
import me.hydos.lint.block.entity.SmelteryBlockEntity;
import me.hydos.lint.client.render.fluid.LintFluidRenderer;
import me.hydos.lint.fluid.LintFluids;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.awt.*;

public class SmelteryBlockEntityRenderer extends BlockEntityRenderer<SmelteryBlockEntity> {

	private static final Rectangle BLOCK_BOUNDS = new Rectangle(1, 1);

	public SmelteryBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(SmelteryBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (entity.center != null && entity.isLit() && entity.fluidData.size() != 0) {
			matrices.translate(0, 0, 1);
			renderFluid(matrices, LintFluids.MOLTEN_FLUID_MAP.get(Lint.id("gold")).getStill(), BLOCK_BOUNDS, light, 2);
			renderFluid(matrices, LintFluids.MOLTEN_FLUID_MAP.get(Lint.id("gold")).getStill(), BLOCK_BOUNDS, light, (float) (6.187*(10^37)));
		}
	}

	public void renderFluid(MatrixStack matrices, Fluid fluid, Rectangle bounds, int light, float level) {
		GlStateManager.enableDepthTest();
		LintFluidRenderer.CachedFluid renderingData = LintFluidRenderer.from(fluid);
		if (renderingData != null) {
			Sprite sprite = renderingData.getSprite();
			int color = renderingData.getColor();
			int a = 255;
			int r = color >> 16 & 255;
			int g = color >> 8 & 255;
			int b = color & 255;
			MinecraftClient.getInstance().getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bb = tessellator.getBuffer();
			Matrix4f matrix = matrices.peek().getModel();
			bb.begin(GL20.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT);
			bb.vertex(matrix, (float) bounds.getMaxX(), level, bounds.y)
					.color(r, g, b, a)
					.texture(sprite.getMaxU(), sprite.getMinV())
					.light(light)
					.next();
			bb.vertex(matrix, (float) bounds.x, level, bounds.y)
					.color(r, g, b, a)
					.texture(sprite.getMinU(), sprite.getMinV())
					.light(light)
					.next();
			bb.vertex(matrix, (float) bounds.x, level, (float) bounds.getMaxY())
					.color(r, g, b, a)
					.texture(sprite.getMinU(), sprite.getMaxV())
					.light(light)
					.next();
			bb.vertex(matrix, (float) bounds.getMaxX(), level, (float) bounds.getMaxY())
					.color(r, g, b, a)
					.texture(sprite.getMaxU(), sprite.getMaxV())
					.light(light)
					.next();
			tessellator.draw();
			GlStateManager.disableDepthTest();
		}
	}
}
