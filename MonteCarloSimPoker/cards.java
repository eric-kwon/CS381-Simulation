//          Name : Eric Kwon
//          Poker Hands - Monte Carlo Simulation
//
//          CARDS class : To simulate a deck of a card depending on array values
//          Suits and Ranks are separated into separate variables for possible future application

public class cards {

    // Integer class member variables
    private int cardSuit;
    private int cardRank;

    // String values will correspond to the integer value and help returning the appropriate card
    private String[] suits = {"D", "C", "H", "S"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    // Default constructor with parameters, x=suit & y=rank
    public cards (int x, int y) {
        cardSuit = x;
        cardRank = y;
    }

    // Each method will return either only the suit, only the rank, or both, usage should vary by case
    public String whatSuit() {
        return ( suits[cardSuit] );
    }
    public String whatRank() {
        return ( ranks[cardRank] );
    }
    public String whatCard() {
        return ( suits[cardSuit] + ranks[cardRank]);
    }
}
