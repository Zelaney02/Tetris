import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;

// border and container
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// For sleep error code
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* GameThread extends Thread
*/ 
public class GameThread extends Thread {
    // variables for easy access
    private GameArea ga;
    private GameFrame gf;
    
    private int score;
    private int level = 1;
    private int scorePerLevel = 50;

    private int pause = 1000;
    private int speedupPerLevel = 100;

    Border br = BorderFactory.createLineBorder(Color.black);
    
    public GameThread(GameArea ga, GameFrame gf) {
      this.ga = ga;
      this.gf = gf;
    }
    @Override
    public void run() {
      while(true) {
        ga.spawnBlock();
        // while current block can move down
        while (ga.moveBlockDown()) {
          try {
          
          Thread.sleep(pause);
        
        } catch(InterruptedException e) {
  //System.err.println(e.getMessage());
  Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
      }  
        }
        // GAME OVER
        // if the block is out of bounds vertically, the game is over
        if(ga.isBlockOutOfBounds()) {
          // ADD GAME OVER LABEL
          // JLabel gameOver = new JLabel("Game Over");
          // gameOver.setBounds(0, 100, 300, 75);
          // gameOver.setFont(new Font("Helvetica", Font.PLAIN, 20));
          // ga.setBorder(br);
    
          // gameOver.setVisible(true);
          // ga.add(gameOver);

          // ga.updateUI();
          System.out.println("Game Over");
          break;
        }
          
        ga.moveBlockToBackground();
        score += 3;
        gf.updateScore(score);
        // SCORE
        // score is num of lines completed
        score += ga.clearLines();
        gf.updateScore(score);

        int lvl = score/ scorePerLevel + 1;
        if(lvl > level)
        {
          level = lvl;
          gf.updateLevel(level);
          pause -= speedupPerLevel;
        }
    }
  }
}