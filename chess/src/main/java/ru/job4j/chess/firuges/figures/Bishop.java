package ru.job4j.chess.firuges.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2019
 */
public abstract class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int steps = Math.abs(source.x - dest.x);
        Cell[] way = new Cell[steps];
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException();
        }
        for (int i = 0; i < steps; i++) {
            int xx = (source.x < dest.x) ? source.x + i + 1 : source.x - i - 1;
            int yy = (source.y < dest.y) ? source.y + i + 1 : source.y - i - 1;
            way[i] = Cell.findByKey(xx, yy);
        }
        return way;
    }
}
