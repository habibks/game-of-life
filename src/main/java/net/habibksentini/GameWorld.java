package net.habibksentini;

import java.util.Iterator;

import static net.habibksentini.Status.DIE;

public class GameWorld implements Iterable <Cell>{

    public final static int ROW_NUMBER = 5;
    public final static int COLUMN_NUMBER = 5;
    private Cell[][] cells;

    public GameWorld() {
        cells = new Cell[ROW_NUMBER][COLUMN_NUMBER];
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                cells[i][j] = (new Cell(i, j, DIE));
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public GameWordIterator iterator() {
        return new GameWordIterator(cells, COLUMN_NUMBER, ROW_NUMBER);
    }
}
