package com.redstoner.nemes.t3tris.util;

import org.lwjgl.opengl.GL11;

public class FontCharacter {

	private double u, v, u2, v2;
	private int w, h;
	
	public FontCharacter(double u, double v, double u2, double v2, int width, int height) {
		this.u = u;
		this.v = v;
		this.w = width;
		this.h = height;
		this.u2 = u2;
		this.v2 = v2;
	}
	
	public void draw(double x, double y, double scale) {
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(u, v2);
		GL11.glVertex2d(x, y + h * scale);
		GL11.glTexCoord2d(u2, v2);
		GL11.glVertex2d(x + w * scale, y + h * scale);
		GL11.glTexCoord2d(u2, v);
		GL11.glVertex2d(x + w * scale, y);
		GL11.glTexCoord2d(u, v);
		GL11.glVertex2d(x, y);
		
		GL11.glEnd();
	}
	
	public int getHeight() {
		return h;
	}
	
	public int getWidth() {
		return w;
	}
}
