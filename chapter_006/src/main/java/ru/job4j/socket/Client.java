package ru.job4j.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.05.2019
 */
public class Client {

    private final Socket socket;
    private static final String EXIT = "пока";

    public static void main(String[] args) {

        try (final Socket socket = new Socket(InetAddress.getByName("localhost"), 1999)) {
            Client client = new Client(socket);
            client.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void init() throws IOException {

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String ask;
        do {
            ask = console.nextLine();
            out.println(ask);
            String str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
                str = in.readLine();
            }
        } while (!EXIT.equals(ask));

    }

}
