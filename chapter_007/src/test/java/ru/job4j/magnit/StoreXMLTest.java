package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void whenSave3EntryShouldRightXMLFile() throws IOException {

        String tempDir = System.getProperty("java.io.tmpdir");
        File actual = new File(tempDir + "actual.xml");
        StoreXML storeXML = new StoreXML(actual);
        storeXML.save(List.of(new Entry(1), new Entry(2), new Entry(3)));
        String actualXML = new String(Files.readAllBytes(Path.of(actual.getAbsolutePath())));
        String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
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
        assertThat(actualXML, is(expectedXML));

    }


}