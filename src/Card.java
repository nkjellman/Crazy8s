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

public class Card {
  
  
  private int value;
  private int suit;
  private String image;
  
  public static final int CLUBS = 0;
  public static final int DIAMONDS = 1;
  public static final int HEARTS = 2;
  public static final int SPADES = 3;
  
  public Card(int cardSuit, int cardValue) {
    //to be completed
    //must assign values to all three instance variables
    //note that the image variable is name of the gif file provided, e.g.:
    // 1c.gif has the image of ace of clubs
    // 11s.gif has the image of jack of spades
    String s = "";
    suit = cardSuit;
    value = cardValue;
    switch (suit) {
      case 0:
        s = "c";
        break;
      case 1:
        s = "d";
        break;
      case 2:
        s = "h";
        break;
      case 3:
        s = "s";
        break;
    }
    image = value + s + ".gif";
  }
  
  public int getValue() {
    return value;
  }
  
  public int getSuit() {
    return suit;
  }
  
  public String getImage() {
    return image;
  }
  
  public String toString() {
    // complete this method 
    // this should return the name of each card, such as 
    //    “8 of spades�?
    // “jack of diamonds�?
    // “ace of clubs�?
    String s = "";
    switch (suit) {
      case 0:
        s = "of clubs";
        break;
      case 1:
        s = "of dimonds";
        break;
      case 2:
        s = "of hearts";
        break;
      case 3:
        s = "of spades";
        break;
    }
    return value + " " + s;
  }
}
