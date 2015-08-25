package com.redstoner.nemes.t3tris.menu;

public class MathHelper {

	public static float clamp_float(float f, float bottom, float top) {
		if (f < bottom) return bottom;
		if (f > top) return top;
		return f;
	}
	
	public static float percentage(float f, float max) {
		return f / max * 100;
	}
	
}
