package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.02.2019
 */
public class FiguresTest {

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhitePawnWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.E6);
        pawnWhite.way(Cell.E6, Cell.B7);
    }

    @Test
    public void whenWhitePawnRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.E6);
        Cell[] way = pawnWhite.way(Cell.E6, Cell.E7);
        assertThat(way[0], is(Cell.E7));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackPawnWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.B8);
        pawnBlack.way(Cell.B8, Cell.H1);
    }

    @Test
    public void whenBlackPawnRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.E6);
        Cell[] way = pawnBlack.way(Cell.E6, Cell.E5);
        assertThat(way[0], is(Cell.E5));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackBishopWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.B8);
        bishopBlack.way(Cell.B8, Cell.H1);
    }

    @Test
    public void whenBlackBishopRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.B8);
        Cell[] way = bishopBlack.way(Cell.B8, Cell.H2);
        assertThat(way[way.length - 1], is(Cell.H2));
        assertThat(way.length, is(6));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhiteBishopWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        BishopWhite bishopWhite = new BishopWhite(Cell.B1);
        bishopWhite.way(Cell.B1, Cell.H6);
    }

    @Test
    public void whenWhiteBishopRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        BishopWhite bishopWhite = new BishopWhite(Cell.B1);
        Cell[] way = bishopWhite.way(Cell.B1, Cell.H7);
        assertThat(way[way.length - 1], is(Cell.H7));
        assertThat(way.length, is(6));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackKingWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.D4);
        kingBlack.way(Cell.D4, Cell.D6);
    }

    @Test
    public void whenBlackKingRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.D4);
        Cell[] way = kingBlack.way(Cell.D4, Cell.D5);
        assertThat(way[way.length - 1], is(Cell.D5));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhiteKingWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        KingWhite kingWhite = new KingWhite(Cell.D4);
        kingWhite.way(Cell.D4, Cell.B2);
    }

    @Test
    public void whenWhiteKingRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        KingWhite kingWhite = new KingWhite(Cell.D4);
        Cell[] way = kingWhite.way(Cell.D4, Cell.E5);
        assertThat(way[way.length - 1], is(Cell.E5));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackKnightWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.C4);
        knightBlack.way(Cell.C4, Cell.C6);
    }

    @Test
    public void whenBlackKnightRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.C4);
        Cell[] way = knightBlack.way(Cell.C4, Cell.B6);
        assertThat(way[way.length - 1], is(Cell.B6));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhiteKnightWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        KnightWhite knightWhite = new KnightWhite(Cell.C4);
        knightWhite.way(Cell.C4, Cell.C5);
    }

    @Test
    public void whenWhiteKnightRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        KnightWhite knightWhite = new KnightWhite(Cell.C4);
        Cell[] way = knightWhite.way(Cell.C4, Cell.A5);
        assertThat(way[way.length - 1], is(Cell.A5));
        assertThat(way.length, is(1));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackRookWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.D4);
        rookBlack.way(Cell.D4, Cell.B3);
    }

    @Test
    public void whenBlackRookRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.D4);
        Cell[] way = rookBlack.way(Cell.D4, Cell.A4);
        assertThat(way[way.length - 1], is(Cell.A4));
        assertThat(way.length, is(3));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhiteRookWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        RookWhite rookWhite = new RookWhite(Cell.D4);
        rookWhite.way(Cell.D4, Cell.E5);
    }

    @Test
    public void whenWhiteRookRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        RookWhite rookWhite = new RookWhite(Cell.D4);
        Cell[] way = rookWhite.way(Cell.D4, Cell.D1);
        assertThat(way[way.length - 1], is(Cell.D1));
        assertThat(way.length, is(3));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackQeenWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        QeenBlack qeenBlack = new QeenBlack(Cell.D4);
        qeenBlack.way(Cell.D4, Cell.G6);
    }

    @Test
    public void whenBlackQeenRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        QeenBlack qeenBlack = new QeenBlack(Cell.D4);
        Cell[] way = qeenBlack.way(Cell.D4, Cell.A1);
        assertThat(way[way.length - 1], is(Cell.A1));
        assertThat(way.length, is(3));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenWhiteQeenWrongWayWhenImpossibleMoveException() throws ImpossibleMoveException {
        QeenWhite qeenWhite = new QeenWhite(Cell.D4);
        qeenWhite.way(Cell.D4, Cell.C2);
    }

    @Test
    public void whenWhiteQeenRightWayWhenWayContainsDest() throws ImpossibleMoveException {
        QeenWhite qeenWhite = new QeenWhite(Cell.D4);
        Cell[] way = qeenWhite.way(Cell.D4, Cell.D1);
        assertThat(way[way.length - 1], is(Cell.D1));
        assertThat(way.length, is(3));
    }
    
}
