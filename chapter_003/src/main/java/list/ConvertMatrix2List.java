package list;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует двумерный массив в лист
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.02.2019
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] line : array) {
            for (int aLine : line) {
                list.add(aLine);
            }
        }
        return list;
    }
}
