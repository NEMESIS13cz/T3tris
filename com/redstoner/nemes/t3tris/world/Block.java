package com.redstoner.nemes.t3tris.world;

import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.Render;

public class Block {

	private Color colour = null;
	
	public Block(Color c) {
		colour = c;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public void update(Grid g, int x, int y, int z) {
		
	}
	
	public void render(Grid g, Render r, int x, int y, int z) {
		
	}
	
	public boolean isTransparent() {
		return false;
	}
}
