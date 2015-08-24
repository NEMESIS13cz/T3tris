package com.redstoner.nemes.t3tris;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.KeyHandler;
import com.redstoner.nemes.t3tris.util.MouseHandler;
import com.redstoner.nemes.t3tris.util.Options;
import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;
import com.redstoner.nemes.t3tris.world.blocks.AirBlock;
import com.redstoner.nemes.t3tris.world.blocks.NormalBlock;

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
		
		grid = new Grid();
		
		try {
			while (instance.getCurrentGameState() == GameState.STARTING) {
				sleep(1);
			}
			grid.setBlock(new NormalBlock((Color) Color.RED), 6, 0, 7);
			grid.setBlock(new NormalBlock((Color) Color.BLUE), 7, 0, 7);
			grid.setBlock(new NormalBlock((Color) Color.GREEN), 6, 0, 6);
			grid.setBlock(new NormalBlock((Color) Color.WHITE), 7, 0, 6);
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
	}
	
	protected synchronized int getTicks() {
		int tps = ticks;
		ticks = 0;
		return tps;
	}
	
	public void tickGame() {
		grid.updateScheduler();
		if (KeyHandler.pressed(Keyboard.KEY_ESCAPE)) {
			instance.setCurrentGameState(GameState.MENU);
		}
		if (KeyHandler.pressed(Keyboard.KEY_W) || KeyHandler.pressed(Keyboard.KEY_UP)) {
			shiftBlocksNegZ();
		}
		if (KeyHandler.pressed(Keyboard.KEY_S) || KeyHandler.pressed(Keyboard.KEY_DOWN)) {
			shiftBlocksPosZ();
		}
		if (KeyHandler.pressed(Keyboard.KEY_A) || KeyHandler.pressed(Keyboard.KEY_LEFT)) {
			shiftBlocksNegX();
		}
		if (KeyHandler.pressed(Keyboard.KEY_D) || KeyHandler.pressed(Keyboard.KEY_RIGHT)) {
			shiftBlocksPosX();
		}
		if (KeyHandler.pressed(Keyboard.KEY_SPACE)) {
			grid.setBlock(new NormalBlock(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))), 7, 31, 7);
			grid.scheduleUpdate(7, 31, 7, grid, rand, 0);
		}
	}
	
	private void shiftBlocksNegX() {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 32; y++) {
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
		for (int x = 15; x > -1; x--) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 32; y++) {
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
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 32; y++) {
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
		for (int x = 0; x < 16; x++) {
			for (int z = 15; z > -1; z--) {
				for (int y = 0; y < 32; y++) {
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
	}
	
	public synchronized Grid getGrid() {
		return grid;
	}
}
