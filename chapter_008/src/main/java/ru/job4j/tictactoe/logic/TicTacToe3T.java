package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.WrongMoveException;

import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * TicTacToe game implementation
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.07.2019
 */
public class TicTacToe3T implements TicTacToe {

    private final int size;
    private Mark[][] table;
    private Mark winner;
    private int numMove = 0;
    private final String wrongSizeMsg = "Введите правильные кординаты. Размер поля %n на %n";
    private final String fieldBusyMsg = "Введите правильные координаты. Ячейка (%n%n) уже занята";

    /**
     * Default constructor
     * @param size - field size
     */
    public TicTacToe3T(int size) {
        this.size = size;
        newGame();
    }

    /**
     * Start new game
     * reset field
     */
    @Override
    public void newGame() {
        this.numMove = 0;
        this.table = new Mark[this.size][this.size];
        this.winner = null;
    }

    @Override
    public int getTableSize() {
        return this.size;
    }

    /**
     * Check if somebody wins
     * if player has set marks on diagonal, horizontal, or vertical
     * @return win mark, null - no winner
     */
    @Override
    public Mark checkWinner() {
        if (isWinnerX()) {
            winner = Mark.X;
        } else if (isWinnerO()) {
            winner = Mark.O;
        }
        return winner;
    }

    /**
     * Make mark on filed
     * @param point - x,y
     * @param mark - mark
     * @throws WrongMoveException - if mark busy, x or y wrong.
     */
    @Override
    public void makeMove(Point point, Mark mark) throws WrongMoveException {
        Mark markPoint = getMark(point);
        if (markPoint != null) {
            throw new WrongMoveException(String.format(fieldBusyMsg, point.getX(), point.getY()));
        } else {
            table[point.getX()][point.getY()] = mark;
            numMove++;
        }
    }

    /**
     * Get mark on field
     * @param point - xy
     * @return mark on xy
     */
    @Override
    public Mark getMark(Point point) throws WrongMoveException {
        if (point.getX() >= table.length || point.getY() >= table.length) {
            throw new WrongMoveException(String.format(wrongSizeMsg, table.length));
        }
        return table[point.getX()][point.getY()];
    }

    /**
     * Check if total steps less field size.
     * @return move possible.
     */
    @Override
    public boolean isMovePossible() {
        return numMove < table.length * table.length;
    }

    /**
     * Check if O wins.
     * @return O wins.
     */
    private boolean isWinnerO() {
        return isWinner(Mark.O::equals);
    }

    /**
     * Check if X wins.
     * @return X wins.
     */
    private boolean isWinnerX() {
        return isWinner(Mark.X::equals);
    }

    /**
     * Check all horizontals, diagonals, verticals if somebody wins.
     * @param predicate - O or X equals for checking.
     * @return - has winner.
     */
    private boolean isWinner(Predicate<Mark> predicate) {
        boolean winner;
        // all diagonals
        winner = this.fillBy(predicate, this.table.length - 1, 0, -1, 1)
                || this.fillBy(predicate, 0, 0, 1, 1);
        // horizontal & vertical
        if (!winner) {
            Predicate<Integer> horizontal = (i->this.fillBy(predicate, 0,  i, 1, 0));
            Predicate<Integer> vertical = (i->this.fillBy(predicate,  i, 0, 0, 1));
            winner = IntStream.range(0, table.length)
                    .parallel()
                    .filter(i ->(horizontal.test(i) || vertical.test(i)))
                    .count() > 0;
        }
        return winner;
    }

    /**
     * Checking line algorithm.
     *
     * @param predicate - O or X equals for checking.
     * @param startX - start x
     * @param startY - start y
     * @param deltaX - increase x each step until table size
     * @param deltaY - increase y each step until table size
     * @return - line has equals marks predicate.
     */
    private boolean fillBy(Predicate<Mark> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Mark cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
