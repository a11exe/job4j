package list;

import java.util.ArrayList;
import java.util.List;

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
        int row = 0;
        int cell = 0;
        for (Integer x : list) {
            array[row][cell++] = x;
            if (cell == cells) {
                cell = 0;
                row++;
            }
        }

        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] arr: list
             ) {
            for (int x: arr
                 ) {
                result.add(x);
            }
        }
        return result;
    }
}
