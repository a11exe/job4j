package ru.job4j.tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generate string using template {name}
 * replace {name} by values with same key in map values
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 03.07.2019
 */
public class SimpleGenerator {

    private static final Pattern KEYS = Pattern.compile("\\$\\{([a-zA-Z]+)\\}");

    public static String formatString(String template, Map<String, String> values) {
        Matcher m = KEYS.matcher(template);
        if (m.results().map(k->k.group(1)).distinct().count() != (long) values.size()) {
            throw new RuntimeException("wrong count of keys");
        }
        return m.replaceAll(matchResult -> values.get(matchResult.group(1)));
    }

}
