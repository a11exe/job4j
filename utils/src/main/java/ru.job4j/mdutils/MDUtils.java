package ru.job4j.mdutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Преобразует строку в заголовок со ссылко для файлов
 * README.md в github
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 10.04.2019
 */
public class MDUtils {

    private final static String NAME = "SQL";

    class QA {
        String question;
        String answer;
    }

    public QA getMenuItem(String str) {
        str = str.replaceAll("#", "");
        str = str.replaceAll("\\(", "");
        str = str.replaceAll("\\)", "");
        str = str.replaceAll("\\[", "");
        str = str.replaceAll("\\]", "");
        str = str.replaceAll("\\+", "");
        str = str.replaceAll("\\/", "");
        str = str.trim();

        String question = str;
        question = "+ [" + question + "]";

        str = str.replaceAll("\\.", "");
        str = str.replaceAll("\\,", "");
        str = str.replaceAll("\\?", "");
        str = str.replaceAll(" ", "-");

        String answer = removeNumberFromBeginOfLine(str);

        QA qa = new QA();
        qa.question = question + "(#" + answer + ")";
        qa.answer = "## "+answer.replaceAll("-", " ");

        return qa;
    }

    private String removeNumberFromBeginOfLine(String str) {
        if (str.indexOf("-") > 0) {
            String number = str.substring(str.indexOf("-") - 1);
            if (isNumeric(number)) {
                str = str.substring(str.indexOf("-") + 1);
            }
        }
        return str;
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String str;
        MDUtils mdUtils = new MDUtils();
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            str = input.nextLine();
            lines.add(str);
            if (str.isEmpty()) {
                break;
            }
        }
        System.out.println();
        List<QA> qaList = new ArrayList<>();
        lines.forEach(s->qaList.add(mdUtils.getMenuItem(s)));
        qaList.forEach(qa -> System.out.println(qa.question));
        qaList.forEach(qa -> {
            System.out.println(qa.answer);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("[к оглавлению](#"+NAME+")");
            System.out.println();
        });
    }


}
