package com.redstoner.nemes.t3tris.util;

public enum EnumKeyBind {

	W, A, S, D, UP, LEFT, DOWN, RIGHT, ESCAPE, SPACE;
	
	public static int getKeyCode(EnumKeyBind key) {
		switch (key) {
		case W: return Controls.getW();
		case A: return Controls.getA();
		case S: return Controls.getS();
		case D: return Controls.getD();
		case UP: return Controls.getUp();
		case LEFT: return Controls.getLeft();
		case DOWN: return Controls.getDown();
		case RIGHT: return Controls.getRight();
		case ESCAPE: return Controls.getEsc();
		case SPACE: return Controls.getSpace();
		default: return 0;
		}
	}
	
	public static void setKeyCode(EnumKeyBind key, int value) {
		switch (key) {
		case W: Controls.setW(value); return;
		case A: Controls.setA(value); return;
		case S: Controls.setS(value); return;
		case D: Controls.setD(value); return;
		case UP: Controls.setUp(value); return;
		case LEFT: Controls.setLeft(value); return;
		case DOWN: Controls.setDown(value); return;
		case RIGHT: Controls.setRight(value); return;
		case ESCAPE: Controls.setEsc(value); return;
		case SPACE: Controls.setSpace(value); return;
		default: return;
		}
	}
	
}
