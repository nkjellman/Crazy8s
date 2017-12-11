/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crazyeights;
/**
 *
 * @author Nick
 */

import java.awt.Color;

public class Displayer {
  
  private static final int NUM_COLS = 7;
  private static final int DISCARD_COL = 0;
  private static final int DECK_COL = 1;
  private static final Color GREEN = new Color(0, 100, 0);
  private static final Color BLUE = new Color(0, 0, 100);
  
  
  private Grid display;
  private Crazy8s game;
  
  //plays the game, beginning from the given game state
  public Displayer(Crazy8s game) {
    //create display
    this.game = game;
    int numUserRows = getNumUserRows();
    display = new Grid(numUserRows+2, NUM_COLS);
    display.setTitle("Crazy 8s");
    for (int row = 0; row < numUserRows+2; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        display.setColor(row, col, GREEN);
      }
    }
    
    display.setImage(0, DECK_COL, "back.gif");
    display.setImage(0, NUM_COLS-1, "newgame.gif");
  }
  
  public void playTurns() {
    updateDisplay();
    //main game loop
    while (true) {
      //pause 100 milliseconds to wait for user click
      try { Thread.sleep(100); } catch (Exception e) {}
      boolean userTurnIsOver = false;
      
      for (int col = 0; col < NUM_COLS; col++) {
        display.setColor(0, col, GREEN);
        display.setColor(1, col, GREEN);
      }
      
      //check if the user clicked
      int[] click = display.checkMouse();
      
      if (click != null) {
        int row = click[0];
        int col = click[1];
        
        //process click on (row, col)
        if (row == 0) {
          if (col == DECK_COL  && !game.isDone()) 
            game.clickedOnDeck();
          if (col == NUM_COLS-1) game.newGame();
        }
        else if (row > 1 && !game.isDone()) { 
          // clicked user card
          int cardIndex = NUM_COLS * (row-2) + col;
          if (cardIndex < game.getNumUserCards()) {
            userTurnIsOver = game.clickedOnCard(cardIndex);
          }
        }
        //update display to show user's play
        updateDisplay();
      }
      
      // computer plays
      if (userTurnIsOver && !game.isDone()) {
        boolean computerTurnIsOver = false;
        
        for (int col = 0; col < NUM_COLS; col++) {
          display.setColor(0, col, BLUE);
          display.setColor(1, col, BLUE);
        }
        while (!computerTurnIsOver) {
          computerTurnIsOver = game.playComputerTurn();
          // pause to see last play
          try { Thread.sleep(1200); } catch (Exception e) {}
          // update display to show computer's
          updateDisplay();
        }
        // ignore any user clicks while the computer was thinking
        display.checkMouse();
        
      }
    }
  }
  
  //updates the display to show correct box states and dice based on given game state
  private void updateDisplay() {
    String exposed = game.getExposedCardImage();
    display.setImage(0, DISCARD_COL, exposed);
    if (game.getNumDeckCards() == 0) {
      display.setImage(0, DECK_COL, null);
    }
    else {
      display.setImage(0, DECK_COL, "back.gif");
    }
    display.setImage(0, NUM_COLS-1, "newgame.gif");
    
    int numUser = game.getNumUserCards();
    for (int i = 0; i < getNumUserRows()*NUM_COLS; i++) {
      int row = i/NUM_COLS + 2;
      int col = i % NUM_COLS;
      if (i < numUser) {
        display.setImage(row, col, game.getUserCardImage(i));     
      }
      else {
        display.setImage(row, col, null);
      }    
    }
    display.setTitle(game.getStatusTitle());
  }
  
  private static int getNumUserRows() {
    // 33 is the most unplayable cards
    return 32 / NUM_COLS + 1;
  }
}
