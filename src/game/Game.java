package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Game {
	public int x=40;
	public int y=300;
	public float z;
	public static void main(String[] args)
	{
		Game main = new Game();
		main.run();
	}

	public void run()
	{
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			// set the color of the quad (R,G,B,A)
			GL11.glColor3f(x/1000f,y/1000f,z);
			// draw quad
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,y);
			GL11.glVertex2f(x+200,y);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x,y+200);
			GL11.glEnd();
			pollInput();
			Display.update();
		}
		Display.destroy();
	}

	public void pollInput() {
		if (Mouse.isButtonDown(0)) {
			x = Mouse.getX();
			y = Mouse.getY();
			System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
			z=(float)Math.random();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
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
}
