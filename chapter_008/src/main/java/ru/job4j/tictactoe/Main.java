package ru.job4j.tictactoe;

import ru.job4j.tictactoe.game.ConsoleGame;
import ru.job4j.tictactoe.game.Game;
import ru.job4j.tictactoe.logic.TicTacToe;
import ru.job4j.tictactoe.logic.TicTacToe3T;
import ru.job4j.tictactoe.player.ComputerRandom;
import ru.job4j.tictactoe.player.Human;
import ru.job4j.tictactoe.player.Player;

/**
 * Main start game
 * init game params
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.07.2019
 */
public class Main {

    public static void main(String[] args) {

        TicTacToe ticTacToe = new TicTacToe3T(3);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        //Player computer2 = new ComputerRandom(ticTacToe, "Random computer2");
        Player human = new Human(ticTacToe, "Human", System.in, System.out);
        Game game = new ConsoleGame(ticTacToe, 2, human, computer, System.out);
        game.newGame();
        while (!game.exit()) {
            game.next();
        }

    }

}
