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
public class ParserHHTest {

    @Test
    public void whenParsePageShouldGetVacancies() throws IOException {
        HTMLHelper htmlHelper = mock(HTMLHelper.class);
        when(htmlHelper.getHTMLPage("https://hh.ru/search/vacancy?L_is_autosearch=false&area=1&clusters=true&enable_snippets=true&text=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82+java&page=0"))
                .thenReturn(IOUtils.toString(Parser.class.getClassLoader().getResourceAsStream("test_vacancies_hhru.html"), "UTF-8"));
        ParserSite parserHH = new ParserHH("https://hh.ru/search/vacancy?L_is_autosearch=false&area=1&clusters=true&enable_snippets=true&text=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82+java&page=", htmlHelper);
        List<Vacancy> vacancies = parserHH.parsePage(0);
        assertThat(vacancies.size(), is(12));
    }

    @Test
    public void whenfillVacanciesBodyShouldGetVacanciesWithBody() throws IOException {
        HTMLHelper htmlHelper = mock(HTMLHelper.class);
        when(htmlHelper.getHTMLPage("https://hh.ru/vacancy/29710014?query=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82%20java"))
                .thenReturn(IOUtils.toString(Parser.class.getClassLoader().getResourceAsStream("test_vacancy_hhru.html"), "UTF-8"));
        ParserSite parserHH = new ParserHH("https://hh.ru/search/vacancy?area=1&text=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82+java", htmlHelper);
        Vacancy vacancy = new Vacancy("vac1", "vac text 77", "https://hh.ru/vacancy/29710014?query=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82%20java", new Date());
        parserHH.fillVacanciesBody(List.of(vacancy));
        String expected = "Компания Colvir Software Solutions была основана в Великобритании и занимает лидирующие "
                + "позиции на рынке разработки, внедрения и поддержки комплексных интегрированных решений для банков, "
                + "почт, предприятий и государственных финансовых институтов. Мы быстрорастущая команда профессионалов "
                + "и не ограничиваем себя рамками одной страны или города. Офисы нашей Компании есть в России, Беларуси, "
                + "Казахстане, Узбекистане, Турции, Великобритании. Мы ищем Middle Java Developer для развития внутреннего "
                + "сервиса управления работами и проектами. Сервис построен на базе продукта TrackStudio. "
                + "Сервис обслуживает более 1000 пользователей, содержит более 900 тысяч задач и постоянно нуждается "
                + "в оптимизации и развитии. В состав входит треккер задач, клиентский ServiceDeck, интеграция с "
                + "технологическими инструментами разработки, специализированные портлеты. Используемый стек технологий: "
                + "Java EE7 (Glassfish/Payara), Oracle DB, Angular. Основные обязанности: • Разработка нового и доработка "
                + "существующего функционала по требованиям пользователей и постановкам задач от аналитика "
                + "• Покрытие написанного кода unit-тестами • Поиск и устранение ошибок в функциональности по заявкам пользователей "
                + "• Поддержка установки обновлений, консультирование пользователей В своих сотрудниках мы хотим видеть: "
                + "• Высшее техническое образование • Владение стеком Java EE7 (Glassfish/Payara), опыт практического использования от 2-х лет "
                + "• Понимание многопоточности в Java • DеvOps навыки в части администрирования собственных тестовых стендов (Linux, Oracle) "
                + "• Умение читать и отлаживать собственный и чужой программный код Существенным плюсом будет: "
                + "• Навыки и готовность разбираться и рефакторить legacy-код • Фронтенд разработка на Angular, базовые навыки создания single page web applications "
                + "• Навыки оптимизации SQL-запросов • Опыт работы в распределённой команде • Умение быстро переключаться между задачами "
                + "Возможности профессионального и карьерного роста: • Возможность развития навыков работы с базами данных, навыков проектирования "
                + "• Возможность карьерного роста до помощника конструктора модуля или конструктора модуля • Опыт работы в распределенной команде "
                + "• Опыт совместной работы в крупном коллективе Colvir предлагает сотрудникам: • Стабильную работу над масштабным комплексным решением "
                + "• Надбавки за кураторство и др. • Достойную заработную плату и бонусы";
        assertThat(vacancy.getText(), is(expected));
    }







}