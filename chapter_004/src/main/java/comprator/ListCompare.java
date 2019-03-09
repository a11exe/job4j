package comprator;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Сравнение двух строк.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2019
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return IntStream.range(0, Math.min(o1.length(), o2.length()))
                .map(i -> Character.compare(o1.charAt(i), o2.charAt(i)))
                .filter(i -> i != 0)
                .findFirst()
                .orElse(o1.length() - o2.length());
    }
}
