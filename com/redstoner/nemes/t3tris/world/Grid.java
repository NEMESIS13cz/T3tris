package com.redstoner.nemes.t3tris.world;

import java.util.ArrayList;
import java.util.Random;

import com.redstoner.nemes.t3tris.util.ScheduledUpdate;
import com.redstoner.nemes.t3tris.world.blocks.AirBlock;

public class Grid {

	private Block[][][] grid = new Block[16][32][16];
	private ArrayList<ScheduledUpdate> scheduler = new ArrayList<ScheduledUpdate>();
	
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
		
		grid[x][y][z] = block == null ? new AirBlock(null) : block;
		return true;
	}
	
	public boolean canSetBlock(int x, int y, int z) {
		if (x > 15 || x < 0 || y > 31 || y < 0 || z > 15 || z < 0) {
			return false;
		}
		return true;
	}
	
	public void scheduleUpdate(int x, int y, int z, Grid g, Random rand, int ticks) {
		scheduler.add(new ScheduledUpdate(ticks, x, y, z, g, rand));
	}
	
	public void updateScheduler() {
		for (int i = 0; i < scheduler.size(); i++) {
			if (scheduler.get(i).updateTime() <= 0) {
				scheduler.get(i).execute();
				scheduler.remove(i);
				i--;
			}
		}
	}
}
