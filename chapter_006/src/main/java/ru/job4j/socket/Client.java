package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.05.2019
 */
public class Client {

    private static final String IP = "localhost";
    private static final int PORT = 1999;
    private static final String EXIT = "пока";

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {

        Socket socket = new Socket(InetAddress.getByName(IP), PORT);
        //socket.setSoTimeout(10000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String ask;
        do {
            ask = console.nextLine();
            out.println(ask);
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (!EXIT.equals(ask));

    }

}
