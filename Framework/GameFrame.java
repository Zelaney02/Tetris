// Note: when using images do "images/pause.png"

// import stuffs we might need
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// KeyBindings - Newer & more flexible than KeyListeners
import javax.swing.ActionMap;
import javax.swing.InputMap; 
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import static java.awt.event.KeyEvent.*;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

// border and container
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
* GameFrame extends JFrame
*/ 
public class GameFrame extends JFrame {
  
  private GameArea ga;
  
  JTextArea scoreDisplay = new JTextArea("\nScore: 0");
    // lines
  JTextArea levelDisplay = new JTextArea("\nLevel: 1");
  // Next piece label

  // The Main Menu Panel
  JPanel panel = new JPanel();
  Border br = BorderFactory.createLineBorder(Color.black);
  Container c = getContentPane();

  public GameFrame() {
    panel.removeAll();
    panel.setLayout(null);

    this.getContentPane().setLayout(null); // clear layout
    this.getContentPane().setBackground(Color.black); // change color of background
    
    // GAMEAREA IS ADDED
    ga = new GameArea(10);
    this.add( ga );
    
    // Basic Setup of Frame
    this.setTitle("Tetris");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(800, 600);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.getContentPane().setLayout(null); // clear layout

    // CHANGED TOP LEFT ICON
    ImageIcon image = new ImageIcon("images/tetris.png"); // create an ImageIcon
    this.setIconImage(image.getImage());  // change icon of frame
    
     // Displays the score
    scoreDisplay.setText("Score: 0");
    scoreDisplay.setBounds(0, 0, 300, 50);
    scoreDisplay.setFont(new Font("Helvetica", Font.PLAIN, 30));
    scoreDisplay.setEditable(false);

    // Displays the level
    levelDisplay.setText("Level: 1");
    levelDisplay.setBounds(0, 50, 300, 50);
    levelDisplay.setFont(new Font("Helvetica", Font.PLAIN, 30));
    levelDisplay.setEditable(false);
    
    // The "PAUSE" button
    JButton pauseButton = new JButton();
    //pauseButton.setText("PAUSE");
    pauseButton.setFocusable(true);
    pauseButton.setBounds(0, 150, 300, 75);
      // add icon
    ImageIcon pauseIcon = new ImageIcon("images/pause.png");
    pauseButton.setIcon(pauseIcon);
    pauseButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        System.out.println("Game Paused.");
        // if (ga.moveBlockDown() == true){
        //   moveDown() == false;
        //};
      }
    });
    
    // The "Replay" button
    JButton replayButton = new JButton();
    //replayButton.setText("REPLAY");
    replayButton.setFocusable(true);
    replayButton.setBounds(0, 250, 300, 75);
    replayButton.setForeground(Color.black);
      // add icon
    ImageIcon replayIcon = new ImageIcon("images/replay.png");
    replayButton.setIcon(replayIcon);

    replayButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
    replayButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        System.out.println("Replay Game.");
        //startGame();
      }
    });
    
    // The "Quit" button
    JButton quitButton = new JButton();
    //quitButton.setText("QUIT");
    quitButton.setFocusable(true);
    quitButton.setBounds(0, 350, 300, 75);
    quitButton.setForeground(Color.black);
     // add icon
    ImageIcon quitIcon = new ImageIcon("images/quit.png");
    quitButton.setIcon(quitIcon);
    
    quitButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
    
    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        System.out.println("Return to Main Menu.");
        //m.backButtonPressed(evt);
      }
    });
    
      initControls(); // Adds Keyboard Binding
      startGame(); // Starts Game
  
    // Adds the components to the panel
    panel.add(scoreDisplay);
    panel.add(levelDisplay);
    panel.add(pauseButton);
    panel.add(replayButton);
    panel.add(quitButton);
    
    //Panel 2 - not the game area
    panel.setBackground(Color.red);
    panel.setBounds(400, 50, 300, 450);
    
    panel.setBorder(br); // outlines the border of 2nd panel
    c.add(panel); // add components to frame
    setVisible(true);

    panel.updateUI();
  }
  
  // advanced keyboard binding
  private void initControls() { 
    InputMap im = this.getRootPane().getInputMap();
    ActionMap am = this.getRootPane().getActionMap();

    im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
    im.put(KeyStroke.getKeyStroke("LEFT"), "left");
    im.put(KeyStroke.getKeyStroke("UP"), "up");
    im.put(KeyStroke.getKeyStroke("DOWN"), "down");

    // Moves right when right key is pressed
    am.put("right", new AbstractAction(){
      @Override
      // adds action to actionMap
      public void actionPerformed(ActionEvent e) {
        ga.moveBlockRight();
      }
    });
      
    // Moves left when left key is pressed
    am.put("left", new AbstractAction(){
      @Override
      // adds action to actionMap
      public void actionPerformed(ActionEvent e) {
        ga.moveBlockLeft();
      }
    });

    // rotates block when up key is pressed
    am.put("up", new AbstractAction(){
      @Override
      // adds action to actionMap
      public void actionPerformed(ActionEvent e) {
        ga.rotateBlock();
      }
    });

    // Instant hard drop when down key is pressed
    am.put("down", new AbstractAction(){
      @Override
      // adds action to actionMap
      public void actionPerformed(ActionEvent e) {
        ga.dropBlock();
      }
    });
    
  }

  public void startGame() {
    new GameThread(ga, this).start();
  }

  public void updateScore(int score) {
    scoreDisplay.setText("Score: " + score);
  }
  public void updateLevel(int level) {
    levelDisplay.setText("Level: " + level);
  }

    /**
   * Changes the  game board to match the initial state of the game.
   */
// replay
  public void resetBoard() {
    new GameFrame();
    
    System.out.println("New Game");
      }
  
   /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable(){
      public void run() {
        new GameFrame().setVisible(true);
      }
    });
  }  
}
  