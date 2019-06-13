package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 10.06.2019
 */
public class ParserHH implements ParserSite {

    private final String url;
    private final HTMLHelper htmlHelper;

    public ParserHH(String url, HTMLHelper htmlHelper) {
        this.url = url;
        this.htmlHelper = htmlHelper;
    }

    @Override
    public List<Vacancy> parsePage(int page) {
        String vacanciesHtml = htmlHelper.getHTMLPage(url + page);
        return parseVacancies(vacanciesHtml);
    }

    @Override
    public void fillVacanciesBody(List<Vacancy> vacancies) {
        vacancies.forEach(vacancy -> vacancy.setText(getVacancyBody(htmlHelper.getHTMLPage(vacancy.getUrl()))));
    }

    private String getVacancyBody(String htmlPage) {
        Document doc = Jsoup.parse(htmlPage);
        Element msg = doc.selectFirst(".vacancy-description");
        Element content =  msg.selectFirst(".g-user-content");
        String text = "";
        if (content != null) {
            text = content.text();
        }
        return text;
    }

    private List<Vacancy> parseVacancies(String vacanciesHtml) {
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc = Jsoup.parse(vacanciesHtml);
        Elements elements = doc.select(".vacancy-serp-item");

        for (Element element : elements) {
            Element url = element.selectFirst("a");
            String name = url.text();
            String link = getUrl(url);
            String date = element.selectFirst(".vacancy-serp-item__publication-date") == null ? null : element.selectFirst(".vacancy-serp-item__publication-date").text();
            if (isJava(name.toLowerCase())) {
                vacancies.add(new Vacancy(name, link, parseDate(date)));
            }
        }
        return vacancies;
    }

    private Date parseDate(String date) {
        String[] dateParts = date.split(" ");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, getMonthByName(dateParts[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));

        return cal.getTime();
    }

    private boolean isJava(String name) {
        return  !(name.contains("javascript") || name.contains("java script")) && (name.contains("java"));
    }

    private String getUrl(Element element) {
        Element link = element.select("a").first();
        return link.attr("href"); // == "/"
    }

    private int getMonthByName(String month) {
        int numMonth;
        switch (month) {
            case ("января") : numMonth = 0; break;
            case ("февраля") : numMonth = 1; break;
            case ("марта") : numMonth = 2; break;
            case ("апреля") : numMonth = 3; break;
            case ("мая") : numMonth = 4; break;
            case ("июня") : numMonth = 5; break;
            case ("июля") : numMonth = 6; break;
            case ("августа") : numMonth = 7; break;
            case ("сентября") : numMonth = 8; break;
            case ("отктября") : numMonth = 9; break;
            case ("ноября") : numMonth = 10; break;
            case ("декабря") : numMonth = 11; break;
            default: numMonth = -1;
        }
        return numMonth;
    }



}
