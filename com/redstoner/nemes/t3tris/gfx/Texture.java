package com.redstoner.nemes.t3tris.gfx;

public class Texture {

	private int ID, width, height;
	
	public Texture(int id, int w, int h) {
		ID = id;
		width = w;
		height = h;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
