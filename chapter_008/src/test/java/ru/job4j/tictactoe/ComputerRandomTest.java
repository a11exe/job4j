package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;
import ru.job4j.tictactoe.logic.TicTacToe3T;
import ru.job4j.tictactoe.player.ComputerRandom;
import ru.job4j.tictactoe.player.Player;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 10.07.2019
 */
public class ComputerRandomTest {

    @Test
    public void whenCompMoveShouldPointInEdgeFiledSize() {

        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Point point = computer.move();
        assertTrue(point.getX() >= 0 && point.getX() < 3);
        assertTrue(point.getY() >= 0 && point.getY() < 3);

    }


}