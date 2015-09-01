package com.redstoner.nemes.t3tris.gameplay;

import java.util.Random;

public class ShapeGenerator {

	public static Shape getRandomShape(Random rand) {
		return new Square(rand);
	}
	
}
