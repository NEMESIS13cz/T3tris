package com.redstoner.nemes.t3tris;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.redstoner.nemes.t3tris.gfx.TextureManager;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.Options;
import com.redstoner.nemes.t3tris.util.Stats;
import com.redstoner.nemes.t3tris.world.Grid;

public class Render extends Thread {

	private T3tris instance;
	private int frames;
	private Grid grid;
	private Random rand = new Random();
	
	public Render(T3tris inst) {
		instance = inst;
	}
	
	public void createWindow() {
		try {
			Display.setDisplayMode(new DisplayMode(Options.startWidth, Options.startHeight));
			Display.setTitle(Options.name);
			Display.setResizable(true);
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void initializeOpenGL() {
		 GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
		 GLU.gluPerspective(Options.FOV, Options.startWidth/Options.startHeight, Options.zNear, Options.zFar);
		 GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 GL11.glEnable(GL11.GL_DEPTH_TEST);
		 GL11.glEnable(GL11.GL_TEXTURE_2D);
		 GL11.glEnable(GL11.GL_BLEND);
		 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 GL11.glEnable(GL11.GL_ALPHA_TEST);
		 GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		 GL11.glEnable(GL11.GL_CULL_FACE);
		 GL11.glCullFace(GL11.GL_BACK);
	}
	
	public void reInitializeOpenGL() {
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(Options.FOV, (float)Display.getWidth()/(float)Display.getHeight(), Options.zNear, Options.zFar);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	public void cleanUp() {
		Keyboard.destroy();
		Mouse.destroy();
		TextureManager.deleteAllTextures();
		Display.destroy();
		System.gc();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void run() {
		setName("Render Thread");
		long next_frame = System.currentTimeMillis();
		long frame_time = 1000 / Options.framerateLimit;
		GameState state;
		long next_second = System.currentTimeMillis();
		long second_time = 1000;
		
		createWindow();
		initializeOpenGL();
		
		TextureManager.loadTexture(false, "missing", "missing");
		TextureManager.loadTexture(true, "cross", "menu_exit");
		
		instance.setCurrentGameState(GameState.MENU);
		
		grid = instance.tick.getGrid();
		
		try {
			while ((state = instance.getCurrentGameState()) != GameState.CLOSING) {
				if (next_frame < System.currentTimeMillis()) {
					if (Display.wasResized()) {
						reInitializeOpenGL();
					}
					long frame_start_time = System.currentTimeMillis();
					next_frame += frame_time;
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
					if (state == GameState.MENU) {
						renderMenu();
					}else{
						renderGame();
					}
					Display.update();
					frames++;
					long temp = System.currentTimeMillis() - frame_start_time;
					if (temp < frame_time) {
						sleep(frame_time - temp);
					}
					if (Display.isCloseRequested()) {
						instance.setCurrentGameState(GameState.CLOSING);
					}
				}
				if (next_second < System.currentTimeMillis()) {
					next_second += second_time;
					
					Display.setTitle(Options.name + " | TPS: " + Stats.getTPS() + " | FPS: " + Stats.getFPS() + " | Uptime: " + Stats.getUptime());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cleanUp();
	}
	
	protected synchronized int getFrames() {
		int fps = frames;
		frames = 0;
		return fps;
	}
	
	public void renderMenu() {
		int w = Display.getWidth();
		int h = Display.getHeight();
		
		start2D(w, h);
		disableTextures();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor4f(0, 0, 0, 1);
		GL11.glVertex2f(0, h);
		GL11.glColor4f(0, 0.8f, 0, 1);
		GL11.glVertex2f(w, h);
		GL11.glColor4f(0, 0, 0.8f, 1);
		GL11.glVertex2f(w, 0);
		GL11.glColor4f(0.8f, 0, 0, 1);
		GL11.glVertex2f(0, 0);
		
		GL11.glEnd();
		
		enableTextures();
		
		instance.currMenu.render(w, h);
		
		end2D();
	}
	
	public void renderGame() {		
		GL11.glTranslatef(-10, -16, -50);
		GL11.glRotatef(30, 1, 0, 0);
		GL11.glRotatef(45, 0, 1, 0);
		disableTextures();
		
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 32; y++) {
				for (int z = 0; z < 16; z++) {
					grid.getBlock(x, y, z).render(grid, this, x, y, z, rand);
				}
			}
		}
		enableTextures();
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(-30, 1, 0, 0);
		GL11.glTranslatef(10, 16, 50);
	}
	
	private void start2D(int w, int h) {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0f, (float)w, (float)h, 0.0f, -1.0f, 1.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	private void end2D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	public static void disableTextures() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	public static void enableTextures() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
