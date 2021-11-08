package de.tabit.test.alexandria.engine.WorkingEngine;

public class Player {
    int playerNum;
    PlayingFieldPosition position;
    boolean hasJoker;
    boolean skipTurn;

    Player(int playerNum) {
        this.playerNum = playerNum;
        this.position = new PlayingFieldPosition(-1,null);
        this.hasJoker = false;
        this.skipTurn = false;
    }

    PlayingFieldPosition incrementPosition(int input) {
        position = new PlayingFieldPosition(this.position.number + input);
        return position;
    }

    PlayingFieldPosition decrementPosition(int input) {
        if(position.number > input) {
            position = new PlayingFieldPosition(this.position.number - input);
        }
        return position;
    }
}
