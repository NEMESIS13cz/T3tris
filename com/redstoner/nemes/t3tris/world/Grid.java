package com.redstoner.nemes.t3tris.world;

public class Grid {

	private Block[][][] grid = new Block[16][32][16];
	
	
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
