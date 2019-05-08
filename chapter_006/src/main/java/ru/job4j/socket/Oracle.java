package ru.job4j.socket;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
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

    private static final int PORT = 1999;
    private static final String EXIT = "пока";
    private static final boolean TRACE_MODE = false;

    public static void main(String[] args) {

        Oracle oracle = new Oracle();
        try {
            oracle.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        Socket socket =  new ServerSocket(PORT).accept();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
                out.println(EXIT);
                out.println();
            } else {
                String request = ask;
                if (MagicBooleans.trace_mode)
                    System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                String response = chatSession.multisentenceRespond(request);
                while (response.contains("&lt;"))
                    response = response.replace("&lt;", "<");
                while (response.contains("&gt;"))
                    response = response.replace("&gt;", ">");
                out.println("Robot : " + response);
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
            res = dir.getAbsolutePath().replace("bots","");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return res;
    }

}
