package net.habibksentini;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static net.habibksentini.GameWorld.COLUMN_NUMBER;
import static net.habibksentini.GameWorld.ROW_NUMBER;
import static net.habibksentini.Status.DIE;
import static net.habibksentini.Status.LIVE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameWordTest {

    private GameWorld gameWorld;

    @Before
    public void init() {
        gameWorld = new GameWorld();
    }

    @Test
    public void should_contains_only_die_cells_after_creation() {
        Stream<Cell> cellsStream = stream(gameWorld.getCells()).flatMap(Arrays::stream);
        cellsStream.forEach(cell -> assertThat(cell.getStatus(), is(DIE)));
    }


}
