package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConvertXSQTTest {

    @Test
    public void whenConvert3EntryShouldRightXML() throws IOException {

        String tempDir = System.getProperty("java.io.tmpdir");

        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <field>1</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>2</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>3</field>\n"
                + "    </entry>\n"
                + "</entries>\n";

        File xml = Files.write(Path.of(tempDir + "test.xml"), xmlData.getBytes(), StandardOpenOption.CREATE).toFile();

        File xsqt = new File(tempDir + "new_entries.xml");
        File schema = new File(tempDir + "schema.xslt");
        Files.copy(Magnit.class.getClassLoader().getResourceAsStream("schema.xslt"), Paths.get(tempDir, "schema.xslt"), StandardCopyOption.REPLACE_EXISTING);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(xml, xsqt, schema);

        String actualXML = new String(Files.readAllBytes(Path.of(xsqt.getAbsolutePath())));
        System.out.println(actualXML);
        String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<entries>\n"
                + "　<entry field=\"1\"/>\n"
                + "　<entry field=\"2\"/>\n"
                + "　<entry field=\"3\"/>\n"
                + "</entries>";


        assertThat(actualXML, is(expectedXML));


    }


}