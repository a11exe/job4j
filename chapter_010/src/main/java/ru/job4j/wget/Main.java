package ru.job4j.wget;
import java.net.URL;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        Parameters param;

        try {
            if (DEBUG) {
                param = new Parameters(
                        new URL("https://github.com/ik87/TheatreSquare/archive/master.zip"),
                        1000
                );
            } else {
                param = getParameters(args);
            }
            Thread thread = new Thread(new Downloader(param));
            thread.start();
            thread.join();
        } catch (Exception e) {
            System.out.println("set correct args: wgetj [url] <speed>");
        }


    }

    private static Parameters getParameters(String[] args) throws Exception {
        URL url = new URL(args[0]);
        int maxSpeed = 10_000;
        if (args.length == 2) {
            maxSpeed = Integer.parseInt(args[1]);
        }
        return new Parameters(url, maxSpeed);
    }
}
