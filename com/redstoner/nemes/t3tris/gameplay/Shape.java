package com.redstoner.nemes.t3tris.gameplay;

import java.util.Random;

import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;

public class Shape {

	protected Block[][] blocks = new Block[Grid.GRID_WIDTH][Grid.GRID_DEPTH];
	
	public void apply(Grid g, Random rand) {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				g.setBlock(blocks[i][j], i, Grid.GRID_HEIGHT - 1, j);
				g.scheduleUpdate(i, Grid.GRID_HEIGHT - 1, j, g, rand, 0);
			}
		}
	}
}
