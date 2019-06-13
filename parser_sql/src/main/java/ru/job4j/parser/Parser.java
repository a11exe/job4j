package ru.job4j.parser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 31.05.2019
 */
public class Parser {

    private final DBUtil dbUtil;
    private final ParserSite parserSite;

    public Parser(DBUtil dbUtil, ParserSite parserSite) {
        this.dbUtil = dbUtil;
        this.parserSite = parserSite;
    }

    public void addLog(String event, String msg, Date date) {
        dbUtil.addLog(event, msg, date);
    }

    public List<Vacancy> getVacancies() {
        int page = 1;
        List<Vacancy> vacancies = new ArrayList<>();

        Date startDate = getStartDate();
        Date maxDate;
        do {
            List<Vacancy> vacanciesFromPage = parserSite.parsePage(page);
            maxDate = getMaxVacanciesDate(vacanciesFromPage);
            vacancies.addAll(vacanciesFromPage);
            page++;

        } while (page < 15 || maxDate.compareTo(startDate) > 0);

        return vacancies;
    }

    public void saveVacancies(List<Vacancy> vacancies) {
        List<Vacancy> vacanciesNew = dbUtil.getNewVacancies(vacancies);
        parserSite.fillVacanciesBody(vacanciesNew);
        dbUtil.save(vacanciesNew);
    }

    private Date getStartDate() {
        Date lastLaunch = dbUtil.getLastLaunch();
        return lastLaunch == null ? getFirstDayOfYear(new Date()) : lastLaunch;
    }

    private Date getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private static Date getMaxVacanciesDate(List<Vacancy> vacancies) {
        return Collections.max(vacancies.stream().map(Vacancy::getPosted).collect(Collectors.toList()));
    }
}
