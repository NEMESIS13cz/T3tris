package com.redstoner.nemes.t3tris.world;

import com.redstoner.nemes.t3tris.world.blocks.AirBlock;

public class Grid {

	private Block[][][] grid = new Block[16][32][16];
	
	public Grid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				for (int k = 0; k < grid[i][j].length; k++) {
					grid[i][j][k] = new AirBlock(null);
				}
			}
		}
	}
	
	public Block getBlock(int x, int y, int z) {
		if (x > 15 || x < 0 || y > 31 || y < 0 || z > 15 || z < 0) {
			return null;
		}
		
		return grid[x][y][z];
	}
	
	public boolean setBlock(Block block, int x, int y, int z) {
		if (x > 15 || x < 0 || y > 31 || y < 0 || z > 15 || z < 0) {
			return false;
		}
		
		grid[x][y][z] = block;
		return true;
	}
	
	public boolean canSetBlock(int x, int y, int z) {
		if (x > 15 || x < 0 || y > 31 || y < 0 || z > 15 || z < 0) {
			return false;
		}
		return true;
	}
}
