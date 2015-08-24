package com.redstoner.nemes.t3tris.gfx;

import java.util.HashMap;

import com.redstoner.nemes.t3tris.util.FontCharacter;

public class FontMap {

	private static String texName;
	private static HashMap<Character, FontCharacter> chars = new HashMap<Character, FontCharacter>();
	
	public static void initialize(String name, String file, char[] characters, int[] charWidth, int texCharWidth, int texCharHeight) throws Exception {
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
		for (int i = 0; i < characters.length; i++) {
			
			chars.put(characters[i], new FontCharacter(((double)charX) / texWidth, ((double)charY) / texHeight, (double)(charX + charWidth[i]) / texWidth, (double)(charY + yStep) / texHeight, charWidth[i], yStep));
			
			charX += xStep;
			if (charX + xStep > texWidth) {
				charX = 0;
				charY += yStep;
			}
		}
	}
	
	public static void drawString(double x, double y, double scale, String s) {
		TextureManager.bindTexture(texName);
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			FontCharacter fc = chars.get(c);
			fc.draw(x, y, scale);
			x += (fc.getWidth() * scale);
		}
		TextureManager.unbind();
	}
}
