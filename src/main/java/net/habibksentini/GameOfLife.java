package net.habibksentini;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static net.habibksentini.Status.*;

public class GameOfLife {

    private GameWorld gameWorld;

    public GameOfLife() {
        gameWorld = new GameWorld();
    }

    public void nextGeneration() {
        GameWordIterator gameWordIterator = gameWorld.iterator();
        GameWorld nextGameWorld = new GameWorld();
        GameWordIterator nextGameWordIterator = nextGameWorld.iterator();
        while (gameWordIterator.hasNext()) {
            Cell currentCell = gameWordIterator.next();
            Cell nextCurrentCell = nextGameWordIterator.next();
            int neighboursNumber = gameWordIterator.countAliveNeighbours();
            if (currentCell.getStatus().equals(LIVE) && neighboursNumber >= 2 && neighboursNumber <= 3) {
                nextCurrentCell.setStatus(LIVE);
            }
        }
        gameWorld = nextGameWorld;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    @Override
    public String toString() {
        return "GameOfLife{" +
                "gameWorld =" + Arrays.deepToString(gameWorld.getCells()) +
                '}';
    }
}
