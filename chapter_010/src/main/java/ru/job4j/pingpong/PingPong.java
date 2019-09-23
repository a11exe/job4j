package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Ping pong game
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.09.2019
 */
public class PingPong extends Application {

    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        RectangleMove rectangleMove = new RectangleMove(rect, limitX - 10, 1);
        Thread thread = new Thread(rectangleMove);
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);

        stage.setOnCloseRequest(
                event -> thread.interrupt()
        );

        stage.show();
    }

}
