package com.redstoner.nemes.t3tris.util;

import java.util.Random;

import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;

public class ScheduledUpdate {

	private int time, x, y, z;
	private Random r;
	private Grid g;
	
	public ScheduledUpdate(int time, int x, int y, int z, Grid grid, Random rand) {
		this.time = time;
		this.x = x;
		this.y = y;
		this.z = z;
		this.g = grid;
		this.r = rand;
	}
	
	public int updateTime() {
		int r = time;
		time--;
		return r;
	}
	
	public void execute() {
		g.getBlock(x, y, z).update(g, x, y, z, r);
	}
	
	public Block getAssociatedBlock() {
		return g.getBlock(x, y, z);
	}
	
	public void moveUpdate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
