package com.redstoner.nemes.t3tris.util;

public class Stats {

	private static int FPS;
	private static int TPS;
	private static int uptime;
	
	public static int getFPS() {
		return FPS;
	}
	
	public static void setFPS(int fps) {
		FPS = fps;
	}
	
	public static int getTPS() {
		return TPS;
	}
	
	public static void setTPS(int tps) {
		TPS = tps;
	}
	
	public static int getUptime() {
		return uptime;
	}
	
	public static void setUptime(int u) {
		uptime = u;
	}
}
