package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.09.2019
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int width;
    private Direction direction;
    private int step;

    public RectangleMove(Rectangle rect, int width, int step) {
        this.rect = rect;
        this.width = width;
        this.step = step;
        this.direction = Direction.RIGHT;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            moveX(this.step, getDirection());

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Direction getDirection() {
        if (this.rect.getX() >= this.width) {
            this.direction = Direction.LEFT;
        }
        if (this.rect.getX() <= 0) {
            this.direction = Direction.RIGHT;
        }
        return this.direction;
    }

    private void moveX(int step, Direction direction) {
        if (direction == Direction.LEFT) {
            step = -step;
        }
        this.rect.setX(this.rect.getX() + step);
    }

}
