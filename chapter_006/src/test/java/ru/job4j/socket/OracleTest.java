package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OracleTest {

    private final static String LN = System.lineSeparator();

    private void serverTest(String ask, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream is = new ByteArrayInputStream(ask.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(is);
        when(socket.getOutputStream()).thenReturn(out);
        Oracle oracle = new Oracle(socket);
        oracle.init();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenServerExitTest() throws IOException {
        serverTest("пока", Joiner.on(LN).join(
                "", ""
        ));

    }

    @Test
    public void whenServerHelloTest() throws IOException {
        String ask = Joiner.on(LN).join("hello", "пока");
        String expected = Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "", "", "");
        serverTest(ask, expected);
    }

}