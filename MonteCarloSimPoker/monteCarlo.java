//          Name : Eric Kwon
//          Poker Hands - Monte Carlo Simulation
//          Objective : Run 1M instances of card deal simulation
//          Find probabilities of either Two Pairs or Four of a Kind
//
//          Associated file : cards.java

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class monteCarlo {

    // Local class members for statistics
    static int dealt_total;
    static double tp_average;
    static double fok_average;
    static double tp_total;
    static double fok_total;

    // Class member initialization
    public static void setDeals (int x) {
        dealt_total = x;
        tp_average = 0;
        fok_average = 0;
        tp_total = 0;
        fok_total = 0;
    }

    // Method to create a new deck of 52 cards, and randomly pick 5 among the cards
    public static HashMap<String, Integer> pickFive () {
        cards[] wholeDeck = new cards[52];
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

    // Initialize the cards from 2 of Diamonds to Ace of Spades
        for (int x = 0; x < 4; x++) {
            int cardNumber = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    wholeDeck[cardNumber] = new cards(i, j);
                    cardNumber++;
                }
            }
        }

        // Initializing array list for unbiased shuffling using Collections.shuffle()
        for (int i = 0; i < 52; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        // For each random card that is picked, place them onto a hash map
        // Key - rank of the card
        // Stored value - occurrences of each card rank
        for (int i = 0; i < 5; i++) {
            if (map.containsKey(wholeDeck[list.get(i)].whatRank())) {
                map.put(wholeDeck[list.get(i)].whatRank(), map.get(wholeDeck[list.get(i)].whatRank()) + 1);
            }
            else {
                map.put(wholeDeck[list.get(i)].whatRank(), 1);
            }

        }
        return map;
    }

    // Method to determine whether the dealt hand is two pairs of a four of a kind
    public static int tp_or_fok (HashMap<String, Integer> x) {

        // Integer value to keep track of 2 separate instance of pairs happening
        int tp_counter = 0;

        // If any of the hash map keys return a value of 4 -- indicates four of a kind
        for (String key : x.keySet()) {
            if (x.get(key) == 4)
                return 1;
        // If any of the hash map keys return a value of 2 -- indicates a PAIR, but not yet a two pair
            if (x.get(key) == 2)
                tp_counter++;
        }

        // If tp_counter is 2 at the end of the loop -- indicates a two pair since 2 separate pairs were found
        if (tp_counter == 2)
            return 2;
        else
            return 0;
    }

    public static void getAverage() {
        tp_average = tp_total / dealt_total;
        fok_average = fok_total / dealt_total;
    }

    public static void outputResults() {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter ("output.txt");
            bw = new BufferedWriter (fw);

            DecimalFormat f = new DecimalFormat("###0.0000000%");
            DecimalFormat f2 = new DecimalFormat("###0.00");
            bw.write ("Total # of Hands Dealt       : " + f2.format(dealt_total));
            bw.newLine();
            bw.write ("Total # of Two Pairs         : " + f2.format(tp_total));
            bw.newLine();
            bw.write ("Total # of Four of a Kind    : " + f2.format(fok_total));
            bw.newLine();
            bw.write ("Percentage of Two Pairs      : " + f.format(tp_average));
            bw.newLine();
            bw.write ("Percentage of Four of a Kind : " + f.format(fok_average));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main (String[] args) {
            setDeals(1000000);

            for (int i = 0 ; i < dealt_total ; i++) {
                HashMap<String, Integer> fiveCards = pickFive();
                int result = tp_or_fok(fiveCards);

                // Method will return integer 1 when four of a kind is found
                if (result == 1)
                    fok_total++;

                // Method will return integer 2 when a two pair is found
                if (result == 2)
                    tp_total++;
            }
            getAverage();
            outputResults();
        }
    }

