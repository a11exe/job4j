package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.*;


/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.02.2019
 */
public class LogicTest {

    @Test(expected = FigureNotFoundException.class)
    public void whenWhitePawnNotFoundOnCellWhenFigureNotFoundException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        PawnWhite pawnWhite = new PawnWhite(Cell.E6);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.move(Cell.E5, Cell.B7);
    }

    @Test
    public void whenWhitePawnRightMoveWhenNoException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        PawnWhite pawnWhite = new PawnWhite(Cell.E6);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.move(Cell.E6, Cell.E7);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhitePawnMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        PawnWhite pawnWhite = new PawnWhite(Cell.E6);
        PawnWhite pawnWhite2 = new PawnWhite(Cell.E7);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(pawnWhite2);
        logic.move(Cell.E6, Cell.E7);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhiteBishopMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        BishopWhite bishopWhite = new BishopWhite(Cell.E6);
        PawnWhite pawnWhite = new PawnWhite(Cell.D5);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(bishopWhite);
        logic.move(Cell.E6, Cell.B3);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhiteKingMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        KingWhite kingWhite = new KingWhite(Cell.E6);
        PawnWhite pawnWhite = new PawnWhite(Cell.E5);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(kingWhite);
        logic.move(Cell.E6, Cell.E5);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhiteKnightMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        KnightWhite knightWhite = new KnightWhite(Cell.E6);
        PawnWhite pawnWhite = new PawnWhite(Cell.C5);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(knightWhite);
        logic.move(Cell.E6, Cell.C5);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhiteQeenMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        QeenWhite qeenWhite = new QeenWhite(Cell.E6);
        PawnWhite pawnWhite = new PawnWhite(Cell.B3);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(qeenWhite);
        logic.move(Cell.E6, Cell.A2);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWhiteRookMoveOnOccupiedCellWhenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        RookWhite rookWhite = new RookWhite(Cell.E6);
        PawnWhite pawnWhite = new PawnWhite(Cell.C6);
        Logic logic = new Logic();
        logic.add(pawnWhite);
        logic.add(rookWhite);
        logic.move(Cell.E6, Cell.B6);
    }
    
}
