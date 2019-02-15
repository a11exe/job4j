package ru.job4j.chess.firuges.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.02.2019
 */
public abstract class King extends Figure {

    public King(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isOneMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[]{dest};
    }

    private boolean isOneMove(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) <= 1 && Math.abs(source.y - dest.y) <= 1;
    }

}
