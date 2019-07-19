package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.game.ConsoleGame;
import ru.job4j.tictactoe.game.Game;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;
import ru.job4j.tictactoe.logic.TicTacToe3T;
import ru.job4j.tictactoe.player.ComputerRandom;
import ru.job4j.tictactoe.player.Human;
import ru.job4j.tictactoe.player.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 10.07.2019
 */
public class ConsoleGameTest {

    @Test
    public void whenNextComputerMoveShouldShowResultOnField() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", System.in, new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, computer, human, new PrintStream(outTest));
        game.next();
        assertThat(outTest.toString(), containsString("Random computer сделал ход:"));
        assertThat(outTest.toString(), containsString(Mark.X.toString()));
    }

    @Test
    public void whenNextPlayerMoveShouldShowResultOnField() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        Player human = new Human(ticTacToe, "Human",  new ByteArrayInputStream("11".getBytes()), new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, human, computer, new PrintStream(outTest));
        game.next();
        assertThat(outTest.toString(), containsString("Ваш ход. Введите координаты ячейки в формате (xy)"));
        assertThat(outTest.toString(), containsString(Mark.X.toString()));
    }

    @Test
    public void whenNextStepWinsShouldShowWinner() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        ticTacToe.makeMove(new Point(0, 0), Mark.O);
        ticTacToe.makeMove(new Point(0, 1), Mark.X);
        ticTacToe.makeMove(new Point(0, 2), Mark.X);
        ticTacToe.makeMove(new Point(1, 0), Mark.O);
        ticTacToe.makeMove(new Point(1, 1), Mark.O);
        ticTacToe.makeMove(new Point(1, 2), Mark.X);
        ticTacToe.makeMove(new Point(2, 0), Mark.X);
        ticTacToe.makeMove(new Point(2, 1), Mark.O);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", System.in, new PrintStream(outTest));

        Game game = new ConsoleGame(ticTacToe, 2, computer, human, new PrintStream(outTest));
        game.next();
        assertThat(outTest.toString(), containsString("Победили крестики! Начните новую игру"));
    }

    @Test
    public void whenShowTableShouldPrintTable() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", System.in, new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, computer, human, new PrintStream(outTest));
        game.showTable();
        String expected = ""
                + " _  _  _ "
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + System.lineSeparator();
        assertThat(outTest.toString(), is(expected));
    }

    @Test
    public void whenPalyerMoveShouldShowOnTable() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", new ByteArrayInputStream("11".getBytes()), new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, human, computer, new PrintStream(outTest));
        game.next();
        String expected = ""
                + " _  _  _ "
                + System.lineSeparator()
                + " _  X  _ "
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + System.lineSeparator();
        assertThat(outTest.toString(), containsString(expected));
    }

    @Test
    public void whenComputerMoveShouldShowOnTable() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", new ByteArrayInputStream("11".getBytes()), new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, human, computer, new PrintStream(outTest));
        game.next();
        String expected = Mark.X.toString();
        assertThat(outTest.toString(), containsString(expected));
    }

    @Test
    public void whenNewGameShouldBlankField() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", System.in, new PrintStream(outTest));
        Game game = new ConsoleGame(ticTacToe, 2, computer, human, new PrintStream(outTest));
        game.newGame();
        String expected = "Новая игра счет 0:0 (крестики:нолики). Игра идет до 2 побед"
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + " _  _  _ "
                + System.lineSeparator()
                + System.lineSeparator();
        assertThat(outTest.toString(), is(expected));
    }

    @Test
    public void whenStartGameExitShouldFalse() {
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        Player human = new Human(ticTacToe, "Human", System.in, System.out);

        Game game = new ConsoleGame(ticTacToe, 2, computer, human, System.out);
        assertFalse(game.exit());
    }

    @Test
    public void whenTotalWinsShouldExitTrue() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);

        String human1Steps = "00" + System.lineSeparator() + "01" + System.lineSeparator() + "02" + System.lineSeparator()
                        + "00" + System.lineSeparator() + "01" + System.lineSeparator() + "02" + System.lineSeparator()
                        + "00" + System.lineSeparator() + "01" + System.lineSeparator() + "02";
        Player human1 = new Human(ticTacToe, "Human", new ByteArrayInputStream(human1Steps.getBytes()), new PrintStream(outTest));

        String human2Steps = "10" + System.lineSeparator() + "11" + System.lineSeparator() + "20" + System.lineSeparator()
                + "10" + System.lineSeparator() + "11" + System.lineSeparator() + "20" + System.lineSeparator()
                + "10" + System.lineSeparator() + "11" + System.lineSeparator() + "20";
        Player human2 = new Human(ticTacToe, "Human", new ByteArrayInputStream(human2Steps.getBytes()), new PrintStream(outTest));

        Game game = new ConsoleGame(ticTacToe, 2, human1, human2, new PrintStream(outTest));
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        game.next();
        assertTrue(game.exit());
    }

    @Test
    public void whemComputerVsComputerShouldSomeWins() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer1 = new ComputerRandom(ticTacToe, "Random computer1");
        Player computer2 = new ComputerRandom(ticTacToe, "Random computer2");
        Game game = new ConsoleGame(ticTacToe, 2, computer1, computer2, new PrintStream(outTest));
        game.newGame();
        while (!game.exit()) {
            game.next();
        }
        assertThat(outTest.toString(), containsString("Игра закончена со счетом"));
    }

    @Test
    public void whemComputerVsComputerOnFiveFiledShouldSomeWins() {
        ByteArrayOutputStream outTest = new ByteArrayOutputStream();
        TicTacToe ticTacToe = new TicTacToe3T(5);
        Player computer1 = new ComputerRandom(ticTacToe, "Random computer1");
        Player computer2 = new ComputerRandom(ticTacToe, "Random computer2");
        Game game = new ConsoleGame(ticTacToe, 2, computer1, computer2, new PrintStream(outTest));
        game.newGame();
        while (!game.exit()) {
            game.next();
        }
        assertThat(outTest.toString(), containsString("Игра закончена со счетом"));
    }

}