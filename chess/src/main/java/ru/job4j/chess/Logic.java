package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.figures.Figure;

import java.util.stream.IntStream;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public void move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {

        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException();
        }
        Cell[] steps = this.figures[index].way(source, dest);

        if (isWayOccupied(steps)) {
            throw new OccupiedWayException();
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            this.figures[index] = this.figures[index].copy(dest);
        }
    }

    private boolean isWayOccupied(Cell[] steps) {
        boolean wayOccupied = false;
        for (Cell step : steps) {
            if (findBy(step) != -1) {
                wayOccupied = true;
                break;
            }
        }
        return wayOccupied;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
//        int rst = -1;
//        for (int index = 0; index != this.figures.length; index++) {
//            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
//                rst = index;
//                break;
//            }
//        }
//        return rst;
        return IntStream.range(0, this.figures.length - 1)
                .filter(i->this.figures[i] != null)
                .filter(i->this.figures[i].position().equals(cell))
                .findFirst()
                .orElse(-1);
    }
}
