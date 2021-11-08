package de.tabit.test.alexandria.engine.WorkingEngine;


import java.util.ArrayList;
import java.util.Collections;

public class PlayingField {
    static final int numFields = 30;

    PlayingFieldPosition[] playingFieldPositions;

    public PlayingField() {
        playingFieldPositions =  new PlayingFieldPosition[numFields];
        ArrayList<String> types = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            types.add("trap");
            types.add("bonus");
        }
        for(int i = 0; i < 20; i++) {
            types.add(null);
        }
        Collections.shuffle(types);

        for(int i = 0; i < this.numFields; i++) {
            this.playingFieldPositions[i] = new PlayingFieldPosition(i, types.get(i) );
        }
    }

    public String printBoardInformation() {
        String output = "";
        for(int i = 0; i < numFields; i++) {
            if(playingFieldPositions[i].type != null)
                output += "Field " + playingFieldPositions[i].number + " has a " + playingFieldPositions[i].type + ".\n";
        }
        return output;
    }

    public String printBoardBonusTrap() {
        char[] result = new char[numFields];
        for(int i = 0; i < numFields; i++) {
            if (playingFieldPositions[i].type == null) {
                result[i] = '_';
            } else if (playingFieldPositions[i].isTrapType()) {
                result[i] = 'T';
            } else if (playingFieldPositions[i].isBonusType()) {
                result[i] = 'B';
            }
        }
        return String.valueOf(result) + "\n";
    }
}
