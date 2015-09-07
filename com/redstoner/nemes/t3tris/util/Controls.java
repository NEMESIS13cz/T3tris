package com.redstoner.nemes.t3tris.util;

import org.lwjgl.input.Keyboard;

public class Controls {

	private static final String KEY_PREFIX = "key_";
	private static final String MOUSE_PREFIX = "mouse_";
	
	private static int leftMouseButton = 0;
	private static int rightMouseButton = 1;
	private static int W = Keyboard.KEY_W;
	private static int A = Keyboard.KEY_A;
	private static int S = Keyboard.KEY_S;
	private static int D = Keyboard.KEY_D;
	private static int up = Keyboard.KEY_UP;
	private static int left = Keyboard.KEY_LEFT;
	private static int down = Keyboard.KEY_DOWN;
	private static int right = Keyboard.KEY_RIGHT;
	private static int esc = Keyboard.KEY_ESCAPE;
	private static int space = Keyboard.KEY_SPACE;
	
	private static int[] keyboardKeys = new int[] {
			Keyboard.KEY_ESCAPE, Keyboard.KEY_SPACE,
			Keyboard.KEY_W, Keyboard.KEY_A, Keyboard.KEY_S, Keyboard.KEY_D, 
			Keyboard.KEY_UP, Keyboard.KEY_LEFT, Keyboard.KEY_DOWN, Keyboard.KEY_RIGHT
			};
	private static int[] mouseButtons = new int[] {0 /* Left */, 1 /* Right */};
	
	public static int getLeftMouseButton() {
		return leftMouseButton;
	}
	
	public static void setLeftMouseButton(int _leftMouseButton) {
		leftMouseButton = _leftMouseButton;
		updateArrays();
	}
	
	public static int getRightMouseButton() {
		return rightMouseButton;
	}
	
	public static void setRightMouseButton(int _rightMouseButton) {
		rightMouseButton = _rightMouseButton;
		updateArrays();
	}
	
	public static int getW() {
		return W;
	}
	
	public static void setW(int w) {
		W = w;
		updateArrays();
	}
	
	public static int getA() {
		return A;
	}
	
	public static void setA(int a) {
		A = a;
		updateArrays();
	}
	
	public static int getS() {
		return S;
	}
	
	public static void setS(int s) {
		S = s;
		updateArrays();
	}
	
	public static int getD() {
		return D;
	}
	
	public static void setD(int d) {
		D = d;
		updateArrays();
	}
	
	public static int getUp() {
		return up;
	}
	
	public static void setUp(int _up) {
		up = _up;
		updateArrays();
	}
	
	public static int getLeft() {
		return left;
	}
	
	public static void setLeft(int _left) {
		left = _left;
		updateArrays();
	}
	
	public static int getDown() {
		return down;
	}
	
	public static void setDown(int _down) {
		down = _down;
		updateArrays();
	}
	
	public static int getRight() {
		return right;
	}
	
	public static void setRight(int _right) {
		right = _right;
		updateArrays();
	}
	
	public static int getEsc() {
		return esc;
	}
	
	public static void setEsc(int _esc) {
		esc = _esc;
		updateArrays();
	}
	
	public static int getSpace() {
		return space;
	}
	
	public static void setSpace(int _space) {
		space = _space;
		updateArrays();
	}
	
	public static int[] getMouseButtons() {
		return mouseButtons.clone();
	}
	
	public static int[] getKeyboardKeys() {
		return keyboardKeys.clone();
	}
	
	public static void load() throws DataException {
		DataFile def = new DataFile();
		def.addInteger(Controls.KEY_PREFIX + "escape", esc);
		def.addInteger(Controls.KEY_PREFIX + "space", space);
		def.addInteger(Controls.KEY_PREFIX + "w", W);
		def.addInteger(Controls.KEY_PREFIX + "a", A);
		def.addInteger(Controls.KEY_PREFIX + "s", S);
		def.addInteger(Controls.KEY_PREFIX + "d", D);
		def.addInteger(Controls.KEY_PREFIX + "up", up);
		def.addInteger(Controls.KEY_PREFIX + "left", left);
		def.addInteger(Controls.KEY_PREFIX + "down", down);
		def.addInteger(Controls.KEY_PREFIX + "right", right);
		def.addInteger(Controls.MOUSE_PREFIX + "left", leftMouseButton);
		def.addInteger(Controls.MOUSE_PREFIX + "right", rightMouseButton);
		DataFile file = new DataFile(Constants.FILE_PATH, "controls.dat", def);
		esc = file.getInteger(Controls.KEY_PREFIX + "escape");
		space = file.getInteger(Controls.KEY_PREFIX + "space");
		W = file.getInteger(Controls.KEY_PREFIX + "w");
		A = file.getInteger(Controls.KEY_PREFIX + "a");
		S = file.getInteger(Controls.KEY_PREFIX + "s");
		D = file.getInteger(Controls.KEY_PREFIX + "d");
		up = file.getInteger(Controls.KEY_PREFIX + "up");
		left = file.getInteger(Controls.KEY_PREFIX + "left");
		down = file.getInteger(Controls.KEY_PREFIX + "down");
		right = file.getInteger(Controls.KEY_PREFIX + "right");
		leftMouseButton = file.getInteger(Controls.MOUSE_PREFIX + "left");
		rightMouseButton = file.getInteger(Controls.MOUSE_PREFIX + "right");
		
		updateArrays();
	}
	
	public static void save() {
		DataFile file = new DataFile();
		file.addInteger(Controls.KEY_PREFIX + "escape", esc);
		file.addInteger(Controls.KEY_PREFIX + "space", space);
		file.addInteger(Controls.KEY_PREFIX + "w", W);
		file.addInteger(Controls.KEY_PREFIX + "a", A);
		file.addInteger(Controls.KEY_PREFIX + "s", S);
		file.addInteger(Controls.KEY_PREFIX + "d", D);
		file.addInteger(Controls.KEY_PREFIX + "up", up);
		file.addInteger(Controls.KEY_PREFIX + "left", left);
		file.addInteger(Controls.KEY_PREFIX + "down", down);
		file.addInteger(Controls.KEY_PREFIX + "right", right);
		file.addInteger(Controls.MOUSE_PREFIX + "left", leftMouseButton);
		file.addInteger(Controls.MOUSE_PREFIX + "right", rightMouseButton);
		
		file.saveFile(Constants.FILE_PATH, "controls.dat");
	}
	
	private static void updateArrays() {
		keyboardKeys[0] = esc;
		keyboardKeys[1] = space;
		keyboardKeys[2] = W;
		keyboardKeys[3] = A;
		keyboardKeys[4] = S;
		keyboardKeys[5] = D;
		keyboardKeys[6] = up;
		keyboardKeys[7] = left;
		keyboardKeys[8] = down;
		keyboardKeys[9] = right;
		mouseButtons[0] = leftMouseButton;
		mouseButtons[1] = rightMouseButton;
	}
}
