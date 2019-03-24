package stream;

import java.util.Arrays;

public class StreamApiTask {

    public int calculate(int[] arr) {
        return Arrays.stream(arr).filter(s->s % 2 == 0).reduce(0, (x, y)->x + y * y);
    }

}
