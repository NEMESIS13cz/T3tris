package com.redstoner.nemes.t3tris.gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.redstoner.nemes.t3tris.Render;
import com.redstoner.nemes.t3tris.T3tris;
import com.redstoner.nemes.t3tris.util.GameState;
import com.redstoner.nemes.t3tris.util.Options;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;
	private JFrame borderlessFrame;
	
	public Window() {
		borderlessFrame = new JFrame();
		borderlessFrame.setSize(Options.startWidth, Options.startHeight);
		borderlessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderlessFrame.setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		borderlessFrame.setLocationByPlatform(true);
		borderlessFrame.setUndecorated(true);
		borderlessFrame.getContentPane().add(this);
		borderlessFrame.setLocationRelativeTo(null);
		borderlessFrame.setVisible(true);
	}
	
	public void cleanUp() {
		borderlessFrame.dispose();
	}
	
	public void setLocation(double x, double y) {
		borderlessFrame.setLocation((int)x, (int)y);
	}
	
	public void setSize(double w, double h) {
		borderlessFrame.setSize((int)w, (int)h);
	}
	
	public void setLocation(int x, int y) {
		borderlessFrame.setLocation(x, y);
	}
	
	public void setSize(int w, int h) {
		borderlessFrame.setSize(w, h);
	}
	
	public Rectangle getScreenBounds() {
		return borderlessFrame.getGraphicsConfiguration().getBounds();
	}

	private boolean wasMouseButtonDown = false;
	private boolean isMouseSnapped = false;
	private boolean isWindowSnapped = false;
	private boolean isResizing = false;
	private boolean drawing = false;
	private int mouseX_, mouseY_;
	private int windowedW, windowedH;
	private EnumResizeDirection resize;
	
	public void checkMouse(int w, int h, int mouseX, int mouseY, Render r) {
		Point MouseLocation = MouseInfo.getPointerInfo().getLocation();

		if (!drawing && !isWindowSnapped && resize(resize, borderlessFrame, (int)MouseLocation.getX(), (int)MouseLocation.getY(), mouseX, mouseY, Mouse.isButtonDown(0))) {
			return;
		}
		
		if (Mouse.isButtonDown(0)) {
			if (!wasMouseButtonDown) { //Mouse button pressed
				mouseX_ = mouseX;
				mouseY_ = mouseY;
				wasMouseButtonDown = true;
			}
			if (mouseY_ < 40 && mouseX_ < w - 40) {
				isMouseSnapped = true;
				if (isWindowSnapped) {
					setLocation(MouseLocation.getX() - windowedW / 2, MouseLocation.getY() - mouseY_);
					setSize(windowedW, windowedH);
					isWindowSnapped = false;
					mouseX_ = Mouse.getX();
					mouseY_ = Mouse.getY();
					wasMouseButtonDown = false;
				}
				drawing = true;
				setLocation(MouseLocation.getX() - mouseX_, MouseLocation.getY() - mouseY_);
			}
		}else{
			if (wasMouseButtonDown) { //Mouse button released
				if (isMouseSnapped) {
					Rectangle screen = getScreenBounds();
					if (!isWindowSnapped && screen.getY() == MouseLocation.getY()) {
						isWindowSnapped = true;
						windowedW = Display.getWidth();
						windowedH = Display.getHeight();
						setLocation(screen.getX(), screen.getY());
						setSize(screen.getWidth(), screen.getHeight());
						r.reInitializeOpenGL();
					}
				}else if (mouseX > w - 40 && mouseY < 40) {
					T3tris.getInstance().setCurrentGameState(GameState.CLOSING);
				}
			}
			wasMouseButtonDown = false;
			isMouseSnapped = false;
			drawing = false;
		}
	}
	
	public boolean resize(EnumResizeDirection resizing, JFrame frame, int mouseXA, int mouseYA, int mouseX, int mouseY, boolean buttonLeft) {
		if (!this.isResizing) {
			if (mouseXA > frame.getX() - 4 && mouseXA < frame.getX() + 4 && mouseYA > frame.getY() + frame.getHeight() - 4 && mouseYA < frame.getY() + frame.getHeight() + 4) {
				this.resize = EnumResizeDirection.SW;
			}else if (mouseXA > frame.getX() - 4 && mouseXA < frame.getX() + 4 && mouseYA > frame.getY() - 4 && mouseYA < frame.getY() + 4) {
				this.resize = EnumResizeDirection.NW;
			}else if (mouseXA > frame.getX() + frame.getWidth() - 4 && mouseXA < frame.getX() + frame.getWidth() + 4 && mouseYA > frame.getY() + frame.getHeight() - 4 && mouseYA < frame.getY() + frame.getHeight() + 4) {
				this.resize = EnumResizeDirection.SE;
			}else if (mouseXA > frame.getX() + frame.getWidth() - 4 && mouseXA < frame.getX() + frame.getWidth() + 4 && mouseYA > frame.getY() - 4 && mouseYA < frame.getY() + 4) {
				this.resize = EnumResizeDirection.NE;
			}else if (mouseXA > frame.getX() - 4 && mouseXA < frame.getX() + 4 && mouseYA > frame.getY() - 4 && mouseYA < frame.getY() + frame.getHeight() + 4) {
				this.resize = EnumResizeDirection.W;
			}else if (mouseXA > frame.getX() + frame.getWidth() - 4 && mouseXA < frame.getX() + frame.getWidth() + 4 && mouseYA > frame.getY() - 4 && mouseYA < frame.getY() + frame.getHeight() + 4) {
				this.resize = EnumResizeDirection.E;
			}else if (mouseXA > frame.getX() - 4 && mouseXA < frame.getX() + frame.getWidth() + 4 && mouseYA > frame.getY() + frame.getHeight() - 4 && mouseYA < frame.getY() + frame.getHeight() + 4) {
				this.resize = EnumResizeDirection.S;
			}else if (mouseXA > frame.getX() - 4 && mouseXA < frame.getX() + frame.getWidth() + 4 && mouseYA > frame.getY() - 4 && mouseYA < frame.getY() + 4) {
				this.resize = EnumResizeDirection.N;
			}else{
				this.resize = EnumResizeDirection.NOT_RESIZING;
			}
			if (!this.resize.equals(EnumResizeDirection.NOT_RESIZING)) {
				if (buttonLeft) {
					isResizing = true;
					Mouse.setClipMouseCoordinatesToWindow(true);
					mouseX_ = mouseX;
					mouseY_ = mouseY;
					windowedH = frame.getHeight();
					windowedW = frame.getWidth();
					return true;
				}
				frame.setCursor(new Cursor(this.resize.getCursorCode()));
			}else{
				if (frame.getCursor() != new Cursor(Cursor.DEFAULT_CURSOR)) {
					frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return false;
				}
			}
		}else{
			if (buttonLeft) {
				if (this.resize.equals(EnumResizeDirection.SW)) {
					int oldX = frame.getX();
					int width = frame.getWidth() + (oldX - (mouseXA - mouseX_));
					int height = mouseYA - frame.getY() + (windowedH - mouseY_) + 1;
					int x = mouseXA - mouseX_;
					if (width < 1) {
						width = 1;
						x = oldX;
					}
					if (height < 1) {
						height = 1;
					}
					frame.setLocation(x, frame.getY());
					frame.setSize(width, height);
				}else if (this.resize.equals(EnumResizeDirection.NW)) {
					int oldX = frame.getX();
					int oldY = frame.getY();
					int width = frame.getWidth() + (oldX - (mouseXA - mouseX_));
					int height = frame.getHeight() + (oldY - (mouseYA - mouseY_));
					int x = mouseXA - mouseX_;
					int y = mouseYA - mouseY_;
					if (width < 1) {
						width = 1;
						x = oldX;
					}
					if (height < 1) {
						height = 1;
						y = oldY;
					}
					frame.setLocation(x, y);
					frame.setSize(width, height);
				}else if (this.resize.equals(EnumResizeDirection.SE)) {
					int width = mouseXA - frame.getX() + (windowedW - mouseX_) - 1;
					int height = mouseYA - frame.getY() + (windowedH - mouseY_) + 1;
					if (width < 1) {
						width = 1;
					}
					if (height < 1) {
						height = 1;
					}
					frame.setLocation(frame.getX(), frame.getY());
					frame.setSize(width, height);
				}else if (this.resize.equals(EnumResizeDirection.NE)) {
					int oldY = frame.getY();
					int width = mouseXA - frame.getX() + (windowedW - mouseX_);
					int height = frame.getHeight() + (oldY - (mouseYA - mouseY_));
					int y = mouseYA - mouseY_;
					if (width < 1) {
						width = 1;
					}
					if (height < 1) {
						height = 1;
						y = oldY;
					}
					frame.setLocation(frame.getX(), y);
					frame.setSize(width, height);
				}else if (this.resize.equals(EnumResizeDirection.S)) {
					int height = mouseYA - frame.getY() + (windowedH - mouseY_) - 1;
					if (height < 1) {
						height = 1;
					}
					frame.setLocation(frame.getX(), frame.getY());
					frame.setSize(frame.getWidth(), height);
				}else if (this.resize.equals(EnumResizeDirection.N)) {
					int oldY = frame.getY();
					int height = frame.getHeight() + (oldY - (mouseYA - mouseY_));
					int y = mouseYA - mouseY_;
					if (height < 1) {
						height = 1;
						y = oldY;
					}
					frame.setLocation(frame.getX(), y);
					frame.setSize(frame.getWidth(), height);
				}else if (this.resize.equals(EnumResizeDirection.W)) {
					int oldX = frame.getX();
					int width = frame.getWidth() + (oldX - (mouseXA - mouseX_));
					int x = mouseXA - mouseX_;
					if (width < 1) {
						width = 1;
						x = oldX;
					}
					frame.setLocation(x, frame.getY());
					frame.setSize(width, frame.getHeight());
				}else if (this.resize.equals(EnumResizeDirection.E)) {
					int width = mouseXA - frame.getX() + (windowedW - mouseX_) - 1;
					if (width < 1) {
						width = 1;
					}
					frame.setLocation(frame.getX(), frame.getY());
					frame.setSize(width, frame.getHeight());
				}
				return true;
			}else{
				this.isResizing = false;
				Mouse.setClipMouseCoordinatesToWindow(false);
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return false;
			}
		}
		return false;
	}
}

enum EnumResizeDirection {

	W(Cursor.W_RESIZE_CURSOR), S(Cursor.S_RESIZE_CURSOR), N(Cursor.N_RESIZE_CURSOR), E(Cursor.E_RESIZE_CURSOR), SW(Cursor.SW_RESIZE_CURSOR), SE(Cursor.SE_RESIZE_CURSOR), NW(Cursor.NW_RESIZE_CURSOR), NE(Cursor.NE_RESIZE_CURSOR), NOT_RESIZING(Cursor.DEFAULT_CURSOR);
	
	private int cCode;
	
	private EnumResizeDirection(int code) {
		cCode = code;
	}
	
	public int getCursorCode() {
		return cCode;
	}
}
