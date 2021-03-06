package com.redstoner.nemes.t3tris.util;

public class Options {

	public static final int DEFAULT_FRAMERATE_LIMIT = 60;
	
	// non-configurable options
	public static final float zNear = 0.002f;
	public static final float zFar = 200.0f;
	public static final int tickrateLimit = 20;
	public static final float FOV = 70.0f;
	public static final String name = "T3tris";
	public static final int startWidth = 800;
	public static final int startHeight = 450;
	
	// configurable options
	public static int framerateLimit = DEFAULT_FRAMERATE_LIMIT;
	
	public static void load() throws DataException {
		DataFile defaultFile = new DataFile();
		defaultFile.addInteger("framerate_limit", framerateLimit);
		DataFile file = new DataFile(Constants.FILE_PATH, "options.dat", defaultFile);
		framerateLimit = file.getInteger("framerate_limit");
	}
	
	public static void save() {
		DataFile file = new DataFile();
		file.addInteger("framerate_limit", framerateLimit);
		file.saveFile(Constants.FILE_PATH, "options.dat");
	}
}
