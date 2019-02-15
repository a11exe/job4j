package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.figures.Figure;
import ru.job4j.chess.firuges.figures.Rook;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookWhite extends Rook {

    public RookWhite(final Cell position) {
       super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
