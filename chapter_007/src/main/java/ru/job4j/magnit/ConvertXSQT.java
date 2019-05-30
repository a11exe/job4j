package ru.job4j.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(
                    new StreamSource(
                            new FileInputStream(scheme))
            );
            transformer.transform(new StreamSource(
                            new FileInputStream(source)),
                    new StreamResult(new FileOutputStream(dest))
            );
        } catch (FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
