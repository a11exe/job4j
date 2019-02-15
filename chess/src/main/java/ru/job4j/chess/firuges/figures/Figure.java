package ru.job4j.chess.firuges.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

public abstract class Figure {

    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell position() {
        return this.position;
    }

    public abstract Cell[] way(Cell source, Cell dest)  throws ImpossibleMoveException;

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    public abstract Figure copy(Cell dest);

    boolean isStraight(Cell source, Cell dest) {
        return source.x == dest.x || source.y == dest.y;
    }

    boolean isDiagonal(Cell source, Cell dest) {
        return  Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }

}
