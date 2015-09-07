package com.redstoner.nemes.t3tris.util;

import org.lwjgl.opengl.GL11;

public class GLColor {

	private float r, g, b, a;
	
	public GLColor(float red, float green, float blue, float alpha) {
		r = red;
		g = green;
		b = blue;
		a = alpha;
	}
	
	public float getRed() {
		return r;
	}
	
	public float getGreen() {
		return g;
	}
	
	public float getBlue() {
		return b;
	}
	
	public float getAlpha() {
		return a;
	}
	
	public void bind() {
		GL11.glColor4f(r, g, b, a);
	}
}
