// import stuffs we might need
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import static java.awt.event.KeyEvent.*;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

/*
* GameArea extends JPanel
*/ 
public class GameArea extends JPanel {

  private int gridRows;
  private int gridColumns;
  private int gridCellSize;
  private Color[][] background; // for blocks that stop moving
  
  private TetrisBlock block;

  private TetrisBlock[] blocks;

  /*
  * game area
  * @param integer columns
  */
  public GameArea(int columns) {
    
    // GAMEAREA CREATED
    // width = 10, height = 15
    this.setBounds(50, 50, 300, 450); // sets the bounds of the game area
    //this.setBackground(Color.gray); // color
    this.setBorder(BorderFactory.createLineBorder(Color.black)); // border
    
    // grid cell = gridwidth/ num of Columns OR gridheight/ num of rows
    gridColumns = columns;
    gridCellSize = this.getBounds().width / gridColumns; 
    gridRows = this.getBounds().height / gridCellSize;
   
    background = new Color[gridRows][gridColumns];

    blocks = new TetrisBlock[] {  new IShape(), 
                                  new JShape(), 
                                  new LShape(), 
                                  new OShape(),
                                  new SShape(), 
                                  new TShape(), 
                                  new ZShape() };

    }
  
  // spawns block backup
  // public TetrisBlock block = new TetrisBlock( new int[][]{ {1, 0}, {1, 0}, {1, 1} }, Color.blue );

  /*
  * block spawner
  */    
  public void spawnBlock() {
    Random r = new Random();
    // spawns random block shapes
    block = blocks[ r.nextInt( blocks.length )];
    block.spawn(gridColumns);
  }

  /*
  * boolean for block out of bounds
  */    
  public boolean isBlockOutOfBounds(){
    if(block.getY() < 0) {
      block = null;
      return true;
    }
    return false;
  }

    
  // Note: Repainting the block makes movement less laggy

  /*
  * boolean moving block down
  */
  public boolean moveBlockDown() {
    // if the current block can no longer move down, move the block to the background and spawn new moving block
    if (checkBottom() == false) {
        
      return false;
    }
      
    // check if block can move down and not out of bounds
    block.moveDown();
    repaint();

    return true;
      
  }

  /*
  * moves block right
  */    
  public void moveBlockRight() {
      
    if( block == null ) return;
    if( !checkRight() ) return;
    
    block.moveRight();
    repaint();
      
  }

  /*
  * moves block left
  */    
  public void moveBlockLeft() {
      
    if( block == null ) return;
    if( !checkLeft() ) return;
    
    block.moveLeft();
    repaint();
      
  }

  /*
  * drops block 
  */    
  public void dropBlock() {
      
    if( block == null ) return;
    while( checkBottom() )
      {
        block.moveDown();
      }
    
    repaint();
      
  }

  /*
  * rotates block
  */   
  public void rotateBlock() {
    
    if( block == null ) return;
    block.rotate();

    if(block.getLeftEdge() < 0) block.setX(0);
    if(block.getRightEdge() >= gridColumns) block.setX( gridColumns - block.getWidth() );
    if(block.getBottomEdge() >= gridRows) block.setY ( gridRows = block.getHeight() );
    
    repaint(); 
      
  }

    
 /*
  * CHECKS FOR OUT OF BOUNDS ON THE GRID - Bottom, Left & Right
  */
  private boolean checkBottom() {
    if( block.getBottomEdge() == gridRows) {
      return false;
        
    }
    
    int[][]shape = block.getShape();
    int w = block.getWidth();
    int h = block.getHeight();
    
    // prevents blocks from stacking vertically
    // If at least one of the cells below block is NOT null, stop the block from moving
    for(int col = 0; col < w; col++) {
      for(int row = h - 1; row >= 0; row--) {
        if(shape[row][col] != 0) {
        int x = col + block.getX();
        int y = row + block.getY() + 1; // encorporates vertical bounds
            
        if(y < 0) break; // terminates when y is negative or out of bounds
        if(background[y][x] != null) return false;
        break;
            
        }
      }
    }
    return true;
  }

  /*
  * checks left 
  */    
  private boolean checkLeft() {
    if( block.getLeftEdge() == 0) return false; // x value starts

    int[][]shape = block.getShape();
    int w = block.getWidth();
    int h = block.getHeight();
    
    // prevents blocks from stacking vertically
    for(int row = 0; row < h; row++) {
      for(int col = 0; col < w; col++) {
        if(shape[row][col] != 0) {
        int x = col + block.getX() - 1; // left bounds
        int y = row + block.getY();
        if(y < 0) break; // terminates when y is negative or out of bounds
        if(background[y][x] != null) return false;
        break;
        }
      }
    }
    return true;
  }

