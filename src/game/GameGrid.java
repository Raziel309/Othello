package game;
/*
 * public procedures
 * =================
 *
 * place(x,y,piece)
 * canPlace(x,y,piece)
 * isGameOver()
 * canPieceMove(piece)
 * numPiece(piece)
 * width()
 * height()
 */
public class GameGrid {
  public static final int NOTHING = 0, JUST_WHITE = 1, JUST_BLACK = 2, ERROR = -1;

  // ----> PUBLIC PROCEDURES <----
  // precondition : canPlace(x,y,pieceType)
  public void place(int x, int y, int pieceType) {
    set(x,y, pieceType);
    for (int direction[] : DIRECTIONS) {
      if (canFlipOnDelta(x,y,direction[DIR_X], direction[DIR_Y])) {
        flipOnDelta(x, y, direction[DIR_X], direction[DIR_Y]);
      }
    }
  }

  // precondition : pieceType != NOTHING
  public boolean canPlace(int x, int y, int pieceType) {
    int oldPiece = get(x,y);

    if (oldPiece != NOTHING) { return false; }

    set(x,y,pieceType); // BOARD MODIFIED :(

    boolean canPlace = false;

    for (int direction[] : DIRECTIONS) {
      if (canFlipOnDelta(x, y, direction[DIR_X], direction[DIR_Y])) {
        canPlace = true;
      }
    }

    set(x,y,oldPiece); // BOARD RESET :)
    return canPlace;
  }

  public boolean isGameOver() {
    return !(canPieceMove(JUST_WHITE) || canPieceMove(JUST_BLACK));
  }

  // precondition : pieceType != NOTHING
  public boolean canPieceMove(int pieceType) {
    for (int x = 0; x != grid.length; ++x) {
      for (int y = 0; y != grid[x].length; ++y) {
        if (canPlace(x,y,pieceType)) { return true; }
      }
    }
    return true;
  }

  public int numPieces(int pieceType) {
    int pieceCount = 0;
    for (int x = 0; x != grid.length; ++x) {
      for (int y = 0; y != grid[x].length; ++y) {
        if (get(x,y) == pieceType) { ++pieceCount; }
      }
    }
    return pieceCount;
  }

  public int width()  { return grid.length;    }
  public int height() { return grid[0].length; }

	// Places `pieceType` at location (x, y).
  private void set(int x, int y, int pieceType) {
    if (inBounds(x,y)) { grid[x][y] = pieceType; }
  }
	
  private int get(int x, int y) {
    if (inBounds(x,y)) { return grid[x][y]; }
    else               { return NOTHING;    }
  } 

  // Flips the piece at (x, y).
  private void flip(int x, int y) {
    int piece = get(x,y);
    if (piece == NOTHING)    { set(x,y,NOTHING);    }
    if (piece == JUST_WHITE) { set(x,y,JUST_BLACK); }
    if (piece == JUST_BLACK) { set(x,y,JUST_WHITE); }
  }

  // precondition : canFlipOnDelta(x_0,y_0)
  private void flipOnDelta(int x_0, int y_0, int dx, int dy) {
    final int initialPiece = get(x_0,y_0);
    for (int x = x_0 + dx, y = y_0 + dy;
         get(x,y) != NOTHING && get(x,y) != initialPiece;
         x += dx,y += dy) {
      flip(x,y);
    }
  }

  // precondition : !(dx == 0 && dy == 0)
  //              : get(x_0,y_0) != NOTHING
  private boolean canFlipOnDelta(int x_0, int y_0, int dx, int dy) {
    final int initialPiece = get(x_0,y_0);
    int x,y;
    for (x = x_0 + dx, y = y_0 + dy;
         get(x,y) == opposite(initialPiece);
         x += dx, y += dy);
    return get(x,y) != NOTHING;
  }

  // ----> IMPLEMENTATION DETAILS <----
  private int grid[][];
  private static final int DEFAULT_WIDTH  = 8,
                           DEFAULT_HEIGHT = 8;
 
  private static final int DIRECTIONS[][] = {{-1,-1},  // up-left
                                             {-1, 0},  // left
                                             {-1, 1},  // down-left
                                             {0 ,-1},  // up
                                             {0 , 1},  // down
                                             {1 ,-1},  // up-right
                                             {1 , 0},  // right
                                             {1 , 1}}; // down-right
  final int DIR_X = 0, DIR_Y = 1;

  // ----> CONSTRUCTION <----
  public GameGrid(int width, int height) { grid = new int[width][height];       }
  public GameGrid()                      { this(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
  
  // ----> AUXILARY FUNCTIONS <----
  private boolean inBounds(int x, int y) {
    return !(x < 0 || y < 0 || x > grid.length || y > grid[0].length);
  }

  private int opposite(int pieceType) {
    if (pieceType == NOTHING)    { return NOTHING;    }
    if (pieceType == JUST_WHITE) { return JUST_BLACK; }
    if (pieceType == JUST_BLACK) { return JUST_WHITE; }
    else { return ERROR; } // should not reach here
  }
}
