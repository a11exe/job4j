package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        boolean winner;
        // all diagonals
        winner = this.fillBy(predicate, this.table.length - 1, 0, -1, 1)
                || this.fillBy(predicate, 0, 0, 1, 1);
        // horizontal & vertical
        if (!winner) {
            Predicate<Integer> horizontal = (i->this.fillBy(predicate, 0,  i, 1, 0));
            Predicate<Integer> vertical = (i->this.fillBy(predicate,  i, 0, 0, 1));
            winner = IntStream.range(0, table.length - 1)
                    .filter(i ->(horizontal.test(i) || vertical.test(i)))
                    .count() > 0;
        }
        return winner;
    }

    public boolean hasGap() {
        return !(this.table.length * this.table.length
                == Arrays.stream(this.table).flatMap(Arrays::stream).takeWhile(s->s.hasMarkO() || s.hasMarkX()).count());
    }
}
