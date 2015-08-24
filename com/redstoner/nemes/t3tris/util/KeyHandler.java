package com.redstoner.nemes.t3tris.util;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

public class KeyHandler {

	private static HashMap<Integer, Boolean> keyMap = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> keyMapOld = new HashMap<Integer, Boolean>();
	
	public static void update() {
		for (int key : Controls.getKeyboardKeys()) {
			keyMapOld.put(key, keyMap.get(key));
			keyMap.put(key, Keyboard.isKeyDown(key));
		}
	}
	
	public static boolean down(int key) {
		return keyMap.get(key);
	}
	
	public static boolean pressed(int key) {
		return keyMap.get(key) && !keyMapOld.get(key);
	}
	
	public static boolean released(int key) {
		return !keyMap.get(key) && keyMapOld.get(key);
	}
	
	static {
		for (int key : Controls.getKeyboardKeys()) {
			keyMap.put(key, false);
			keyMapOld.put(key, false);
		}
	}
}
