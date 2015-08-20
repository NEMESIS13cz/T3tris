package com.redstoner.nemes.t3tris;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.Options;
import com.redstoner.nemes.t3tris.world.Grid;
import com.redstoner.nemes.t3tris.world.blocks.NormalBlock;

public class Tick extends Thread {

	private T3tris instance;
	private int ticks;
	private Grid grid;
	
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
			
			Random rand = new Random();
			//TODO REMOVE
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 32; j++) {
					for (int k = 0; k < 16; k++) {
						grid.setBlock(new NormalBlock(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))), i, j, k);
					}
				}
			}
			
			while ((state = instance.getCurrentGameState()) != GameState.CLOSING) {
				if (next_tick < System.currentTimeMillis()) {
					long tick_start_time = System.currentTimeMillis();
					next_tick += tick_time;
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
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			instance.setCurrentGameState(GameState.MENU);
		}
		
	}
	
	public void tickMenu() {
		instance.currMenu.update(Mouse.getX(), Display.getHeight() - Mouse.getY(), Display.getWidth(), Display.getHeight());
	}
	
	public synchronized Grid getGrid() {
		return grid;
	}
}
