package com.redstoner.nemes.t3tris.world;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.util.ScheduledUpdate;
import com.redstoner.nemes.t3tris.world.blocks.AirBlock;
import com.redstoner.nemes.t3tris.world.blocks.NormalBlock;

public class Grid {

	public static final int GRID_WIDTH = 16;
	public static final int GRID_HEIGHT = 32;
	public static final int GRID_DEPTH = 16;
	
	private Block[][][] grid = new Block[GRID_WIDTH][GRID_HEIGHT][GRID_DEPTH];
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
		if (x > GRID_WIDTH - 1 || x < 0 || y > GRID_HEIGHT - 1 || y < 0 || z > GRID_DEPTH - 1 || z < 0) {
			return null;
		}
		
		return grid[x][y][z];
	}
	
	public boolean setBlock(Block block, int x, int y, int z) {
		if (x > GRID_WIDTH - 1 || x < 0 || y > GRID_HEIGHT - 1 || y < 0 || z > GRID_DEPTH - 1 || z < 0) {
			return false;
		}
		
		grid[x][y][z] = block == null ? new AirBlock(null) : block;
		return true;
	}
	
	public boolean canSetBlock(int x, int y, int z) {
		if (x > GRID_WIDTH - 1 || x < 0 || y > GRID_HEIGHT - 1 || y < 0 || z > GRID_DEPTH - 1 || z < 0) {
			return false;
		}
		return true;
	}
	
	public void scheduleUpdate(int x, int y, int z, Grid g, Random rand, int ticks) {
		ScheduledUpdate u = new ScheduledUpdate(ticks, x, y, z, g, rand);
		scheduler.add(u);
		g.getBlock(x, y, z).appendUpdate(u);
	}
	
	public void updateScheduler() {
		for (int i = 0; i < scheduler.size(); i++) {
			ScheduledUpdate u = scheduler.get(i);
			if (u.updateTime() <= 0) {
				u.execute();
				u.getAssociatedBlock().removeUpdate(u);
				scheduler.remove(i);
				i--;
			}
		}
	}
	
	public static Block generateBlock(Random rand) {
		return new NormalBlock(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	}
	
	public boolean isLayerEmpty(int y) {
		if (y < 0 || y > Grid.GRID_HEIGHT - 1) {
			return false;
		}
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				if (!(grid[x][y][z] instanceof AirBlock)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isLayerFull(int y) {
		if (y < 0 || y > Grid.GRID_HEIGHT - 1) {
			return false;
		}
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				if (grid[x][y][z] instanceof AirBlock) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void deleteLayer(int y) {
		if (y < 0 || y > Grid.GRID_HEIGHT - 1) {
			return;
		}
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				setBlock(null, x, y, z);
			}
		}
	}
	
	public void updateAll(Grid g, Random rand, int time) {
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				for (int y = 0; y < Grid.GRID_HEIGHT; y++) {
					scheduleUpdate(x, y, z, g, rand, time);
				}
			}
		}
	}
}
