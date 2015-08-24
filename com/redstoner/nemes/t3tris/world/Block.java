package com.redstoner.nemes.t3tris.world;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.Color;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.util.ScheduledUpdate;

public class Block {

	private float r, g, b, a;
	private ArrayList<ScheduledUpdate> updates = new ArrayList<ScheduledUpdate>();
	
	public Block(Color c) {
		if (c != null) {
			r = c.getRed() / 255.0f;
			g = c.getGreen() / 255.0f;
			b = c.getBlue() / 255.0f;
			a = c.getAlpha() / 255.0f;
		}
	}
	
	public Color getColour() {
		return new Color((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255));
	}
	
	public float getRed() {
		return r;
	}
	
	public float getGreen() {
		return g;
	}
	
	public float getBlue() {
		return b;
	}
	
	public float getAlpha() {
		return a;
	}
	
	public void update(Grid g, int x, int y, int z, Random rand) {
		
	}
	
	public void render(Grid g, Render r, int x, int y, int z, Random rand) {
		
	}
	
	public boolean isTransparent() {
		return false;
	}
	
	public void appendUpdate(ScheduledUpdate u) {
		updates.add(u);
	}
	
	public void removeUpdate(ScheduledUpdate u) {
		updates.remove(u);
	}
	
	public void moveBlock(Grid g, int x, int y, int z, int newX, int newY, int newZ) {
		g.setBlock(this, newX, newY, newZ);
		for (ScheduledUpdate u : updates) {
			u.moveUpdate(newX, newY, newZ);
		}
		g.setBlock(null, x, y, z);
	}
}
