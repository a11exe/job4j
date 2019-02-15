package ru.job4j.chess.firuges.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.02.2019
 */
public abstract class Knight extends Figure {

    public Knight(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isKnightMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[]{dest};
    }

    private boolean isKnightMove(Cell source, Cell dest) {
        int xSteps = Math.abs(source.x - dest.x);
        int ySteps = Math.abs(source.y - dest.y);
        return (xSteps != 0 && ySteps != 0 && xSteps + ySteps == 3);
    }

}
