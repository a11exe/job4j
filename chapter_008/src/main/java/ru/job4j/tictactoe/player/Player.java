package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public interface Player {

    Point move();

    String getMoveMessage();

    void setMark(Mark mark);

}
