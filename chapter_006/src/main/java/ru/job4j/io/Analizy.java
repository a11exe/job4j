package ru.job4j.io;

import java.io.*;
import java.util.List;

/**
 * Analyze server log
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.04.2019
 */
public class Analizy {

    public void unavailable(String source, String target) {

        List<String> unavailable = List.of("400", "500");
        List<String> available = List.of("200", "530");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            String line = reader.readLine();
            Item item = null;
            StringBuilder result = new StringBuilder();
            while (line != null) {
                Item nextItem = createItem(line);

                if (statusChanged(item, nextItem, available, unavailable)) {
                    result.append(nextItem);
                } else if (statusChanged(item, nextItem, unavailable, available)) {
                    result.append(nextItem).append(System.lineSeparator());
                }
                item = (nextItem != null) ? nextItem : item;
                line = reader.readLine();
            }

            out.print(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean statusChanged(Item oldItem, Item newtItem, List oldStatus, List newStatus) {

            return  (oldItem != null
                    && newtItem != null
                    && oldStatus.contains(oldItem.status))
                    && (newStatus.contains(newtItem.status));
    }

    private class Item {
        String status;
        String time;

        @Override
        public String toString() {
            return time + ";";
        }
    }

    private Item createItem(String str) {
        Item item = null;
        String[] itemArr = str.split(" ");
        if (itemArr.length == 2) {
            item = new Item();
            item.status = itemArr[0];
            item.time = itemArr[1];
        }
        return item;
    }


    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
