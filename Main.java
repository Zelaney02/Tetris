/**
 * @author Zelaney
 * @partner rchie144
 */

// import everything we might need
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;


// border and container
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
* Main class extends JFrame
*/ 
public class Main extends JFrame {
  // initiate all components needed for the game 
  
  // The Main Menu Panel
  JPanel panel = new JPanel();
  
  // score and level text
  JTextArea scoreDisplay = new JTextArea("\nScore: 0");
  JTextArea levelDisplay = new JTextArea("\nLevel: 0");

  // border outline and contents of panel
  Border br = BorderFactory.createLineBorder(Color.black);
  Container c = getContentPane();

  // initiate colors that might be used
  final Color DARK_BLUE = new Color(28, 7, 168);
  final Color DARK_GREEN = new Color(58, 86, 34);
  final Color GRAY = new Color(220, 220, 220);

// create a new game in instructions, "play again"
  /**
   * Creates a new game.
   */
  public Main() {
    setMainMenuPanel();
    
    this.add(panel);
    this.setSize(800, 600);
    this.setResizable(false);
    this.setTitle("Tetris");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
      
  }

// instructions menu panel
   /**
   * Changes the panel to display the Main Menu.
   */
  private void setMainMenuPanel() {
    panel.setLayout(null);
    panel.removeAll();
    
    // The "Start Game" button
    JButton startButton = new JButton();
    startButton.setText("START GAME");
    startButton.setSize(300, 50);
    startButton.setLocation(240, 200);
    startButton.setForeground(DARK_BLUE);
    startButton.setBackground(Color.WHITE);
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        setGamePanel(); // grid game panel 
      }
    });

      
   // The "Instructions" button
    JButton instructionsButton = new JButton();
    instructionsButton.setText("INSTRUCTIONS");
    instructionsButton.setSize(300, 50);
    instructionsButton.setLocation(240, 300);
    instructionsButton.setForeground(DARK_GREEN);
    instructionsButton.setBackground(Color.WHITE);
    instructionsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        setInstructionsPanel(); 
      }
    });

      
    // The "Highscore" button
    JButton highscoreButton = new JButton();
    highscoreButton.setText("HIGHSCORES");
    highscoreButton.setSize(300, 50);
    highscoreButton.setLocation(240, 400);
    highscoreButton.setForeground(DARK_BLUE);
    highscoreButton.setBackground(Color.WHITE);
    highscoreButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        setHighscorePanel(); // grid game panel
      }
    });

      
    // The Main Menu Title
    JLabel mainTitle = new JLabel("Welcome to Tetris");
    
    mainTitle.setSize(400, 50);
    mainTitle.setLocation(225, 50);
    mainTitle.setFont(new Font("Helvetica", Font.PLAIN, 36));
    mainTitle.setForeground(Color.WHITE);
    
    // The background is dark blue
    panel.setBackground(DARK_BLUE);
    
    // Adds the components to the panel
    panel.add(startButton);
    panel.add(instructionsButton);
    panel.add(highscoreButton);
    panel.add(mainTitle);
    panel.updateUI();
    this.add(panel);
  }

    
  /**
   * Changes the panel to display the board.
   */
  private void setGamePanel() {
    panel.removeAll();

    new GameFrame();

  }
    
  
 /**
   * Changes the panel to display the board.
   */
  private void setInstructionsPanel() {
    panel.removeAll();
    
    // The title
    JLabel instructionsTitle = new JLabel("How to Play Tetris");
    
    instructionsTitle.setSize(736, 100);
    instructionsTitle.setLocation(250, 0);
    instructionsTitle.setFont(new Font("Helvetica", Font.PLAIN, 36));
    instructionsTitle.setForeground(Color.WHITE);
    
    // The instructions
    JLabel instructions1 = new JLabel();
    JTextArea instructions2 = new JTextArea();
    JLabel instructions3 = new JLabel();
    JTextArea instructions4 = new JTextArea();
    
    instructions1.setText("Objective:");
    instructions2.setText("Complete lines, survive and achieve the highest score!");
    instructions3.setText("Gameplay: \n\n");
    instructions4.setText("Players must complete rows on the grid. \n");
    instructions4.setText(instructions4.getText() + "When a specific row is filled, it is deleted, and everything is shifted down. \n\n");
    instructions4.setText(instructions4.getText() + "Use the arrow keys to play the game. \n\n");
    instructions4.setText(instructions4.getText() + "Left & Right - side to side movement, Up - Rotate, Down - Hard drop. \n");
    
instructions4.setText(instructions4.getText() + "For every row completed, a point is received. \n");
    instructions4.setText(instructions4.getText() + "As the levels increase, the speed of the block also increases. \n");
    instructions4.setText(instructions4.getText() + "The game ends when the board is filled up. \n\n");
    instructions4.setText(instructions4.getText() + "Good Luck!");
    
    instructions1.setSize(736, 50);
    instructions1.setLocation(25, 70);
    instructions1.setBackground(Color.black);
    instructions1.setFont(new Font("Helvetica", Font.PLAIN, 25));
    instructions1.setForeground(Color.WHITE);
    
    instructions2.setSize(736, 30);
    instructions2.setLocation(25, 120);
    instructions2.setBackground(Color.black);
    instructions2.setFont(new Font("Helvetica", Font.PLAIN, 20));
    instructions2.setForeground(Color.WHITE);
    instructions2.setEditable(false);
    
    instructions3.setSize(736, 50);
    instructions3.setLocation(25, 150);
    instructions3.setBackground(Color.black);
    instructions3.setFont(new Font("Helvetica", Font.PLAIN, 25));
    instructions3.setForeground(Color.WHITE);
    
    instructions4.setSize(736, 300);
    instructions4.setLocation(25, 200);
    instructions4.setBackground(Color.black);
    instructions4.setFont(new Font("Helvetica", Font.PLAIN, 20));
    instructions4.setForeground(Color.WHITE);
    instructions4.setEditable(false);
    
    // The background is black
    panel.setBackground(Color.black);

    // The "Back" button
    JButton backButton = new JButton();
    backButton.setText("BACK");
    backButton.setSize(100, 50);
    backButton.setLocation(350, 515);
    backButton.setForeground(DARK_GREEN);
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        backButtonPressed(evt);
      }
    });

      
    // Adds the components to the panel
    panel.add(backButton);
    panel.add(instructionsTitle);
    panel.add(instructions1);
    panel.add(instructions2);
    panel.add(instructions3);
    panel.add(instructions4);
    panel.updateUI();
    this.add(panel);
  }
    

  private void setHighscorePanel() {
    panel.removeAll();
    
    // The title
    JLabel highscoreTitle = new JLabel("HIGHSCORES");
    
    highscoreTitle.setSize(300, 100);
    highscoreTitle.setLocation(250, 0);
    highscoreTitle.setFont(new Font("Helvetica", Font.PLAIN, 36));
    highscoreTitle.setForeground(Color.WHITE);

    // The background is dark green
    panel.setBackground(DARK_BLUE);

    // The "Back" button
    JButton backButton = new JButton();
    backButton.setText("BACK");
    backButton.setSize(100, 50);
    backButton.setLocation(350, 515);
    backButton.setForeground(DARK_GREEN);
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        backButtonPressed(evt);
      }
    });

    // Adds the components to the panel
    panel.add(backButton);
    panel.add(highscoreTitle);
    panel.updateUI();
    this.add(panel);
  }
  
    /**
   * The action listener for the "Back" button.
   * @param evt
   */
  public void backButtonPressed(ActionEvent evt) {
    setMainMenuPanel();
  }
  
  /**
   * The action listener for the "Start" button"
   * @param evt
   */
  public void startButtonPressed(ActionEvent evt) {
    setGamePanel();
  }
  
 /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Main game = new Main();
  }  
}