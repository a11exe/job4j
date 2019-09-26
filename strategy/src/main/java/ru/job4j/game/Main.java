package ru.job4j.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.game.utils.LoadUtil;
import ru.job4j.game.utils.TeamUtil;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.09.2019
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        List<Hero> heroes = new LoadUtil().loadHeroes(Group.REGULAR);
        TeamUtil teamUtil = new TeamUtil(heroes);

        Team team1 = teamUtil.getTeam1();
        Team team2 = teamUtil.getTeam2();

        LOG.info("Start game " + team1.getRace() + " Vs " + team2.getRace());

        while (!team1.isTeamDead() && !team2.isTeamDead()) {
            team1.turn(team2);
            team2.turn(team1);
        }
        if (team1.isTeamDead()) {
            LOG.info(team2.getRace() + " Win");
        }
        if (team2.isTeamDead()) {
            LOG.info(team1.getRace() + " Win");
        }
    }

}
