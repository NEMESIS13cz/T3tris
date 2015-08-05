package com.redstoner.nemes.t3tris;

import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.Stats;

public class Util extends Thread {

	private T3tris instance;
	protected int seconds;
	
	public Util(T3tris inst) {
		instance = inst;
	}
	
	public void run() {
		setName("Utility Thread");
		long next_second = System.currentTimeMillis();
		long second_time = 1000;
		
		try {
			while (instance.getCurrentGameState() == GameState.STARTING) {
				sleep(1);
			}
			while (instance.getCurrentGameState() != GameState.CLOSING) {
				if (next_second < System.currentTimeMillis()) {
					long second_start_time = System.currentTimeMillis();
					next_second += second_time;
					seconds++;
					Stats.setFPS(instance.render.getFrames());
					Stats.setTPS(instance.tick.getTicks());
					Stats.setUptime(seconds);
					long temp = System.currentTimeMillis() - second_start_time;
					if (temp < second_time) {
						sleep(second_time - temp);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
