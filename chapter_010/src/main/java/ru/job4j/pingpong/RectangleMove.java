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
    private final int step;

    public RectangleMove(Rectangle rect, int width, int step) {
        this.rect = rect;
        this.width = width;
        this.step = step;
    }

    @Override
    public void run() {
        int direction = 1;

        while (true) {
            if (this.rect.getX() >= this.width) {
                direction = -1;
            }
            if (this.rect.getX() <= 0) {
                direction = 1;
            }
            this.rect.setX(this.rect.getX() + (direction * step));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
