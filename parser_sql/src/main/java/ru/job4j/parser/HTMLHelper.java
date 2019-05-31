package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class HTMLHelper {

    private static final Logger LOG = LogManager.getLogger(HTMLHelper.class);

    public String getHTMLPage(String url) {
        String htmlString = "";
        try {
            htmlString = Jsoup.connect(url).get().html();
        } catch (IOException e) {
            LOG.error(String.format("error getting url: %s", url), e);
        }
        return htmlString;
    }

}
