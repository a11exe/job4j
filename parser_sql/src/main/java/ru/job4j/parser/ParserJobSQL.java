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
public class ParserJobSQL implements Job {

    private static final Logger LOG = LogManager.getLogger(ParserJobSQL.class);

    public void execute(JobExecutionContext context) {
        LOG.info("Start parsing job SQL.ru - " + new Date());

        JobDataMap properties = context.getJobDetail().getJobDataMap();

        DBUtil dbUtil = new DBUtil(properties.getString("url"), properties.getString("username"), properties.getString("password"));
        ParserSite parserSQL = new ParserSQL("https://www.sql.ru/forum/job-offers/", new HTMLHelper());
        Parser parser = new Parser(dbUtil, parserSQL);
        parser.addLog("START", "start parsing sql", new Date());
        List<Vacancy> vacancies = parser.getVacancies();
        parser.saveVacancies(vacancies);
        parser.addLog("END", "end parsing sql", new Date());

    }

}
