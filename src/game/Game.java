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

public class Game {
	public int x=40;
	public int y=300;
	public int turn = 1;
	public int[][] board;
	UnicodeFont font;
	
	public static void main(String[] args)
	{
		Game main = new Game();
		main.run();
	}

	public void run()
	{
		try {
			Display.setDisplayMode(new DisplayMode(800,900));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 900, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		setup();
		drawBackground();
		System.out.println("Setup Complete");
		while (!Display.isCloseRequested()) {
			drawBackground();
			drawBoard();
			/*GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			// set the color of the quad (R,G,B,A)
			GL11.glColor3f(x/1000f,y/1000f,z);
			// draw quad
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x,y);
			GL11.glVertex2f(x+200,y);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x,y+200);
			GL11.glEnd();*/
			//System.out.println("Looping always");
			if(pollInput())
			{
				doMove();
			}
			int win = checkWin();
			if(win!=0)
			{
				if(win==1)
					font.drawString(100, 50, "Black wins!", Color.red);
				if(win==2)
					font.drawString(100, 50, "White wins!", Color.red);
			}
			Display.update();
		}
		Display.destroy();
	}
	
	public void setup()
	{
		board = new int[8][8];
		board[3][3]=1;
		board[3][4]=2;
		board[4][3]=2;
		board[4][4]=1;
		
		// load a default java font
	    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	    font = new UnicodeFont(awtFont);
	    font.getEffects().add(new ColorEffect(java.awt.Color.white));
	    font.addAsciiGlyphs();
	    try{
	    font.loadGlyphs();
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Is this what's wrong?");
	    }
	         

	}
	
	public int checkWin()
	{
		int [] count = new int[3];
		for(int i = 0; i<board.length;i++)
		{
			for(int j = 0; j<board[i].length;j++)
			{
				count[board[i][j]]++;
			}
		}
		if(count[0]==0)
			return count[1]>count[2]?1:2;
			if(count[1]==0)
				return 2;	
			if(count[2]==0)
				return 1;
			return 0;
	}
	
	public boolean oInput()
	{
		return false;
	}

	public boolean doMove()
	{
		//check legality
		if(x>800||y>800)
			return oInput();
		if(board[x/100][y/100]!=0)
		return false;
		if(!checkFlips(turn,x/100,y/100))
			return false;
		board[x/100][y/100]=turn;
		if(turn==1)
			turn=2;
		else
			turn=1;
		return true;
	}
	
	public boolean checkFlips(int color, int xPos, int yPos)
	{
		boolean valid=false;
		int [][] tBoard = new int[8][8];
		for(int i = 0; i<board.length;i++)
		{
			for(int j = 0; j<board[i].length;j++)
			{
				tBoard[i][j]=board[i][j];
			}
		}
		//down
		for(int i=yPos-1;i>0;i--)
		{
			if(tBoard[xPos][i]==0)
				break;
			if(tBoard[xPos][i]==color)
			{
				if(Math.abs(yPos-i)<=1)
					break;
				for(int r=i;r<yPos;r++)
				{
					tBoard[xPos][r]=color;
				}
				valid=true;
			}
		}
		//up
		for(int i=yPos+1;i<8;i++)
		{
			if(tBoard[xPos][i]==0)
				break;
			if(tBoard[xPos][i]==color)
			{
				if(Math.abs(yPos-i)<=1)
					break;
				for(int r=i;r>yPos;r--)
				{
					tBoard[xPos][r]=color;
				}
				valid=true;
			}
		}
		//left
		for(int j=xPos-1;j>0;j--)
		{
			if(tBoard[j][yPos]==0)
				break;
			if(tBoard[j][yPos]==color)
			{
				if(Math.abs(xPos-j)<=1)
					break;
				for(int c=j;c<xPos;c++)
				{
					tBoard[c][yPos]=color;
				}
				valid=true;
			}
		}
		//right
		for(int j=xPos+1;j<8;j++)
		{
			if(tBoard[j][yPos]==0)
				break;
			if(tBoard[j][yPos]==color)
			{
				if(Math.abs(xPos-j)<=1)
					break;
				for(int c=j;c>xPos;c--)
				{
					tBoard[c][yPos]=color;
				}
				valid=true;
			}
		}
		//up-right
		int d=1;
		while(true)
		{
			if(xPos+d>7||yPos+d>7)
				break;
			if(tBoard[xPos+d][yPos+d]==0)
				break;
			if(tBoard[xPos+d][yPos+d]==color)
			{
				if(d<=1)
					break;
				for(int a=d;a>0;a--)
				{
					tBoard[xPos+a][yPos+a]=color;
				}
				valid=true;
			}
			d++;
			if(xPos+d>7||yPos+d>7)
				break;
		}
		//down-left
		d=1;
		while(true)
		{
			if(xPos-d<0||yPos-d<0)
				break;
			if(tBoard[xPos-d][yPos-d]==0)
				break;
			if(tBoard[xPos-d][yPos-d]==color)
			{
				if(d<=1)
					break;
				for(int a=d;a>0;a--)
				{
					tBoard[xPos-a][yPos-a]=color;
				}
				valid=true;
			}
			d++;
			if(xPos-d<0||yPos-d<0)
				break;
		}
		
		d=1;
		while(true)
		{
			if(xPos+d>7||yPos-d<0)
				break;
			if(tBoard[xPos+d][yPos-d]==0)
				break;
			if(tBoard[xPos+d][yPos-d]==color)
			{
				if(d<=1)
					break;
				for(int a=d;a>0;a--)
				{
					tBoard[xPos+a][yPos-a]=color;
				}
				valid=true;
			}
			d++;
			if(xPos+d>7||yPos-d<0)
				break;
		}
		//down-left
		d=1;
		while(true)
		{
			if(xPos-d<0||yPos+d>7)
				break;
			if(tBoard[xPos-d][yPos+d]==0)
				break;
			if(tBoard[xPos-d][yPos+d]==color)
			{
				if(d<=1)
					break;
				for(int a=d;a>0;a--)
				{
					tBoard[xPos-a][yPos+a]=color;
				}
				valid=true;
			}
			d++;
			if(xPos-d<0||yPos+d>7)
				break;
		}
		
		if(valid)
			for(int i = 0; i<board.length;i++)
			{
				for(int j = 0; j<board[i].length;j++)
				{
					board[i][j]=tBoard[i][j];
				}
			}
		return valid;
	}
	
	public void drawBoard()
	{
		for(int i = 0; i<board.length;i++)
		{
			for(int j = 0; j<board[i].length;j++)
			{
				if(board[i][j]!=0)
					drawCircle(100*i+50,100*j+50,50,100,board[i][j]);
			}
		}
	}
	
	public void drawBackground()
	{
		//font.drawString(100, 850, "Pass", Color.green);
		GL11.glColor3f(0,.5f,0);
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0,0);
		GL11.glVertex2f(800,0);
		GL11.glVertex2f(800,800);
		GL11.glVertex2f(0,800);
		GL11.glColor3f(0.0f, 0.0f, 0.0f);
		GL11.glEnd();
		
	    for(int i = 100; i<800;i+=100)
	    {
		    GL11.glBegin(GL11.GL_LINE_STRIP);
	    GL11.glVertex2d(i, 0);
	    GL11.glVertex2d(i, 800);
	    GL11.glEnd();
	    GL11.glBegin(GL11.GL_LINE_STRIP);
    GL11.glVertex2d(0, i);
    GL11.glVertex2d(800, i);
    GL11.glEnd();
	    }
	}
	
	public void drawCircle(float cx, float cy, float r, int num_segments, int color) 
	{ 
		if(color==1)
			GL11.glColor3f(0.0f, 0.0f, 0.0f);
		else
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glPushMatrix();
		GL11.glTranslatef(cx, cy, 0);
		GL11.glScalef(r, r, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= num_segments; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
		    double angle = Math.PI * 2 * i / num_segments;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		GL11.glPopMatrix();
	}

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
		}*/
	}
}
