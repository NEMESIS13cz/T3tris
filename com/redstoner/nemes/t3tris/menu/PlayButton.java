package com.redstoner.nemes.t3tris.menu;

import org.lwjgl.opengl.GL11;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.T3tris;
import com.redstoner.nemes.t3tris.gfx.FontMap;
import com.redstoner.nemes.t3tris.util.Controls;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.MouseHandler;

public class PlayButton extends Button {

	
	public PlayButton(float x, float y, float x2, float y2, IMenu m) {
		super(x, y, x2, y2, m);
	}

	public void render(int w, int h) {
		float x_ = this.x * w;
		float x2_ = x2 * w;
		float y_ = this.y * h;
		float y2_ = y2 * h;
		
		Render.disableTextures();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.4f);
		GL11.glVertex2f(x_, y2_);
		GL11.glVertex2f(x2_, y2_);
		GL11.glVertex2f(x2_, y_);
		GL11.glVertex2f(x_, y_);
		GL11.glEnd();
		Render.enableTextures();

		double button_h = y2_ - y_;
		double scale = button_h / 10;
		FontMap.drawString(x_ + (x2_ - x_) / 2, y_ + (button_h - scale * 8) / 2, scale, "Play", 0xFFFF00, true);
	}
	
	public void update(int x, int y, int w, int h) {
		int x_ = (int) (this.x * w);
		int x2_ = (int) (x2 * w);
		int y_ = (int) (this.y * h);
		int y2_ = (int) (y2 * h);
		
		if (x > x_ && x < x2_ && y > y_ && y < y2_) {
			if (MouseHandler.released(Controls.getLeftMouseButton())) {
				T3tris.getInstance().setCurrentGameState(GameState.PLAYING);
			}
		}
	}
}
