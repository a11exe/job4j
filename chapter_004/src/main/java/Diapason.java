import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 07.03.2019
 */
public class Diapason {

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

}
