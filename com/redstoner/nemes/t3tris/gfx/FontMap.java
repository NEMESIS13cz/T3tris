package com.redstoner.nemes.t3tris.gfx;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.redstoner.nemes.t3tris.util.FontCharacter;

public class FontMap {

	private static String texName;
	private static HashMap<Character, FontCharacter> chars = new HashMap<Character, FontCharacter>();
	private static FontCharacter missing_char;
	
	public static void initialize(String name, String file, char[] characters, int[] charWidth, int texCharWidth, int texCharHeight, int missing_width) throws Exception {
		if (characters.length != charWidth.length) {
			throw new Exception("Character array and char width array aren't the same length!");
		}
		texName = name;
		TextureManager.loadTexture(false, name, file);
		Texture tex = TextureManager.getTexture(name);
		double texWidth = tex.getWidth();
		double texHeight = tex.getHeight();
		int charX = 0;
		int charY = 0;
		int xStep = texCharWidth;
		int yStep = texCharHeight;
		
		missing_char = new FontCharacter(0, 0, ((double)missing_width) / texWidth, ((double)yStep) / texHeight, missing_width, yStep);

		charX += xStep;
		if (charX + xStep > texWidth) {
			charX = 0;
			charY += yStep;
		}
		
		for (int i = 0; i < characters.length; i++) {
			
			chars.put(characters[i], new FontCharacter(((double)charX) / texWidth, ((double)charY) / texHeight, (double)(charX + charWidth[i]) / texWidth, (double)(charY + yStep) / texHeight, charWidth[i], yStep));
			
			charX += xStep;
			if (charX + xStep > texWidth) {
				charX = 0;
				charY += yStep;
			}
		}
	}
	
	// Format: RGB
	public static void drawString(double x, double y, double scale, String s, int color, boolean centered) {
		drawStringWithAlpha(x, y, scale, s, (color << 8) | 0xFF, centered);
	}
	
	// Format: RGBA
	public static void drawStringWithAlpha(double x, double y, double scale, String s, int color, boolean centered) {
		float r = ((color >> 24) & 0xFF) / 255.0f;
		float g = ((color >> 16) & 0xFF) / 255.0f;
		float b = ((color >> 8) & 0xFF) / 255.0f;
		float a = (color & 0xFF) / 255.0f;
		GL11.glColor4f(r, g, b, a);
		drawText(x, y, scale, s, centered);
	}
	
	public static void drawString(double x, double y, double scale, String s, boolean centered) {
		GL11.glColor4f(1, 1, 1, 1);
		drawText(x, y, scale, s, centered);
	}
	
	private static void drawText(double x, double y, double scale, String s, boolean centered) {
		TextureManager.bindTexture(texName);
		char[] charArray = s.toCharArray();
		
		if (centered) {
			double offset = 0;
			for (char c : charArray) {
				FontCharacter fc = chars.get(c);
				if (fc == null) {
					offset += (missing_char.getWidth() + 1) * scale;
					continue;
				}
				offset += (fc.getWidth() + 1) * scale;
			}
			x -= offset / 2;
		}
		
		for (char c : charArray) {
			FontCharacter fc = chars.get(c);
			if (fc == null) {
				missing_char.draw(x, y, scale);
				x += (missing_char.getWidth() + 1) * scale;
				continue;
			}
			fc.draw(x, y, scale);
			x += ((fc.getWidth() + 1) * scale);
		}
		
		TextureManager.unbind();
	}
}
