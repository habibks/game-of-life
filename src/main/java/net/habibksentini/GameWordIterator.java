package net.habibksentini;


import java.util.Iterator;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.habibksentini.Status.LIVE;

public class GameWordIterator implements Iterator<Cell> {

    private final Cell[][] cells;
    private final int lastColumnIndex;
    private final int lastRowIndex;
    private Cell cell;
    private int columnCursor;
    private int rowCursor;


    public GameWordIterator(Cell[][] cells, int columnNumber, int rowNumber) {
        this.cells = cells;
        this.lastRowIndex = rowNumber - 1;
        this.lastColumnIndex = columnNumber - 1;
    }

    @Override
    public boolean hasNext() {
        return columnCursor <= lastColumnIndex && rowCursor <= lastRowIndex;
    }

    @Override
    public Cell next() {
        cell = cells[columnCursor][rowCursor];
        moveCursors();
        return cell;
    }

    private void moveCursors() {
        if (columnCursor == lastColumnIndex && rowCursor < lastRowIndex) {
            rowCursor++;
            columnCursor = 0;
        } else {
            columnCursor++;
        }
    }

    public int countAliveNeighbours() {
        int aliveNeighboursNumber = 0;
        int startColumnCursor = max(cell.getX() - 1, 0);
        int endColumnCursor = min(cell.getX() + 1, lastColumnIndex);
        int startRowCursor = max(cell.getY() - 1, 0);
        int endRowCursor = min(cell.getY() + 1, lastRowIndex);
        for (int i = startColumnCursor; i <= endColumnCursor; i++) {
            for (int j = startRowCursor; j <= endRowCursor; j++) {
                if (i == cell.getX() && j == cell.getX()) {
                    continue;
                } else if (cells[i][j].getStatus().equals(LIVE)) {
                    aliveNeighboursNumber++;
                }
            }
        }
        return aliveNeighboursNumber;
    }

    public void setColumnCursor(int columnCursor) {
        this.columnCursor = columnCursor;
    }

    public void setRowCursor(int rowCursor) {
        this.rowCursor = rowCursor;
    }

    public Cell[][] getCells() {
        return cells;
    }
}