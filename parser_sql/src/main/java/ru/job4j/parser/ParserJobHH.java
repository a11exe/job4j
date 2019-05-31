package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class ParserJobHH implements Job {

    private static final Logger LOG = LogManager.getLogger(ParserJobHH.class);

    public void execute(JobExecutionContext context) {
        LOG.info("Start parsing job HH.ru - " + new Date());

        JobDataMap properties = context.getJobDetail().getJobDataMap();

        DBUtil dbUtil = new DBUtil(properties.getString("url"), properties.getString("username"), properties.getString("password"));
        ParserSite parserHH = new ParserHH("https://hh.ru/search/vacancy?L_is_autosearch=false&area=1&clusters=true&enable_snippets=true&text=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82+java&page=", new HTMLHelper());
        Parser parser = new Parser(dbUtil, parserHH);
        parser.addLog("START", "start parsing hh", new Date());
        List<Vacancy> vacancies = parser.getVacancies();
        parser.saveVacancies(vacancies);
        parser.addLog("END", "end parsing hh", new Date());

    }

}
