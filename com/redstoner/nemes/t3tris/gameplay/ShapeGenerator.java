package com.redstoner.nemes.t3tris.gameplay;

import java.util.Random;

public class ShapeGenerator {
	
	public static Shape getRandomShape(Random rand) {
		Shape s = null;
		int i = rand.nextInt(4);
		
		switch(i) {
		case 0: s = new ShapeSquare(rand); break;
		case 1: s = new ShapeLineX(rand); break;
		case 2: s = new ShapeLineZ(rand); break;
		case 3: s = new ShapeL(rand); break;
		}
		
		return s;
	}
}
