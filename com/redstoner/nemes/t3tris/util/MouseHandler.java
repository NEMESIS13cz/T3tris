package com.redstoner.nemes.t3tris.util;

import java.util.HashMap;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHandler {

	private static HashMap<Integer, Boolean> buttonMap = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> buttonMapOld = new HashMap<Integer, Boolean>();
	private static final int[] buttons = new int[] {0 /* Left */, 1 /* Right */};
	
	private static int x = 0;
	private static int y = 0;
	
	public static void update() {
		for (int button : buttons) {
			buttonMapOld.put(button, buttonMap.get(button));
			buttonMap.put(button, Mouse.isButtonDown(button));
		}
		x = Mouse.getX();
		y = Display.getHeight() - Mouse.getY();
	}
	
	public static boolean down(int button) {
		return buttonMap.get(button);
	}
	
	public static boolean pressed(int button) {
		return buttonMap.get(button) && !buttonMapOld.get(button);
	}
	
	public static boolean released(int button) {
		return !buttonMap.get(button) && buttonMapOld.get(button);
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	static {
		buttonMap.put(0, false);
		buttonMapOld.put(0, false);
		buttonMap.put(1, false);
		buttonMapOld.put(1, false);
	}
}
