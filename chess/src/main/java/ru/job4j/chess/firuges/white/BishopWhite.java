package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.figures.Bishop;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite extends Bishop {

    public BishopWhite(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
