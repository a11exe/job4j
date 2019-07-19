package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;
import ru.job4j.tictactoe.player.Player;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Console game
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.07.2019
 */
public class ConsoleGame implements Game {

    private final TicTacToe ticTacToe;
    private final int tableSize;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final int numWins;
    private int oWins;
    private int xWins;
    private final PrintStream ps;
    private Player nextMovePlayer;
    private boolean exit;
    private final String newGameMsg = "Новая игра счет %s:%s (%s:%s). Игра идет до %s побед";
    private final String winnerMsg = "Победили %s! Начните новую игру";
    private final String winnerGameMsg = "Игра закончена со счетом %s:%s. Победили %s!";
    private final String xMsg = "крестики";
    private final String oMsg = "нолики";
    private final String drawMsg = "Ничья! Начните новую игру";
    private final String fieldPatternMsg = " %s ";
    private final String fieldBlankMsg = "_";


    /**
     * Default constructor
     *
     * @param ticTacToe - game.
     * @param numWins - num of total wins to win game.
     * @param firstPlayer - firstPlayer.
     * @param secondPlayer - secondPlayer.
     * @param os - output stream.
     */
    public ConsoleGame(TicTacToe ticTacToe, int numWins, Player firstPlayer, Player secondPlayer, OutputStream os) {
        this.ticTacToe = ticTacToe;
        this.numWins = numWins;
        this.firstPlayer = firstPlayer;
        this.firstPlayer.setMark(Mark.X);
        this.secondPlayer = secondPlayer;
        this.secondPlayer.setMark(Mark.O);
        this.tableSize = ticTacToe.getTableSize();
        this.nextMovePlayer = firstPlayer;
        this.exit = false;
        this.ps = new PrintStream(os);
    }

    /**
     * Start new game
     * reset field
     * show empty field
     */
    @Override
    public void newGame() {
        printMessage(newGameMsg, String.valueOf(xWins), String.valueOf(oWins), xMsg, oMsg, String.valueOf(numWins));
        ticTacToe.newGame();
        showTable();
    }

    /**
     * Show table
     */
    @Override
    public void showTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                Mark mark = ticTacToe.getMark(new Point(i, j));
                sb.append(String.format(fieldPatternMsg, (mark == null ? fieldBlankMsg : mark)));
            }
            sb.append(System.lineSeparator());
        }
        ps.println(sb);
    }

    /**
     * Get next move computer or player
     * one by one
     * show table after move
     * after move check if has winner
     */
    @Override
    public void next() {
        Point point = this.nextMovePlayer.move();
        printMessage(String.format(this.nextMovePlayer.getMoveMessage(), point.getX(), point.getY()));
        this.nextMovePlayer = this.nextMovePlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
        showTable();
        checkWinner();
    }

    /**
     * Check if somebody wins.
     * If has winner start new game
     * or stop game if one has wins equals numWins
     */
    private void checkWinner() {

        boolean hasWinner = false;
        if (Mark.O.equals(ticTacToe.checkWinner())) {
            printMessage(String.format(winnerMsg, oMsg));
            this.oWins++;
            hasWinner = true;
        } else if (Mark.X.equals(ticTacToe.checkWinner())) {
            printMessage(String.format(winnerMsg, xMsg));
            this.xWins++;
            hasWinner = true;
        }
        if (oWins >= numWins) {
            printMessage(winnerGameMsg, String.valueOf(xWins), String.valueOf(oWins), oMsg);
            exit = true;
        } else if (xWins >= numWins) {
            printMessage(winnerGameMsg, String.valueOf(xWins), String.valueOf(oWins), xMsg);
            exit = true;
        }

        if (hasWinner && !exit) {
            newGame();
        }

        if (!ticTacToe.isMovePossible()) {
            printMessage(drawMsg);
            newGame();
        }
    }


    private void printMessage(String pattern, String ... vars) {
        ps.println(String.format(pattern, vars));
    }

    @Override
    public boolean exit() {
        return exit;
    }
}
