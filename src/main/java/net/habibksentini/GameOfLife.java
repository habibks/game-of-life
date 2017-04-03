package net.habibksentini;


import java.util.HashSet;
import java.util.Set;

public class GameOfLife {

    private Set<Cell> cells;

    public GameOfLife(Set<Cell> cells) {
        this.cells = cells;
    }

    public void nextGeneration() {
        Set<Cell> aliveCells;aliveCells = new HashSet<>();
        for (Cell cell : cells) {
            int neighboursNumber = countNeighbours(cell);
            if (neighboursNumber >= 2 && neighboursNumber <= 3) {
                aliveCells.add(cell);
            }
        }
        cells = aliveCells;
    }

    private int countNeighbours(Cell cell) {
        int neighboursNumber = 0;
        for (int i = cell.getX() - 1; i <= cell.getX() + 1; i++) {
            for (int j = cell.getY() - 1; j <= cell.getY() + 1; j++) {
                if (i == cell.getX() && j == cell.getY()) {
                    continue;
                }
                if (cells.contains(new Cell(i, j))) {
                    neighboursNumber++;
                }
            }
        }
        return neighboursNumber;
    }

    public Set<Cell> getCells() {
        return cells;
    }
}
