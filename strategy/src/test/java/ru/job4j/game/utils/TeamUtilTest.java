package ru.job4j.game.utils;

import org.junit.Test;
import ru.job4j.game.Group;
import ru.job4j.game.Hero;
import ru.job4j.game.Race;
import ru.job4j.game.Skill;
import ru.job4j.game.Team;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class TeamUtilTest {

    @Test
    public void whenMakeTeamsWhenTeamsShouldBeOpponents() {
        List<Hero> heroes = new LoadUtil().loadHeroes(Group.REGULAR);
        TeamUtil teamUtil = new TeamUtil(heroes);

        Team team1 = teamUtil.getTeam1();
        Team team2 = teamUtil.getTeam2();

        assertTrue(team1.getRace().getI() != team2.getRace().getI());
    }

    @Test
    public void whenMakeTeamsWhenTeamsShouldBe1Wizard3Archer4FighterInEachTeam() {
        List<Hero> heroes = new LoadUtil().loadHeroes(Group.REGULAR);
        TeamUtil teamUtil = new TeamUtil(heroes);

        Team team1 = teamUtil.getTeam1();
        Team team2 = teamUtil.getTeam2();

        assertThat(team1.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.WIZARD)).count(), is(1L));
        assertThat(team2.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.WIZARD)).count(), is(1L));
        assertThat(team1.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.ARCHER)).count(), is(3L));
        assertThat(team2.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.ARCHER)).count(), is(3L));
        assertThat(team1.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.FIGHTER)).count(), is(4L));
        assertThat(team2.getHeroes().stream().filter(hero -> hero.getSkill().equals(Skill.FIGHTER)).count(), is(4L));
    }

    @Test
    public void whenTeamEmptyWhenTeamIsDead() {
        Team team = new Team(Race.ELF, new ArrayList<>());
        assertTrue(team.isTeamDead());
    }

}