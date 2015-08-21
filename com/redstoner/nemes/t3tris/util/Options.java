package com.redstoner.nemes.t3tris.util;

public class Options {

	// non-configurable options
	public static final float zNear = 0.002f;
	public static final float zFar = 200.0f;
	public static final int tickrateLimit = 20;
	public static final String name = "T3tris";
	
	// startup-arg-changeable options
	public static int startWidth = 600;
	public static int startHeight = 400;
	
	// configurable options
	public static int framerateLimit = Integer.MAX_VALUE;
	public static float FOV = 70.0f;
	
	public void loadOptions() {
		
	}
	
	public void saveOptions() {
		
	}
}
