package lambda;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
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
        List<Matcher<? super Double>> expected = List.of(
        closeTo(1.609D, 0.001),
        closeTo(1.791D, 0.001),
        closeTo(1.945D, 0.001));
        assertThat(result, contains(expected));
    }

}
