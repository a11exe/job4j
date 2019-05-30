package ru.job4j.magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class Magnit {

    public static void main(String[] args) {

        String tempDir = System.getProperty("java.io.tmpdir");

        Config config = new Config();
        config.init();

        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(1000);
        List<Entry> list = storeSQL.load();

        File xml = new File(tempDir + "entries.xml");
        StoreXML storeXML = new StoreXML(xml);
        storeXML.save(list);
        System.out.println(xml.getAbsolutePath());

        File xsqt = new File(tempDir + "new_entries.xml");
        File schema = new File(tempDir + "schema.xslt");
        try {
            Files.copy(Magnit.class.getClassLoader().getResourceAsStream("schema.xslt"), Paths.get(tempDir, "schema.xslt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(xml, xsqt, schema);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SAXPars saxp = new SAXPars();

            parser.parse(xsqt, saxp);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
