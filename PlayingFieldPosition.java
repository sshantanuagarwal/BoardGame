package de.tabit.test.alexandria.engine.WorkingEngine;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.format;

public class PlayingFieldPosition {
    private final Random random = new Random();

    int number;
    String type;

    PlayingFieldPosition(int n) {
        this.number = n;
        this.type = null;
    }
    PlayingFieldPosition(int n, String t) {
        this.number = n;
        this.type = t;
    }

    boolean isBonusType() {
        return this.type == "bonus";
    }

    boolean isTrapType() {
        return this.type == "trap";
    }

    String printCurrPlayer(Player currPlayer) {
        int n = currPlayer.playerNum;
        return format("Player %d", n + 1);
    }

    public void selectTrap(Player currPlayer, ArrayList<Player> otherPlayers) {
        System.out.print("TRAP: ");
        if (currPlayer.hasJoker) {
            currPlayer.hasJoker = false;
            System.out.println(printCurrPlayer(currPlayer) + " has joker. Can skip the trap.");
            return;
        }

        int trap = random.nextInt(3);
        switch (trap) {
            case 0:
                System.out.println("Move back 2 positions");
                currPlayer.decrementPosition(2);
                break;
            case 1:
                System.out.println("Other move forward 2 positions");

                for (Player otherPlayer : otherPlayers) {
                    otherPlayer.incrementPosition(2);
                }
                break;
            case 2:
                System.out.println("Got a skip turn");
                currPlayer.skipTurn = true;
                break;
        }
    }

    public void selectBonus(Player currPlayer, ArrayList<Player> otherPlayers) {
        System.out.print("BONUS: ");
        int bonus = random.nextInt(3);
        switch (bonus) {
            case 0:
                System.out.println("Move forward 2 positions");
                currPlayer.incrementPosition(2);
                break;
            case 1:
                System.out.println("Others move back 2 positions");

                for (Player otherPlayer : otherPlayers) {
                    otherPlayer.decrementPosition(2);
                }
                break;
            case 2:
                System.out.println("Got a joker");

                currPlayer.hasJoker = true;
                break;
        }
    }
}
