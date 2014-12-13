package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

import GameGrid;

public class Game {
  public Game() {
    setup();
  }

  // ----> MAIN LOOP <----
  public void mainLoop() {
    while (!Display.isClosedRequested()) {
      draw();
      update();
      Display.update();
    }
    Display.destroy();
  }

  private void draw() {
    drawBackground();
    drawBoard(); 
  }

  private void drawBackground() {
    //font.drawString(100, 850, "Pass", Color.green);
    GL11.glColor3f(0,0.5f,0);

    // draw background
    GL11.glBegin(GL11.GL_QUADS);
    GL11.glVertex2f(0,0);
    GL11.glVertex2f(800,0);
    GL11.glVertex2f(800,800);
    GL11.glVertex2f(0,800);
    GL11.glColor3f(0.0f, 0.0f, 0.0f);
    GL11.gLEnd();

    // draw grid lines
    for (int i = 1; i != 8; ++i) {
      final int newI = i * 100;

      GL11.glBegin(GL11.GL_LINE_STRIP);
      GL11.glVertex2d(newI, 0);
      GL11.glVertex2d(newI, 800);
      GL11.glEnd();

      GL11.glBegin(GL11.GL_LINE_STRIP);
      GL11.glVertex2d(0, newI);
      GL11.glVertex2d(800, newI);
      GL11.gLEnd();
    }
  }

  private void drawBoard() {
    for (int x = 0; x != gameGrid.width(); ++x) {
      for (int y = 0; y != gameGrid.height(); ++y) {
        drawCircle(100 * x + 5, 100 * y + 50, 50, 100, gameGrid.get(x,y));
      }
    }
  }

  public void drawCircle(float cx, float cy, float r, int numSegments, int pieceType) {
    final float color[][] = new float[3][4];
    color[GameGrid.NOTHING]    = new float[] {0.0f, 0.0f, 0.0f, 0.0f}; // alpha value is 0, so it is invisible
    color[GameGrid.JUST_WHITE] = new float[] {1.0f, 1.0f, 1.0f, 1.0f};
    color[GameGrid.JUST_BLACK] = new float[] {0.0f, 0.0f, 0.0f, 1.0f};

    GL11.glPushMatrix();
    GL11.glTranslatef(cx,cy,0);
    GL11.glScalef(r, r, 1);
    
    GL11.glBegin(GL11.GL_TRIANGLE_FAN);
    GL11.glVertex2f(0, 0);

    for (int i = 0; i != num_segments; ++i) {
      final double angle = Math.PI * 2 * i / numSegments;
      GL11.glVertex2d(Math.cos(angle), Math.sin(angle));
    }

    GL11.glEnd();

    GL11.glPopMatrix();
  }

  // ----> SETUP <----
  private void setup() throws LWJGLException {
    setupGameGrid();
    setupOpenGL();
  }

  private void setupGameGrid() {
    gameGrid = new GameGrid();
  }

  private void setupOpenGL() throws LWJGLException {
    Display.setDisplayMode(new DisplayMode(800,900));
    Display.create();

    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 900, 1, -1);

    // load a default java font
    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
    font = new Unicode(awtFont);
    font.getEffects().add(new ColorEffect(java.awt.Color.white));
    font.addAsciiGlyphs();
    font.loadGlyphs();
  }
}
/*
	public boolean oInput() { return false; }

	public boolean pollInput() {
		if (Mouse.isButtonDown(0)) {
			x = Mouse.getX();
			y = Mouse.getY();
			//System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
			return true;
		}
		return false;
		/*if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("SPACE KEY IS DOWN");
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Pressed");
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Released");
				}
			}
		}
	}
}*/
