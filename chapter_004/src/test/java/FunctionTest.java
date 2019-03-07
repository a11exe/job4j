import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 07.03.2019
 */
public class FunctionTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, x -> 5 * Math.pow(x, 2) + 3 * x + 1);
        List<Double> expected = Arrays.asList(141D, 199D, 267D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, Math::log);
        List<Double> expected = Arrays.asList(1.6094379124341003D, 1.791759469228055D, 1.9459101490553132D);
        assertThat(result, is(expected));
    }

}
