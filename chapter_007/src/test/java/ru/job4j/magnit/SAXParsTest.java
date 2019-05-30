package ru.job4j.magnit;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SAXParsTest {

    @Test
    public void whenParse3EntryShouldSumSix() throws ParserConfigurationException, SAXException, IOException {

        String tempDir = System.getProperty("java.io.tmpdir");

        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<entries>\n"
                + "　<entry field=\"1\"/>\n"
                + "　<entry field=\"2\"/>\n"
                + "　<entry field=\"3\"/>\n"
                + "</entries>";

        File xsqt = Files.write(Path.of(tempDir + "testxsrt.xml"), xmlData.getBytes(), StandardOpenOption.CREATE).toFile();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        parser.parse(xsqt, saxp);
        assertThat(out.toString(), is("Result: 6\n"));
        System.setOut(System.out);
    }


}