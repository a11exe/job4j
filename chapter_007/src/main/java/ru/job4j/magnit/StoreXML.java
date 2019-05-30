package ru.job4j.magnit;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class StoreXML {

    private final File file;

    public StoreXML(File file) {
        this.file = file;
    }

    public void save(List<Entry> list) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Entries entries = new Entries();
            entries.setEntries(list);
            jaxbMarshaller.marshal(entries, this.file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
