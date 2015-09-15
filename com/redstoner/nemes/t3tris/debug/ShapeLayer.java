package com.redstoner.nemes.t3tris.debug;

import java.util.Random;

import com.redstoner.nemes.t3tris.gameplay.Shape;
import com.redstoner.nemes.t3tris.world.Grid;

public class ShapeLayer extends Shape {

	public ShapeLayer(Random rand) {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				blocks[i][j] = Grid.generateBlock(rand);
			}
		}
	}
	
}
