package com.redstoner.nemes.t3tris.gameplay;

import java.util.Random;

import com.redstoner.nemes.t3tris.world.Grid;

public class ShapeSquare extends Shape {

	public ShapeSquare(Random rand) {
		int x = blocks.length / 2;
		int z = blocks[0].length / 2;
		
		blocks[x    ][z    ] = Grid.generateBlock(rand);
		blocks[x + 1][z    ] = Grid.generateBlock(rand);
		blocks[x    ][z + 1] = Grid.generateBlock(rand);
		blocks[x + 1][z + 1] = Grid.generateBlock(rand);
	}
	
	
}
