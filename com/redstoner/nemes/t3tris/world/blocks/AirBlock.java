package com.redstoner.nemes.t3tris.world.blocks;

import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;

public class AirBlock extends Block {

	public AirBlock(Color c) {
		super(c);
	}
	
	public void render(Grid g, Render r, int x, int y, int z) {
		
	}
	
	public boolean isTransparent() {
		return true;
	}
}
