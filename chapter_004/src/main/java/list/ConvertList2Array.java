package list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Конвертируем лист в двумерный массив
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.02.2019
 */
public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % 3 != 0 ? list.size() / rows + 1 : list.size() / rows;
        int[][] array = new int[rows][cells];
        final int[] row = {0};
        final int[] cell = {0};
        list.forEach(x -> {
            array[row[0]][cell[0]++] = x;
            if (cell[0] == cells) {
                cell[0] = 0;
                row[0]++;
            }
        });
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
