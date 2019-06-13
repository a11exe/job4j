package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class ParserSQL implements ParserSite {

    private final String url;
    private final HTMLHelper htmlHelper;

    public ParserSQL(String url, HTMLHelper htmlHelper) {
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

    private List<Vacancy> parseVacancies(String vacanciesHtml) {
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc = Jsoup.parse(vacanciesHtml);
        Elements elements = doc.getElementsByTag("tr");

        for (Element element : elements) {
            Element url = element.selectFirst(".postslisttopic a:not(.newTopic):not(.pages)");
            if (isJava(url)) {
                vacancies.add(new Vacancy(url.text(), getUrl(element), parseDate(element.getElementsByTag("td").get(5).selectFirst(".altCol").text())));
            }
        }
        return vacancies;
    }

    private String getUrl(Element element) {
        Element link = element.select("a").first();
        return link.attr("href"); // == "/"
    }

    private String getVacancyBody(String htmlString) {
        Document doc = Jsoup.parse(htmlString);
        Element msg = doc.selectFirst(".msgTable");
        String vacancyBody = "";
        Elements elements = msg.select(".msgBody");
        if (elements.size() > 1) {
            vacancyBody = elements.get(1).text();
        }
        return vacancyBody;
    }

    private boolean isJava(Element url) {
        boolean isJava = false;
        if (url != null) {
            String str = url.toString().toLowerCase();
            isJava = !(str.contains("javascript") || str.contains("java script")) && (str.contains("java"));
        }
        return isJava;
    }

    private Date parseDate(String text) {

        Calendar cal = Calendar.getInstance();

        String[] dateTimeArr = text.split(",");
        String date = dateTimeArr[0];
        String[] timeArr = dateTimeArr[1].trim().split(":");

        cal.set(Calendar.HOUR, Integer.parseInt(timeArr[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));

       if ("вчера".equals(date)) {
            cal.add(Calendar.DATE, -1);
        } else if (!"сегодня".equals(date)) {
            String[] dateParts = date.split(" ");
            cal.set(Calendar.YEAR, getActualYear(dateParts[2],  cal.get(Calendar.YEAR)));
            cal.set(Calendar.MONTH, getMonthByName(dateParts[1]));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
        }

        return cal.getTime();
    }

    private int getActualYear(String year, Integer actualYear) {
        return Integer.parseInt(String.valueOf(actualYear).substring(0, 2) + year);
    }

    private int getMonthByName(String month) {
        int numMonth;
        switch (month) {
            case ("янв") : numMonth = 0; break;
            case ("фев") : numMonth = 1; break;
            case ("мар") : numMonth = 2; break;
            case ("апр") : numMonth = 3; break;
            case ("май") : numMonth = 4; break;
            case ("июн") : numMonth = 5; break;
            case ("июл") : numMonth = 6; break;
            case ("авг") : numMonth = 7; break;
            case ("сен") : numMonth = 8; break;
            case ("отк") : numMonth = 9; break;
            case ("ноя") : numMonth = 10; break;
            case ("дек") : numMonth = 11; break;
            default: numMonth = -1;
        }
        return numMonth;
    }

}
