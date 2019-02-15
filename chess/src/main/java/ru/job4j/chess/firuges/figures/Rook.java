package ru.job4j.chess.firuges.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2019
 */
public abstract class Rook extends Figure {

    public Rook(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int steps = Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y);
        Cell[] way = new Cell[steps];
        if (!isStraight(source, dest)) {
            throw new ImpossibleMoveException();
        }
        for (int i = 0; i < steps; i++) {
            int xx = source.x;
            int yy = source.y;
            if (source.x != dest.x) {
                xx = (source.x < dest.x) ? source.x + i + 1 : source.x - i - 1;
            }
            if (source.y != dest.y) {
                yy = (source.y < dest.y) ? source.y + i + 1 : source.y - i - 1;
            }
            way[i] = Cell.findByKey(xx, yy);
        }
        return way;
    }
}
