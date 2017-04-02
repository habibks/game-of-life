package net.habibksentini;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class GameOfLifeTest {

    @Test
    public void any_live_cell_with_fewer_than_two_live_neighbours_dies() {
        Set<Cell> cells = new HashSet<>();
        cells.add(new Cell(2, 2));
        cells.add(new Cell(3, 2));
        cells.add(new Cell(4, 2));
        cells.add(new Cell(5, 3));
        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCells(), containsInAnyOrder(new Cell(3, 2), new Cell(4, 2)));
    }

}