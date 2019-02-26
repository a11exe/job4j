package peer;
// TODO убрать пустую строку

import java.util.*;

public class Convert {

    public Convert(){ // TODO пустой конструктор можно не создавать, если нет других конструкторов, то создается автоматически
// TODO убрать пустую строку
    }

    //Converts array to list
    List<Integer> makeList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                list.add(array[i][j]);
        }
        return list;
    }
// TODO убрать пустую строку

    //Converts list to array
    public int[][] makeArray(List<Integer> list, int rws) { // TODO возможно лучше rows, вместо rws
        Iterator<Integer> iterator = list.iterator();
        int cls = list.size() / rws + (list.size() % rws == 0 ? 0 : 1); // TODO возможно лучше cells, вместо cls
// TODO убрать пустую строку
// TODO убрать пустую строку
        int[][] array = new int[rws][cls]; // TODO массив int по умолчанию инициализирован нулями, можно не присваивать 0
        for (int i = 0; i < rws; i++) {
            for (int j = 0; j < cls; j++) {
                if (iterator.hasNext()) 
                    array[i][j] = iterator.next();
                else
                    array[i][j] = 0;
            }
        }
        return array;
    }
}