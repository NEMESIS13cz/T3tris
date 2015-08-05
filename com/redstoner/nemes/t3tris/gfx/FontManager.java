package com.redstoner.nemes.t3tris.gfx;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

public class FontManager {
	
	private static HashMap<String, TrueTypeFont> fontMap = new HashMap<String, TrueTypeFont>();
	
	public static TrueTypeFont getFont(String name) {
		return fontMap.get(name);
	}
	
	public static void draw(String name, String text, float x, float y) {
		TextureImpl.bindNone();
		TrueTypeFont font = FontManager.getFont(name);
		font.drawString(x, y, text);
	}
	
	public static void loadFont(boolean aa, int size, int formatting, String name, String fontName) {
	    Font awtFont = new Font(fontName, formatting, size);
	    fontMap.put(name, new TrueTypeFont(awtFont, aa));
	}
}
