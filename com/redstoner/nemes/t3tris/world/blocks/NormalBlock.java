package com.redstoner.nemes.t3tris.world.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.world.Block;
import com.redstoner.nemes.t3tris.world.Grid;

public class NormalBlock extends Block {

	public NormalBlock(Color c) {
		super(c);
	}

	public void render(Grid g, Render r, int x, int y, int z) {
		Block block1 = g.getBlock(x, y + 1, z);
		Block block2 = g.getBlock(x, y, z + 1);
		Block block3 = g.getBlock(x - 1, y, z);
		
		GL11.glColor3f((getColour().getRed() / 255.0f), (getColour().getGreen() / 255.0f), (getColour().getBlue() / 255.0f));
		GL11.glBegin(GL11.GL_QUADS);
		
		if (block1 == null || block1.isTransparent()) {
			GL11.glVertex3i(x, y, z + 1);
			GL11.glVertex3i(x + 1, y, z + 1);
			GL11.glVertex3i(x + 1, y, z);
			GL11.glVertex3i(x, y, z);
		}
		
		if (block2 == null || block2.isTransparent()) {
			GL11.glVertex3i(x, y - 1, z + 1);
			GL11.glVertex3i(x + 1, y - 1, z + 1);
			GL11.glVertex3i(x + 1, y, z + 1);
			GL11.glVertex3i(x, y, z + 1);
		}

		if (block3 == null || block3.isTransparent()) {
			GL11.glVertex3i(x, y - 1, z);
			GL11.glVertex3i(x, y - 1, z + 1);
			GL11.glVertex3i(x, y, z + 1);
			GL11.glVertex3i(x, y, z);
		}
		
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
	}
}
