package com.redstoner.nemes.t3tris;

import com.redstoner.nemes.t3tris.menu.IMenu;
import com.redstoner.nemes.t3tris.menu.MainMenu;
import com.redstoner.nemes.t3tris.util.GameState;

public class T3tris {

	public static void main(String[] args) {
		new T3tris();
	}
	
	protected Render render;
	protected Tick tick;
	protected Util util;
	protected GameState state;
	
	public Runtime runtime;
	public IMenu currMenu = new MainMenu();
	
	private static T3tris instance;
	
	public T3tris() {
		instance = this;
		state = GameState.STARTING;
		runtime = Runtime.getRuntime();
		
		render = new Render(this);
		tick = new Tick(this);
		util = new Util(this);
		
		tick.start();
		util.start();
		render.start();
	}
	
	public synchronized GameState getCurrentGameState() {
		return state;
	}

	public synchronized void setCurrentGameState(GameState s) {
		state = s;
	}
	
	public static T3tris getInstance() {
		return instance;
	}
}
