package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;
import ru.job4j.tictactoe.logic.TicTacToe3T;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public class TicTacToe3TTest {

    @Test
    public void whenMakeMoveShouldMarkOnField() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(0, 0);
        ticTacToe.makeMove(point, Mark.X);
        assertThat(ticTacToe.getMark(point), is(Mark.X));
    }

    @Test(expected = WrongMoveException.class)
    public void whenMakeMoveOnWrongPointShouldException() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(4, 0);
        ticTacToe.makeMove(point, Mark.X);
    }

    @Test(expected = WrongMoveException.class)
    public void whenMakeMoveOnBusyPointShouldException() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(0, 0);
        ticTacToe.makeMove(point, Mark.X);
        ticTacToe.makeMove(point, Mark.O);
    }

    @Test
    public void whenGetMarkShouldReturnMark() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(0, 0);
        ticTacToe.makeMove(point, Mark.X);
        assertThat(ticTacToe.getMark(point), is(Mark.X));
    }

    @Test(expected = WrongMoveException.class)
    public void whenGetMarkWrongPointShouldException() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(5, 0);
        ticTacToe.getMark(point);
    }

    @Test
    public void whenOneStepShouldMovePossible() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(0, 0);
        ticTacToe.makeMove(point, Mark.X);
        assertTrue(ticTacToe.isMovePossible());
    }

    @Test
    public void whenStepsAsFieldSizeShouldMoveNotPossible() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point point = new Point(i, j);
                ticTacToe.makeMove(point, Mark.X);
            }
        }
        assertFalse(ticTacToe.isMovePossible());
    }

    @Test
    public void whenOVerticalShouldWinnerO() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        ticTacToe.makeMove(new Point(0, 0), Mark.X);
        ticTacToe.makeMove(new Point(0, 1), Mark.O);
        ticTacToe.makeMove(new Point(0, 2), Mark.O);
        ticTacToe.makeMove(new Point(1, 0), Mark.X);
        ticTacToe.makeMove(new Point(1, 1), Mark.X);
        ticTacToe.makeMove(new Point(1, 2), Mark.O);
        ticTacToe.makeMove(new Point(2, 1), Mark.X);
        ticTacToe.makeMove(new Point(2, 2), Mark.O);
        assertThat(ticTacToe.checkWinner(), is(Mark.O));
    }

    @Test
    public void whenGetTableSizeShouldFieldSize() {
        TicTacToe ticTacToe = new TicTacToe3T(5);
        assertThat(ticTacToe.getTableSize(), is(5));
    }

    @Test
    public void whenNewGameShouldBlankField() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Point point = new Point(0, 0);
        ticTacToe.makeMove(point, Mark.X);
        ticTacToe.newGame();
        assertNull(ticTacToe.getMark(point));
    }

    @Test
    public void whenNewGameWinnerShouldNull() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        ticTacToe.makeMove(new Point(0, 0), Mark.X);
        ticTacToe.makeMove(new Point(0, 1), Mark.X);
        ticTacToe.makeMove(new Point(0, 2), Mark.X);
        assertThat(ticTacToe.checkWinner(), is(Mark.X));
        ticTacToe.newGame();
        assertNull(ticTacToe.checkWinner());
    }


}