package ru.job4j.parser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 31.05.2019
 */
public class ParserSQLTest {

    @Test
    public void whenParsePageShouldGetVacancies() throws IOException {
        HTMLHelper htmlHelper = mock(HTMLHelper.class);
        when(htmlHelper.getHTMLPage("https://www.sql.ru/forum/job-offers/1")).thenReturn(IOUtils.toString(Parser.class.getClassLoader().getResourceAsStream("test_vacancies_sqlru.html"), "UTF-8"));
        ParserSite parserSQL = new ParserSQL("https://www.sql.ru/forum/job-offers/", htmlHelper);
        List<Vacancy> vacancies = parserSQL.parsePage(1);
        assertThat(vacancies.size(), is(11));
    }

    @Test
    public void whenfillVacanciesBodyShouldGetVacanciesWithBody() throws IOException {
        HTMLHelper htmlHelper = mock(HTMLHelper.class);
        when(htmlHelper.getHTMLPage("https://www.sql.ru/forum/1313162/be-razrabotchik-java")).thenReturn(IOUtils.toString(Parser.class.getClassLoader().getResourceAsStream("test_vacancy_sqlru.html"), "UTF-8"));
        ParserSite parserSQL = new ParserSQL("https://www.sql.ru/forum/job-offers/", htmlHelper);
        Vacancy vacancy = new Vacancy("vac1", "vac text 77", "https://www.sql.ru/forum/1313162/be-razrabotchik-java", new Date());
        parserSQL.fillVacanciesBody(List.of(vacancy));
        String expected = "Расположение: Москва, Павелецкая/ Пролетарская Требуемый опыт работы: 1–3 года "
                + "Полная занятость, полный день Кого ищем? Сейчас ищем разработчика который хочет разрабатывать "
                + "как на клиенте, так и на сервере. Готовы рассмотреть F-E, если Вы хотите переходить на B-E, или наоборот. "
                + "Технологии применяемые у нас: На сервере: Java 8 (основной язык), Guice (внедрение зависимостей), "
                + "maven (сборка проекта), Jenkins (сборка проекта в 1 клик). На клиенте: Node.js (зависимости и запуск), "
                + "Vue.js (фреймворк) , Vuex (управление состоянием), axios (запросы на сервер). "
                + "Приложение разбито на несколько сервисов, взаимодействуют с помощью Rest Api. "
                + "Обязанности: Анализ технического задания, выбор технологий и инструментов для реализации, согласование технических решений; "
                + "Разработка программных алгоритмов, тестов, профилирование, документирование; "
                + "Рефакторинг существующего кода; Используемый стек: Jersey + Guice + Докумино (своя разработка по работе с данными). "
                + "Требования: Опыт разработки на Java SE 7, 8; Опыт работы с Jira, Maven, Git; Опыт создания Web-сервисов; "
                + "Уверенное владение стандартной библиотекой; Знание основных алгоритмов и шаблонов проектирования ПО; "
                + "Знание технического английского языка, чтение профессиональной литературы; Обучаемость, умение логически мыслить; "
                + "Умение разбираться в чужом коде. Навыки, которые дадут Вам преимущество: Владение технологиями "
                + "Web-разработки: HTML, JavaScript, CSS, JQuery, GWT, Vaadin; Опыт работы с реляционными БД (MySQL, PostgreSQL); "
                + "Опыт работы с ОС Linux. Условия: Интересные проекты (возможность поучаствовать в развитии собственной ecm-платформы, "
                + "используются последние технологии, инициатива приветствуется) Оформление по ТК РФ, белая заработная "
                + "плата ДМС по итогам испытательного срока Бонусы по итогам работы за год ЗП обсуждается в "
                + "индивидуальном порядке с кандидатами (потолок 115 т. гросс). По всем вопросам пишите jullysova@gmail.com, либо звоните +79055171455 Юлия.";
        assertThat(vacancy.getText(), is(expected));
    }







}