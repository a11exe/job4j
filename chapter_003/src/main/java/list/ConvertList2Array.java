package list;

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
        int k = 0;
        int cells = list.size() % 3 != 0 ? list.size() / rows + 1 : list.size() / rows;
        int[][] array = new int[rows][cells];
        if (list.size() > 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cells; j++) {
                    array[i][j] = k >= list.size() ? 0 : list.get(k++);
                }
            }
        }
        return array;
    }
}
