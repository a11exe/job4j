package ru.job4j.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class HTMLHelperTest {

    @Test
    public void whenGetHtmlVacanciesShouldReturnHTML() {
        HTMLHelper htmlHelper = new HTMLHelper();
        assertTrue(htmlHelper.getHTMLPage("https://www.sql.ru/forum/job-offers").contains("<title>Вакансии / Форум / Sql.ru</title> "));
    }

}