package list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Конвертирует двумерный массив в лист
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.02.2019
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
         return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
