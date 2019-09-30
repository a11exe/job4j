package ru.job4j.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class Board {

    private final ReentrantLock[][] board;
    private final int size;
    private static final Logger LOG = LogManager.getLogger(Board.class);

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }


    public boolean move(Cell source, Cell dist) {

        boolean result = false;
        ReentrantLock lockDist = getLock(dist);
        ReentrantLock lockSource = getLock(source);

        try {
            if (lockDist.tryLock(500, TimeUnit.MILLISECONDS)) {
                LOG.info(Thread.currentThread().getName() + " step go from " + source + " to " + dist + " success");
                LOG.info("unlock " + source);

                lockSource.unlock();
                result = true;
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return result;
    }

    public ReentrantLock getLock(Cell cell) {
        return board[cell.getX()][cell.getY()];
    }

    public int getSize() {
        return size;
    }
}