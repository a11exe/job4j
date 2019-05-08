package ru.job4j.mdutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.04.2019
 */
public class Table {

    public String makeTable(List<String> strings) {
        StringBuilder table = new StringBuilder();
        if (strings.size() > 1) {
            table.append(getTableString(strings.get(0)));
            table.append(getHeadDiv(strings.get(0)));
            IntStream.range(1, strings.size())
                    .forEach(idx ->table.append(getTableString(strings.get(idx))));
        }
        return table.toString();
    }

    private String getTableString(String str) {
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split("\t");
        Arrays.stream(arr).forEach(s -> sb.append("| ").append(s));
        sb.append(" |").append(System.lineSeparator());
        return sb.toString();
    }

    private String getHeadDiv(String str) {
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split("\t");
        Arrays.stream(arr).forEach(s -> sb.append("| ").append("---"));
        sb.append(" |").append(System.lineSeparator());
        return sb.toString();
    }

    public static void main(String[] args) {

        String str;
        Table table = new Table();
        Scanner input = new Scanner(System.in);
        List<String> strings = new ArrayList<>();
        while (input.hasNextLine()) {
            str = input.nextLine();
            strings.addAll(Arrays.asList(str.split(System.lineSeparator())));
            if (str.isEmpty()) {
                break;
            }
        }
        System.out.println(table.makeTable(strings));
    }

}
