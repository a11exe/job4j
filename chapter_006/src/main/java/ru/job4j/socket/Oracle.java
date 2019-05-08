package ru.job4j.socket;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.05.2019
 */
public class Oracle {

    private final Socket socket;
    private static final String EXIT = "пока";
    private static final boolean TRACE_MODE = false;

    public static void main(String[] args) {

        try (final Socket socket = new ServerSocket(1999).accept()) {
            Oracle oracle = new Oracle(socket);
            oracle.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Oracle(Socket socket) {
        this.socket = socket;
    }

    public void init() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask;

        String resourcesPath = getResourcesPath();
        System.out.println(resourcesPath);
        MagicBooleans.trace_mode = TRACE_MODE;
        Bot bot = new Bot("super", resourcesPath);
        Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();

        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);

            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (EXIT.equals(ask)) {
                out.println();
            } else {
                if (MagicBooleans.trace_mode) {
                    System.out.println("STATE=" + ask + ":THAT=" + chatSession.thatHistory.get(0).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                }
                String response = chatSession.multisentenceRespond(ask);
                while (response.contains("&lt;")) {
                    response = response.replace("&lt;", "<");
                }
                while (response.contains("&gt;")) {
                    response = response.replace("&gt;", ">");
                }
                out.println("Oracle: " + response);
                out.println();
            }
        } while (!EXIT.equals(ask));
    }

    private String getResourcesPath() {
        URL resource = Oracle.class.getResource("/bots");
        String res = "";
        try {
            File dir = Paths.get(resource.toURI()).toFile();
            System.out.println(dir);
            res = dir.getAbsolutePath().replace("bots", "");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return res;
    }

}
