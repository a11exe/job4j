package comprator;

import java.util.Comparator;

/**
 * Сравнение двух строк.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2019
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int minSize = Math.min(o1.length(), o2.length());
        int rez = 0;
        for (int i = 0; i < minSize; i++) {
            rez = Character.compare(o1.charAt(i), o2.charAt(i));
            if (rez != 0) {
                break;
            }
        }
        rez = rez == 0 ? o1.length() - o2.length() : rez;
        return rez;
    }
}
