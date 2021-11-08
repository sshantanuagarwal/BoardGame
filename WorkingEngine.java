package de.tabit.test.alexandria.engine.WorkingEngine;

import de.tabit.test.alexandria.engine.api.IAlexandriaGameEngine;

import java.util.ArrayList;
import java.util.Random;

public class WorkingEngine implements IAlexandriaGameEngine {
    private final Random random = new Random();

    int count;
    int numberOfPlayers;
    ArrayList<Player> players;
    PlayingField playingField;
    Player currPlayer;

    public String printPlayerPositions() {
        char[] result = new char[playingField.numFields];
        for(int i = 0; i < playingField.numFields; i++) {
            result[i] = '_';
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = players.get(i);
            if(player.position.number >= 0 && player.position.number < playingField.numFields)
                result[player.position.number] = (char)('0' + i);
        }
        return String.valueOf(result) + "\n";
    }

    public ArrayList<Player> getOtherPlayers() {
        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(currPlayer);
        return otherPlayers;
    }

    @Override
    public String startGame(Integer numberOfPlayers) {
        this.count = 0;
        this.numberOfPlayers = numberOfPlayers;

        players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(i));
        }

        // Initialize the playing field.
        playingField = new PlayingField();
        String output = playingField.printBoardInformation();
        output += "The game has started with " + numberOfPlayers + " players.";
        return output;
    }

    @Override
    public boolean gameIsRunning() {
        for (Player player : players) {
            if (player.position.number >= playingField.numFields) {
                System.out.println("Player " + player.playerNum + " has WON.\n");
                return false;
            }
        }
        return true;
    }

    @Override
    public String nextPlayer() {
        count++;
        currPlayer = players.get(random.nextInt(numberOfPlayers));
        return "Player " + currPlayer.playerNum;
    }

    @Override
    public String nextPlayerTurn(Integer input) {
        String output = "";
        if(currPlayer.skipTurn) {
            currPlayer.skipTurn = false;
            return "SKIPPED TURN\n";
        }
        currPlayer.incrementPosition(input);
        if (gameIsRunning()) {
            int currPosition = currPlayer.position.number;

            PlayingFieldPosition playingFieldPosition = playingField.playingFieldPositions[currPosition];
            if (playingFieldPosition.type != null) {
                if (playingFieldPosition.isTrapType()) {
                    playingFieldPosition.selectTrap(currPlayer, getOtherPlayers());
                }
                if (playingFieldPosition.isBonusType()) {
                    playingFieldPosition.selectBonus(currPlayer, getOtherPlayers());
                }
            }
        }

        output += count + ". " + playingField.printBoardBonusTrap();
        output += count + ". " + printPlayerPositions();
        output += "Moved to position " + currPlayer.position.number + "\n";
        return output;
    }

}
