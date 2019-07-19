package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;

/**
 * Player player
 * using random move algoritm
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public class ComputerRandom implements Player {

    private final TicTacToe ticTacToe;
    private final int tableSize;
    private final String name;
    private Mark mark;

    public ComputerRandom(TicTacToe ticTacToe, String name) {
        this.ticTacToe = ticTacToe;
        this.tableSize = ticTacToe.getTableSize();
        this.name = name;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Make move on field using random algoritm
     * @return point of move
     */
    @Override
    public Point move() {
        Point point = null;
        boolean moved = false;
        while (!moved) {
            Integer x = (int) (Math.random() * tableSize);
            Integer y = (int) (Math.random() * tableSize);
            point = new Point(x, y);
            if (ticTacToe.getMark(point) == null) {
                ticTacToe.makeMove(point, getMark());
                moved = true;
            }
        }
        return point;
    }

    @Override
    public String getMoveMessage() {
        return name + " сделал ход: %s%s";
    }
}
