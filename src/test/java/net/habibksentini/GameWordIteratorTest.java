package net.habibksentini;


import org.junit.Before;
import org.junit.Test;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.habibksentini.Status.DIE;
import static net.habibksentini.Status.LIVE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameWordIteratorTest{

    private final int columnNumber = 5;
    private final int rowNumber = 5;
    private GameWordIterator gameWorldIterator;

    @Before
    public void init(){
        Cell[][] cells = new Cell[5][5];
        for (int i = 0; i< columnNumber; i++){
            for (int j = 0; j< rowNumber; j++){
                cells[i][j] = new Cell(i, j, DIE);
            }
        }
        gameWorldIterator = new GameWordIterator(cells, columnNumber, rowNumber);
    }

    @Test
    public void iterator_should_return_first_element_when_next_is_called_at_the_first_time() {
        Cell cell = gameWorldIterator.next();

        assertThat(cell.getX(), is(0));
        assertThat(cell.getY(), is(0));
    }


    @Test
    public void iterator_should_return_next_element_in_the_same_row_when_current_cell_is_not_in_last_column() {
        gameWorldIterator.setRowCursor(0);
        gameWorldIterator.setColumnCursor(1);

        gameWorldIterator.next();
        Cell cell = gameWorldIterator.next();

        assertThat(cell.getX(), is(2));
        assertThat(cell.getY(), is(0));
    }

    @Test
    public void iterator_should_return_next_element_in_the_next_row_when_current_cell_is_in_last_column() {
        gameWorldIterator.setRowCursor(1);
        gameWorldIterator.setColumnCursor(columnNumber - 1);

        gameWorldIterator.next();
        Cell cell = gameWorldIterator.next();

        assertThat(cell.getX(), is(0));
        assertThat(cell.getY(), is(2));
    }

    @Test
    public void iterator_should_return_number_of_alive_neighbours_when_current_cell_is_in_the_middle_of_the_game_word() {
        gameWorldIterator.setRowCursor(2);
        Cell[][] cells = gameWorldIterator.getCells();
        gameWorldIterator.setColumnCursor(2);
        cells[1][2].setStatus(LIVE);
        cells[3][2].setStatus(LIVE);
        cells[2][1].setStatus(LIVE);

        gameWorldIterator.next();
        int aliveNeighbours = gameWorldIterator.countAliveNeighbours();

        assertThat(aliveNeighbours, is(3));
    }

    @Test
    public void iterator_should_return_number_of_alive_neighbours_of_current_cell_when_current_cell_is_in_the_bottom_right_corner() {
        gameWorldIterator.setRowCursor(rowNumber - 1);
        gameWorldIterator.setColumnCursor(columnNumber -1);
        Cell[][] cells = gameWorldIterator.getCells();
        cells[4][3].setStatus(LIVE);
        cells[3][4].setStatus(LIVE);

        gameWorldIterator.next();
        int aliveNeighbours = gameWorldIterator.countAliveNeighbours();

        assertThat(aliveNeighbours, is(2));
    }

    @Test
    public void iterator_should_return_number_of_alive_neighbours_of_current_cell_when_current_cell_is_in_the_top_right_corner() {
        gameWorldIterator.setRowCursor(rowNumber - 1);
        gameWorldIterator.setColumnCursor(0);
        Cell[][] cells = gameWorldIterator.getCells();
        cells[1][3].setStatus(LIVE);

        gameWorldIterator.next();
        int aliveNeighbours = gameWorldIterator.countAliveNeighbours();

        assertThat(aliveNeighbours, is(1));
    }

    @Test
    public void iterator_should_return_number_of_alive_neighbours_of_current_cell_when_current_cell_is_in_the_top_left_corner() {
        gameWorldIterator.setRowCursor(0);
        gameWorldIterator.setColumnCursor(0);
        Cell[][] cells = gameWorldIterator.getCells();
        cells[0][1].setStatus(LIVE);
        cells[1][1].setStatus(LIVE);

        gameWorldIterator.next();
        int aliveNeighbours = gameWorldIterator.countAliveNeighbours();

        assertThat(aliveNeighbours, is(2));
    }


    @Test
    public void iterator_should_return_number_of_alive_neighbours_of_current_cell_when_current_cell_is_in_the_bottom_left_corner() {
        gameWorldIterator.setRowCursor(rowNumber - 1);
        gameWorldIterator.setColumnCursor(0);
        Cell[][] cells = gameWorldIterator.getCells();
        cells[0][3].setStatus(LIVE);
        cells[1][3].setStatus(LIVE);

        gameWorldIterator.next();
        int aliveNeighbours = gameWorldIterator.countAliveNeighbours();

        assertThat(aliveNeighbours, is(2));
    }

}