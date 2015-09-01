package com.redstoner.nemes.t3tris.gameplay;

import java.util.Random;

import com.redstoner.nemes.t3tris.world.Grid;

public class ShapeLineZ extends Shape {

	public ShapeLineZ(Random rand) {
		int x = blocks.length / 2;
		int z = blocks[0].length / 2;
		
		blocks[x][z - 2] = Grid.generateBlock(rand);
		blocks[x][z - 1] = Grid.generateBlock(rand);
		blocks[x][z    ] = Grid.generateBlock(rand);
		blocks[x][z + 1] = Grid.generateBlock(rand);
		blocks[x][z + 2] = Grid.generateBlock(rand);
	}
	
}
