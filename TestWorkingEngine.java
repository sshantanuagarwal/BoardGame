package de.tabit.test.alexandria.test;
import de.tabit.test.alexandria.engine.WorkingEngine.WorkingEngine;

import java.util.Random;

import static java.lang.String.format;

public class TestWorkingEngine {
    public static void main(String[] args) {
        Random random = new Random();
        WorkingEngine gameEngine = new WorkingEngine();
        System.out.println(gameEngine.startGame(2));
        while (gameEngine.gameIsRunning()) {
            int input = random.nextInt(6) + 1;
            System.out.println(format("The next player is %s. Please enter the dice number: " + input, gameEngine.nextPlayer()));

            String gameEngineInformation = gameEngine.nextPlayerTurn(input);
            System.out.println(gameEngineInformation);
        }
    }
}
