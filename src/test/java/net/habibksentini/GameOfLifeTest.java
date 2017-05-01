package net.habibksentini;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.habibksentini.Status.LIVE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class GameOfLifeTest {

    @Test
    public void any_live_cell_with_fewer_than_two_live_neighbours_dies() {
        Set<Cell> cells = new HashSet<>();
        cells.add(new Cell(2, 2, LIVE));
        cells.add(new Cell(3, 2, LIVE));
        cells.add(new Cell(4, 2, LIVE));
        cells.add(new Cell(5, 3, LIVE));
        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCells(), containsInAnyOrder(new Cell(3, 2, LIVE), new Cell(4, 2, LIVE)));
    }

    @Test
    public void any_live_cell_with_more_than_three_live_neighbours_dies() {
        Set<Cell> cells = new HashSet<>();
        cells.add(new Cell(2, 2, LIVE));
        cells.add(new Cell(2, 3, LIVE));
        cells.add(new Cell(3, 2, LIVE));
        cells.add(new Cell(3, 3, LIVE));
        cells.add(new Cell(4, 2, LIVE));
        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCells(), containsInAnyOrder(new Cell(2, 2, LIVE), new Cell(2, 3, LIVE), new Cell(4, 2, LIVE)));
    }

    @Test
    public void any_live_cell_with_two_or_three_live_neighbours_lives() {

        Set<Cell> cells = new HashSet<>();
        cells.add(new Cell(2, 2, LIVE));
        cells.add(new Cell(2, 3, LIVE));
        cells.add(new Cell(3, 2, LIVE));
        cells.add(new Cell(3, 3, LIVE));
        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCells(), containsInAnyOrder(new Cell(2, 2, LIVE), new Cell(2, 3, LIVE), new Cell(3, 2, LIVE), new Cell(3, 3, LIVE)));
    }

}
