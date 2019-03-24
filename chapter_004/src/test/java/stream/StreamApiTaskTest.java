package stream;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StreamApiTaskTest {

    @Test
    public void when0224InArrShould24() {
        StreamApiTask streamApiTask  = new StreamApiTask();
        int[] arr = new int[]{1, 0, 3, 2, 7, 9, 2, 4};
        assertThat(streamApiTask.calculate(arr), is(24));
    }

    @Test
    public void whenEmptyArrShould0() {
        StreamApiTask streamApiTask  = new StreamApiTask();
        int[] arr = new int[]{};
        assertThat(streamApiTask.calculate(arr), is(0));
    }

    @Test
    public void whenHasNoEvenArrShould0() {
        StreamApiTask streamApiTask  = new StreamApiTask();
        int[] arr = new int[]{1, 3, 5, 7};
        assertThat(streamApiTask.calculate(arr), is(0));
    }


}