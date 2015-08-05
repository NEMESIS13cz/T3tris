package com.redstoner.nemes.t3tris;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.redstoner.nemes.t3tris.gfx.FontManager;
import com.redstoner.nemes.t3tris.gfx.TextureManager;
import com.redstoner.nemes.t3tris.gfx.Window;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.Options;

public class Render extends Thread {

	private T3tris instance;
	private int frames;
	private Window window;
	
	public Render(T3tris inst) {
		instance = inst;
	}
	
	public void createWindow() {
		window = new Window();
		try {
			Display.setDisplayMode(new DisplayMode(Options.startWidth, Options.startHeight));
			Display.setTitle(Options.name);
			Display.setResizable(true);
			Display.setParent(window);
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
		 GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
		 //GL11.glEnable(GL11.GL_CULL_FACE);
		 //GL11.glCullFace(GL11.GL_BACK);
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
		window.cleanUp();
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
		
		createWindow();
		initializeOpenGL();
		
		TextureManager.loadTexture(false, "missing", "missing");
		TextureManager.loadTexture(true, "cross", "menu_exit");
		FontManager.loadFont(true, 24, 0, "courier", "Courier New");
		
		instance.setCurrentGameState(GameState.MENU);
		
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
		int mouseX = Mouse.getX();
		int mouseY = h - Mouse.getY();
		
		start2D(w, h);
		disableTextures();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor4f(0, 0, 0, 1);
		GL11.glVertex2f(0, 40);
		GL11.glColor4f(0, 0.8f, 0, 1);
		GL11.glVertex2f(w, 40);
		GL11.glColor4f(0, 0, 0.8f, 1);
		GL11.glVertex2f(w, h);
		GL11.glColor4f(0.8f, 0, 0, 1);
		GL11.glVertex2f(0, h);
		
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glColor4f(0.2f, 0.2f, 0.2f, 1);
		GL11.glVertex2f(0, 0);
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glVertex2f(w, 0);
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glVertex2f(w, 40);
		GL11.glColor4f(0.2f, 0.2f, 0.2f, 1);
		GL11.glVertex2f(0, 40);
		
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.4f);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(w - 40, 0);
		GL11.glVertex2f(w - 40, 40);
		GL11.glVertex2f(0, 40);

		GL11.glEnd();
		
		enableTextures();
		TextureManager.bindTexture("cross");
		GL11.glBegin(GL11.GL_QUADS);

		if (mouseX > w - 40 && mouseY < 40) {
			GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.7f);
		}
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(w - 40, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(w, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(w, 40);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(w - 40, 40);
		
		GL11.glEnd();
		
		instance.currMenu.render(w, h);
		
		end2D();
		
		window.checkMouse(w, h, mouseX, mouseY, this);
	}
	
	public void renderGame() {
		
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
