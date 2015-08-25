package com.redstoner.nemes.t3tris.menu;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.gfx.FontMap;
import com.redstoner.nemes.t3tris.util.Controls;
import com.redstoner.nemes.t3tris.util.MouseHandler;
import com.redstoner.nemes.t3tris.util.Options;

public class FramerateSlider extends Button {

	private float sliderX;
	private float sliderW = 0.01f;
	private boolean isCatched = false;
	
	public FramerateSlider(float x, float y, float x2, float y2, IMenu m) {
		super(x, y, x2, y2, m);
		float w = (this.x2 * Display.getWidth() - this.x * Display.getWidth()) / (float)Display.getWidth() - sliderW;
		float posX = ((Options.framerateLimit == Integer.MAX_VALUE ? 205 : Options.framerateLimit) - 5) / 200.0f;
		sliderX = posX * w;
	}

	public void render(int w, int h) {
		float x_ = this.x * w;
		float x2_ = x2 * w;
		float y_ = this.y * h;
		float y2_ = y2 * h;
		
		Render.disableTextures();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0.5f, 0.5f, 0.5f, 0.6f);
		GL11.glVertex2f(x_, y2_);
		GL11.glVertex2f(x2_, y2_);
		GL11.glVertex2f(x2_, y_);
		GL11.glVertex2f(x_, y_);
		
		GL11.glColor4f(0.7f, 0.7f, 0.7f, 0.6f);
		GL11.glVertex2f(x_ + sliderX * w, y2_);
		GL11.glVertex2f(x_ + (sliderX + sliderW) * w, y2_);
		GL11.glVertex2f(x_ + (sliderX + sliderW) * w, y_);
		GL11.glVertex2f(x_ + sliderX * w, y_);
		GL11.glEnd();
		Render.enableTextures();

		double button_h = y2_ - y_;
		double scale = button_h / 10;
		FontMap.drawString(x_ + (x2_ - x_) / 2, y_ + (button_h - scale * 8) / 2, scale, "Framerate", 0xF0F0F0, true);
	}
	
	public void update(int x, int y, int w, int h) {
		int x_ = (int) (this.x * w);
		int x2_ = (int) (x2 * w);
		int y_ = (int) (this.y * h);
		int y2_ = (int) (y2 * h);

		if (x > x_ && x < x2_ && y > y_ && y < y2_) {
			if (MouseHandler.pressed(Controls.getLeftMouseButton())) {
				isCatched = true;
			}
		}
		if (MouseHandler.released(Controls.getLeftMouseButton())) {
			isCatched = false;
		}
		
		if (isCatched) {
			sliderX = MouseHandler.getX() / (float)w - this.x - sliderW / 2;
			sliderX = MathHelper.clamp_float(sliderX, 0, ((x2_ - x_) / (float)w) - sliderW);
			float p = MathHelper.percentage(sliderX, (x2_ - x_) / (float)w - sliderW);
			int fps = (int)(p * 2.0f) + 5;
			Options.framerateLimit = fps == 205 ? Integer.MAX_VALUE : fps;
		}
		
	}
}
