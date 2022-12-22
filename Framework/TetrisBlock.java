import java.awt.Color;
import java.util.Random;

/*
* tetris block
*/ 
public class TetrisBlock {
  private int [][] shape;
  private Color color;
  private int x, y;
  private int[][][] shapes; // store different rotations
  private int currentRotation;
  
   //Colors of blockets
  final Color PURPLE = new Color(195, 0, 255);
  final Color ORANGE = new Color(255, 140, 0);

  private Color[] availableColors = {Color.green, Color.red, Color.blue, Color.cyan, ORANGE, PURPLE};
  
  public TetrisBlock( int[][] shape) {
    this.shape = shape;

    initShapes();
  }
    
  /*
  * initiate shapes
  */
  private void initShapes() {
    shapes = new int[4][][];

    for (int i = 0; i < 4; i++) {
      int r = shape[0].length;
      int c = shape.length;

      shapes[i] = new int[r][c];
      
      for(int y = 0; y < r; y++) {
        for (int x = 0; x < c; x++) {
            shapes[i][y][x] = shape[c - x - 1][y]; // creates new rotated blocks in one group // refer to time stamp 15:30
          }
      }
      shape = shapes[i]; // initate new rotated shape to current
    }  
  }
    
  /*
  * spawner
  * @param integer gridWidth
  */
  public void spawn(int gridWidth) {
    Random r = new Random();
    currentRotation = r.nextInt( shapes.length ); // changes rotation of block spawned
    shape = shapes[currentRotation];
    
    y = -getHeight(); // shows before the grid
    x = r.nextInt(gridWidth - getWidth()); // changes position of where blocks are spawned

    color = availableColors[ r.nextInt( availableColors.length) ];
  }  
  // accessor method
  // create variables for easy access throughout GameArea, easier to manipulate objects
    
  public int[][] getShape() { return shape; }
  public Color getColor() { return color; }
  public int getHeight() { return shape.length; }
  public int getWidth() { return shape[0].length; }
  
  public int getX(){ return x; }
  public void setX (int newX){ x = newX; } // set bounds
  public int getY(){ return y; }
  public void setY (int newY){ y = newY; } // set bounds
  
  public void moveDown(){ y++; }
  public void moveLeft(){ x--; }
  public void moveRight(){ x++; }
  
  /*
  * rotate blocks
  */    
  public void rotate() {
    currentRotation++;
    if(currentRotation > 3) currentRotation = 0; // only 3 other different rotating shapes, otherwise return to default
    shape = shapes[currentRotation];
  }
  
  public int getBottomEdge(){ return y + getHeight(); }

  public int getLeftEdge(){ return x; }

  public int getRightEdge(){ return x + getWidth(); }
  
}