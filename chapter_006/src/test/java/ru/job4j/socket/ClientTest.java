package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private final static String LN = System.lineSeparator();

    private void test(String ask, String answer, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream is = new ByteArrayInputStream(answer.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(is);
        when(socket.getOutputStream()).thenReturn(out);
        System.setIn(new ByteArrayInputStream(ask.getBytes()));
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
        Client client = new Client(socket);
        client.init();
        assertThat(consoleOut.toString(), is(expected));
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void whenClientExitTest() throws IOException {
        test("пока", Joiner.on(LN).join("", ""),  "");
    }

    @Test
    public void whenClientHelloTest() throws IOException {
        test(
                Joiner.on(LN).join("hello", "пока"),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.", "", "", ""),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.", ""));
    }


}