  /*
  * checks right
  */    
  private boolean checkRight() {
    if( block.getRightEdge() == gridColumns ) return false; // x value ends
    
    int[][]shape = block.getShape();
    int w = block.getWidth();
    int h = block.getHeight();
    
    // prevents blocks from stacking vertically
    for(int row = 0; row < h; row++) {
      for(int col = w - 1; col >= 0; col--) {
        if(shape[row][col] != 0) {
        int x = col + block.getX() + 1; // right bounds
        int y = row + block.getY();
        if(y < 0) break; // terminates when y is negative or out of bounds
        if(background[y][x] != null) return false;
        break;
        }
      }
    }
    return true;
  }

  /*
  * clears lines
  */   
  public int clearLines() {
    boolean lineFilled;
    int linesCleared = 0;
    
    for (int r = gridRows - 1; r >= 0; r--) {
      lineFilled = true;
        
        for(int c = 0; c < gridColumns; c++) {
            if(background[r][c] == null) { // if one of the block in line is not filled in, line is not complete and is ignored
            
              lineFilled = false;
              break;
            }
          }
      
      if(lineFilled) { // if line is complete, set the block to null color and repaint
        linesCleared++;
        clearLine(r);
        shiftDown(r);
        clearLine(0);

        r++; // to clear multiple lines and updates properly

        repaint();
      }
    }
      
    return linesCleared;
  }

  /*
  * clears lines
  * @param integer r
  */   
  private void clearLine(int r) {
     for(int i = 0; i < gridColumns; i++) {
          background[r][i] = null;
        }
  }

  /*
  * shifts downwards
  * @param integer r
  */ 
  private void shiftDown(int r) {
    for(int row = r; row > 0; row--) {
      for(int col = 0; col < gridColumns; col++) {
        background[row][col] = background[row - 1][col];
      }
    }
  }
  // Make blocks that have dropped to the bottom unmovable by adding them to the background. They are all just a clump of colored/non-colored blocks at the bottom.

  /*
  * moves blocks to background
  */ 
  public void moveBlockToBackground()
  {
    int[][] shape = block.getShape();
    int h = block.getHeight();
    int w = block.getWidth();

    int xPos = block.getX();
    int yPos = block.getY();

    Color color = block.getColor();

    for (int r = 0; r < h; r++) {
      for (int c = 0; c < w; c++) {
         if(shape[r][c] == 1) {
           background[r + yPos][c + xPos] = color;
         }
      }
    }
  }

    
  // bug - blocks can still rotate out of bounds
  // draws Tetris block - foreground

  /*
  * draws the block
  * @param graphics g
  */
  private void drawBlock(Graphics g) {
    // Storing Variables locally
    int h = block.getHeight();
    int w = block.getWidth();
    Color c = block.getColor();
    int[][] shape = block.getShape();

    // for every gridcell, if grid value is 1, fill with color to get shape of block
    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
          if (shape [row][col] == 1) { 
            // colored block
            int x = (block.getX() + col) * gridCellSize;
            int y = (block.getY() + row) * gridCellSize;
        
        drawGridSquare(g, c, x, y);
          }
        }
    }   
  }

  /*
  * draws background
  * @param graphics g 
  */    
  private void drawBackground(Graphics g) {
    Color color;
    
    for (int r = 0; r < gridRows; r++) {
      for (int c = 0; c < gridColumns; c++) {
        color = background[r][c];

        if(color != null) {
          int x = c * gridCellSize;
          int y = r * gridCellSize;

          drawGridSquare(g, color, x, y);
        }
      }
    }
  }

    
  /*
  * redraws the grid every time
  */
  private void drawGridSquare(Graphics g, Color color, int x, int y) {
    g.setColor(color);
    g.fillRect(x, y, gridCellSize, gridCellSize);
    // grid lines
    g.setColor(Color.black);
    g.drawRect(x, y, gridCellSize, gridCellSize);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g); 
    
  //   //GRID LINES - removed
  // for (int y = 0; y < gridRows; y++){
  //   for (int x = 0; x < gridColumns; x++){
  //     g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
  //   }
  // }  
    drawBackground(g);
    drawBlock(g);
  }
}