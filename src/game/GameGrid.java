package game;

public class GameGrid {
	public static final int NOTHING = 0, JUST_WHITE = 1, JUST_BLACK = 2;

	// Places `pieceType` at location `(x,y)`.
  public void set(int x, int y, int pieceType) {
    if (inBounds(x,y)) { grid[x][y] = pieceType; }
  }
	
  public int get(int x, int y) {
    if (inBounds(x,y)) { return grid[x][y]; }
    else               { return NOTHING;    }
  }  

  // Flips the piece at `(x,y)`.
  public void flip(int x, int y) {
    int newPiece;
    switch (get(x,y)) {
      case NOTHING:
        newPiece = NOTHING;    break;
      case JUST_WHITE:
        newPiece = JUST_BLACK; break;
      case JUST_BLACK:
        newPiece = JUST_WHITE; break;
    }
    set(x,y,newPiece);
  }

  // ----> IMPLEMENTATION DETAILS <----
  private int grid[][];
  private final int DEFAULT_WIDTH  = 8,
                    DEFAULT_HEIGHT = 8;

  // ----> CONSTRUCTION <----
  public GameGrid(int width, int height) { grid = new int[width][height];       }
  public GameGrid()                      { this(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
  
  // ----> AUXILARY FUNCTIONS <----
  public boolean inBounds(x,y) {
    return !(x < 0 || y < 0 || x > grid.length || y > grid[0].length);
  } 
}
