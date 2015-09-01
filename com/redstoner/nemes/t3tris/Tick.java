package com.redstoner.nemes.t3tris;

import java.util.Random;

import org.lwjgl.opengl.Display;

import com.redstoner.nemes.t3tris.gameplay.ShapeGenerator;
import com.redstoner.nemes.t3tris.util.Controls;
import com.redstoner.nemes.t3tris.util.DataException;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.KeyHandler;
import com.redstoner.nemes.t3tris.util.MouseHandler;
import com.redstoner.nemes.t3tris.util.Options;
import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;
import com.redstoner.nemes.t3tris.world.blocks.AirBlock;

public class Tick extends Thread {

	private T3tris instance;
	private int ticks;
	private Grid grid;
	private Random rand = new Random();
	
	public Tick(T3tris inst) {
		instance = inst;
	}
	
	public void run() {
		setName("Tick Thread");
		long next_tick = System.currentTimeMillis();
		long tick_time = 1000 / Options.tickrateLimit;
		GameState state;
		
		try {
			Options.load();
			Controls.load();
		} catch (DataException e1) {
			e1.printStackTrace();
		}
		
		grid = new Grid();
		
		try {
			while (instance.getCurrentGameState() == GameState.STARTING) {
				sleep(1);
			}
			while ((state = instance.getCurrentGameState()) != GameState.CLOSING) {
				if (next_tick < System.currentTimeMillis()) {
					long tick_start_time = System.currentTimeMillis();
					next_tick += tick_time;
					
					KeyHandler.update();
					MouseHandler.update();
					if (state == GameState.MENU) {
						tickMenu();
					}else{
						tickGame();
					}
					ticks++;
					long temp = System.currentTimeMillis() - tick_start_time;
					if (temp < tick_time) {
						sleep(tick_time - temp);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		saveData();
	}
	
	protected synchronized int getTicks() {
		int tps = ticks;
		ticks = 0;
		return tps;
	}
	
	public void saveData() {
		Options.save();
		Controls.save();
	}
	
	public void tickGame() {
		grid.updateScheduler();
		if (KeyHandler.pressed(Controls.getEsc())) {
			instance.setCurrentGameState(GameState.MENU);
		}
		if (KeyHandler.pressed(Controls.getW()) || KeyHandler.pressed(Controls.getUp())) {
			shiftBlocksNegZ();
		}
		if (KeyHandler.pressed(Controls.getS()) || KeyHandler.pressed(Controls.getDown())) {
			shiftBlocksPosZ();
		}
		if (KeyHandler.pressed(Controls.getA()) || KeyHandler.pressed(Controls.getLeft())) {
			shiftBlocksNegX();
		}
		if (KeyHandler.pressed(Controls.getD()) || KeyHandler.pressed(Controls.getRight())) {
			shiftBlocksPosX();
		}
		if (KeyHandler.pressed(Controls.getSpace()) && grid.isLayerEmpty(Grid.GRID_HEIGHT - 1) && grid.isLayerEmpty(Grid.GRID_HEIGHT - 2)) {
			ShapeGenerator.getRandomShape(rand).apply(grid, rand);
		}
	}
	
	private void shiftBlocksNegX() {
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				for (int y = 0; y < Grid.GRID_HEIGHT; y++) {
					Block b = grid.getBlock(x, y, z);
					if (b instanceof AirBlock) {
						continue;
					}
					Block b2 = grid.getBlock(x - 1, y, z);
					Block b3 = grid.getBlock(x, y - 1, z);
					if (b2 != null && b2 instanceof AirBlock && b3 != null && b3 instanceof AirBlock) {
						b.moveBlock(grid, x, y, z, x - 1, y, z);
					}
				}
			}
		}
	}
	
	private void shiftBlocksPosX() {
		for (int x = Grid.GRID_WIDTH - 1; x > -1; x--) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				for (int y = 0; y < Grid.GRID_HEIGHT; y++) {
					Block b = grid.getBlock(x, y, z);
					if (b instanceof AirBlock) {
						continue;
					}
					Block b2 = grid.getBlock(x + 1, y, z);
					Block b3 = grid.getBlock(x, y - 1, z);
					if (b2 != null && b2 instanceof AirBlock && b3 != null && b3 instanceof AirBlock) {
						b.moveBlock(grid, x, y, z, x + 1, y, z);
					}
				}
			}
		}
	}
	
	private void shiftBlocksNegZ() {
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = 0; z < Grid.GRID_DEPTH; z++) {
				for (int y = 0; y < Grid.GRID_HEIGHT; y++) {
					Block b = grid.getBlock(x, y, z);
					if (b instanceof AirBlock) {
						continue;
					}
					Block b2 = grid.getBlock(x, y, z - 1);
					Block b3 = grid.getBlock(x, y - 1, z);
					if (b2 != null && b2 instanceof AirBlock && b3 != null && b3 instanceof AirBlock) {
						b.moveBlock(grid, x, y, z, x, y, z - 1);
					}
				}
			}
		}
	}
	
	private void shiftBlocksPosZ() {
		for (int x = 0; x < Grid.GRID_WIDTH; x++) {
			for (int z = Grid.GRID_DEPTH - 1; z > -1; z--) {
				for (int y = 0; y < Grid.GRID_HEIGHT; y++) {
					Block b = grid.getBlock(x, y, z);
					if (b instanceof AirBlock) {
						continue;
					}
					Block b2 = grid.getBlock(x, y, z + 1);
					Block b3 = grid.getBlock(x, y - 1, z);
					if (b2 != null && b2 instanceof AirBlock && b3 != null && b3 instanceof AirBlock) {
						b.moveBlock(grid, x, y, z, x, y, z + 1);
					}
				}
			}
		}
	}
	
	public void tickMenu() {
		instance.currMenu.update(MouseHandler.getX(), MouseHandler.getY(), Display.getWidth(), Display.getHeight());
		if (KeyHandler.pressed(Controls.getEsc())) {
			instance.currMenu = instance.currMenu.getEscapeMenu();
		}
	}
	
	public synchronized Grid getGrid() {
		return grid;
	}
}
