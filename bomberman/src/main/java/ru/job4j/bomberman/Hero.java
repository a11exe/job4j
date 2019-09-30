package ru.job4j.bomberman;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class Hero implements Runnable {

    private final Board board;
    private Cell position;
    private final String name;
    private final List<Direction> steps = List.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
    private static final Logger LOG = LogManager.getLogger(Hero.class);

    public Hero(Board board, Cell position, String name) {
        this.board = board;
        this.position = position;
        this.name = name;
    }

    @Override
    public void run() {

        ReentrantLock lock = this.board.getLock(this.position);
        lock.lock();
        LOG.info("set lock " + position);

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            for (Direction direction: this.steps
                 ) {
                if (go(direction)) {
                    break;
                }
            }

        }
    }

    private boolean go(Direction direction) {
        boolean result = false;

        if (isNextCell(this.position, direction)) {
            LOG.info(this.getName() + " " + direction);
            Cell dist = getNextCell(this.position, direction);

            if (board.move(this.position, dist)) {
                this.position = dist;
                result = true;
            }
        }
        return result;
    }

    public boolean isNextCell(Cell cell, Direction direction) {
        boolean nextCell = true;
        if (direction.equals(Direction.UP) && cell.getY() == 0) {
            nextCell = false;
        }
        if (direction.equals(Direction.DOWN) && cell.getY() == this.board.getSize() - 1) {
            nextCell = false;
        }
        if (direction.equals(Direction.LEFT) && cell.getX() == 0) {
            nextCell = false;
        }
        if (direction.equals(Direction.RIGHT) && cell.getX() == this.board.getSize() - 1) {
            nextCell = false;
        }

        return nextCell;

    }

    public Cell getNextCell(Cell cell, Direction direction) {
        Cell next = null;
        if (direction.equals(Direction.UP)) {
            next = new Cell(cell.getX(), cell.getY() - 1);
        }
        if (direction.equals(Direction.DOWN)) {
            next = new Cell(cell.getX(), cell.getY() + 1);
        }
        if (direction.equals(Direction.LEFT)) {
            next = new Cell(cell.getX() - 1, cell.getY());
        }
        if (direction.equals(Direction.RIGHT)) {
            next = new Cell(cell.getX() + 1, cell.getY());
        }

        return next;
    }


    public String getName() {
        return name;
    }
}
