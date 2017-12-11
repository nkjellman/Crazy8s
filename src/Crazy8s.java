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

import java.util.Random;
import java.util.ArrayList;

public class Crazy8s {
  
  private static final int NUM_CARDS_TO_DEAL = 7;
  private static final int WILDCARD = 8;
  
  private ArrayList<Card> computer;
  private ArrayList<Card> user;
  private ArrayList<Card> deck;
  private ArrayList<Card> discard;
  
  private Displayer display;
  
  public Crazy8s() {
    Random rand = new Random();
    display = new Displayer(this);
    deck = new ArrayList<Card>(52);
    user = new ArrayList<Card>(52);
    computer = new ArrayList<Card>(52);
    discard = new ArrayList<Card>(52);
    //Displayer desplay = new Displayer(this);
    //ArrayList<Integer> deck = new ArrayList<Integer>(51);
    
    for (int su = 0; su < 4; su++)
    {
      for(int val = 1; val < 14; val++)
      {
        deck.add(new Card(su, val));
      }
    }
    Card cardTemp = null;
    for(int i = 0; i < 52; i++)
    {
      int randVal = rand.nextInt(52);
      cardTemp = deck.get(i);
      deck.set(i, deck.get(randVal));
      deck.set(randVal, cardTemp);
    }
    
    for(int i = 0; i < 52; i++)
    {
      System.out.println(deck.get(i));
    }
  }
  
  
  public void startGame() {
    Card tempCard = null;
    for(int i = 0; i < 14; i++)
    {
      tempCard = deck.get(i);
      user.add(tempCard);
      deck.remove(i);
      tempCard = deck.get(i);
      computer.add(tempCard);
      deck.remove(i);
    }
    int i = 0;
    
    while(deck.get(i).getValue() == 8)
    {
      i++;
    }
    discard.add(deck.get(i));
    deck.remove(i);
    display.playTurns();
  }
  
  public String getExposedCardImage() {
    Card datCard = discard.get(discard.size()-1);
    return datCard.getImage();  
  }
  
  public String getUserCardImage(int index) {
    Card datCard = user.get(index);
    return datCard.getImage();  
  }
  
  public int getNumUserCards() {
    return user.size();  
  }
  public int getNumComputerCards() {
    return computer.size();  
  } 
  public int getNumDeckCards() {
    return deck.size();  
  }
  
  // returns whether finished turn
  public boolean clickedOnCard(int cardIndex) {
    // get card clicked on from user array (user's hand)
    Card clickedCard = user.get(cardIndex);
    // get card from top of discard array (0)
    Card discardCard = discard.get(discard.size()-1);
    // if card is valid
    if ((clickedCard.getSuit() == discardCard.getSuit()) || (clickedCard.getValue() == discardCard.getValue()))
    {
    //    move user card to discard pile
      discard.add(clickedCard);
      user.remove(cardIndex);
    //    if user card is an eight then
      if (clickedCard.getValue() == WILDCARD)
      {
        return false;
      }
      else {
        return true;
      }
    }
    // else return false
    return false;
  }
  public void clickedOnDeck() {
    // if deck is empty
    if (deck.size() == 0){
      //move cards from discard to deck
      for (int i = 0; i < 52; i++){
        deck.add(discard.get(i));
      }
      //shuffle the deck
    }
    //else move card from top of deck to user
  }
  
  public boolean playComputerTurn() {
    return true; //remove this line when you complete this method
  }
  
  
  public void newGame() {
    // to be completed
  }
  
  public String getStatusTitle() {
    return ""; //remove this line when you complete this method
  }
  
  public boolean isDone() {
    return false;//remove this line when you complete this method
  }
  
  public String toString() {
    return "E: " + discard.size() + " D: " + deck.size() + 
      " C: " + computer.size() + " U: " + user.size() + " T: " 
      +    (discard.size()  + deck.size() + 
            computer.size() +  user.size() );
  }
}
