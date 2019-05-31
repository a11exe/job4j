package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class Main {

    public static void main(String[] args) {

        final Logger LOG = LogManager.getLogger(Main.class);

        Properties properties = new Properties();
        if (args.length > 0) {
            try {
                properties.load(new FileReader(args[0]));
            } catch (IOException e) {
                LOG.error(String.format("Error loading properties from file %s", args[0]));
            }
        } else {
            LOG.error("Error loading properties no file in parameters");
        }

//        Properties properties = new Properties();
//        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream("app.properties")) {
//            properties.load(in);
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }

        if (!properties.isEmpty()) {
            LOG.info(String.format("cron string %s", properties.getProperty("cron.time")));
            JobDetail jobSQL = JobBuilder.newJob(ParserJobSQL.class)
                    .withIdentity("sqlParser", "group1")
                    .usingJobData("url", properties.getProperty("url"))
                    .usingJobData("username", properties.getProperty("username"))
                    .usingJobData("password", properties.getProperty("password"))
                    .build();

            Trigger triggerSQL = TriggerBuilder
                    .newTrigger()
                    .withIdentity("sqlParser", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(properties.getProperty("cron.time")))
                    .build();

            JobDetail jobHH = JobBuilder.newJob(ParserJobHH.class)
                    .withIdentity("hhParser", "group2")
                    .usingJobData("url", properties.getProperty("url"))
                    .usingJobData("username", properties.getProperty("username"))
                    .usingJobData("password", properties.getProperty("password"))
                    .build();

            Trigger triggerHH = TriggerBuilder
                    .newTrigger()
                    .withIdentity("hhParser", "group2")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(properties.getProperty("cron.time")))
                    .build();

            //schedule it
            Scheduler scheduler;
            try {
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.scheduleJob(jobSQL, triggerSQL);
                scheduler.scheduleJob(jobHH, triggerHH);
                scheduler.start();
            } catch (SchedulerException e) {
                LOG.error(String.format("Error scheduler %s", e.getMessage()));
            }
        }

    }

}
