package com.redstoner.nemes.t3tris.world;

import org.lwjgl.util.Color;

public class Block {

	private Color colour = null;
	
	public Block(Color c) {
		colour = c;
	}
	
	public Color getColour() {
		return colour;
	}
}
