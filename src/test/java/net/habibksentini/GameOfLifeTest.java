package net.habibksentini;


import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.deepEquals;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.stream;
import static net.habibksentini.GameWorld.COLUMN_NUMBER;
import static net.habibksentini.GameWorld.ROW_NUMBER;
import static net.habibksentini.Status.DIE;
import static net.habibksentini.Status.LIVE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class GameOfLifeTest {

    private GameOfLife gameOfLife;

    @Before
    public void init() {
        gameOfLife = new GameOfLife();
    }

    @Test
    public void any_live_cell_with_fewer_than_two_live_neighbours_dies() {
        Cell[][] cells = gameOfLife.getGameWorld().getCells();
        cells[2][2] = new Cell(2, 2, LIVE);
        cells[3][2] = new Cell(3, 2, LIVE);
        cells[4][2] = new Cell(4, 2, LIVE);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife, hasLiveCells(
                new Cell(3, 2, LIVE),
                new Cell(4, 2, LIVE)));
    }

    @Test
    public void any_live_cell_with_more_than_three_live_neighbours_dies() {
        Cell[][] cells = gameOfLife.getGameWorld().getCells();
        cells[2][2] = new Cell(2, 2, LIVE);
        cells[2][3] = new Cell(2, 3, LIVE);
        cells[3][2] = new Cell(3, 2, LIVE);
        cells[3][3] = new Cell(3, 3, LIVE);
        cells[4][2] = new Cell(4, 2, LIVE);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife, hasLiveCells(
                new Cell(2, 2, LIVE),
                new Cell(2, 3, LIVE),
                new Cell(4, 2, LIVE)));
    }

    @Test
    public void any_live_cell_with_two_or_three_live_neighbours_lives() {
        Cell[][] cells = gameOfLife.getGameWorld().getCells();
        cells[2][2] = new Cell(2, 2, LIVE);
        cells[2][3] = new Cell(2, 3, LIVE);
        cells[3][2] = new Cell(3, 2, LIVE);
        cells[3][3] = new Cell(3, 3, LIVE);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife, hasLiveCells(
                new Cell(2, 2, LIVE),
                new Cell(2, 3, LIVE),
                new Cell(3, 2, LIVE),
                new Cell(3, 3, LIVE)));
    }

    private Matcher<GameOfLife> hasLiveCells(Cell... expectedLiveCells) {
        return new BaseMatcher<GameOfLife>() {

            private Cell[][] expectedCells;

            @Override
            public void describeTo(Description description) {
                description.appendText("nextGeneration should return ").appendValue(deepToString(expectedCells));
            }

            @Override
            public boolean matches(Object gameOfLife) {
                Cell[][] resultCells = ((GameOfLife) gameOfLife).getGameWorld().getCells();
                expectedCells = createExpectedCells();
                return deepEquals(expectedCells, resultCells);
            }

            private Cell[][] createExpectedCells() {
                Cell[][] cells = createDieCells();
                stream(expectedLiveCells).forEach(cell -> cells[cell.getX()][cell.getY()].setStatus(LIVE));
                return cells;
            }

            private Cell[][] createDieCells() {
                Cell[][] cells = new Cell[COLUMN_NUMBER][ROW_NUMBER];
                for (int i = 0; i < COLUMN_NUMBER; i++){
                    for (int j =0; j < ROW_NUMBER; j++){
                        cells[i][j] = new Cell(i, j, DIE);
                    }
                }
                return cells;
            }
        };
    }

}
