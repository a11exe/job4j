package ru.job4j.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class TeamTest {

    @Test
    public void whenLastHeroKilledWhenTeamIsDead() {
        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "стрелять из лука (нанесение урона 7 HP)",
                7,
                false,
                null,
                null);
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroFrom = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, defaultGroup, 100);
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 7);

        Team teamFrom = new Team(Race.ELF, new ArrayList<>(List.of(heroFrom)));
        Team teamTo = new Team(Race.MEN, new ArrayList<>(List.of(heroTo)));
        assertFalse(teamTo.isTeamDead());
        teamFrom.turn(teamTo);
        assertTrue(teamTo.isTeamDead());
    }

    @Test
    public void whenHeroImprovedWhenNextTurnIncreasedPowerAndHeroFromGroupBeRegular() {
        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "стрелять из лука (нанесение урона 7 HP)",
                7,
                false,
                null,
                null);
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroFrom = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, Group.EXTRA, 100);
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);

        Team teamFrom = new Team(Race.ELF, new ArrayList<>(List.of(heroFrom)));
        Team teamTo = new Team(Race.MEN, new ArrayList<>(List.of(heroTo)));
        teamFrom.turn(teamTo);
        assertThat(heroTo.getHealth(), is(90));
        assertThat(heroFrom.getGroup(), is(defaultGroup));
    }

    @Test
    public void whenHeroDiseasedWhenNextTurnDecreasedPowerAndHeroFromGroupBeRegular() {
        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "стрелять из лука (нанесение урона 7 HP)",
                7,
                false,
                null,
                null);
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroFrom = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, Group.DISEASED, 100);
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);

        Team teamFrom = new Team(Race.ELF, new ArrayList<>(List.of(heroFrom)));
        Team teamTo = new Team(Race.MEN, new ArrayList<>(List.of(heroTo)));
        teamFrom.turn(teamTo);
        assertThat(heroTo.getHealth(), is(97));
        assertThat(heroFrom.getGroup(), is(defaultGroup));
    }

    @Test
    public void whenHeroInExtraGroupWhenHeGoesFirst() {
        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "стрелять из лука (нанесение урона 7 HP)",
                7,
                false,
                null,
                null);
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroExtra = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, Group.DISEASED, 100);
        Hero heroRegular = new HeroImpl(Race.ELF, Skill.WIZARD, null, Group.REGULAR, 100);

        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);

        Team teamFrom = new Team(Race.ELF, new ArrayList<>(List.of(heroExtra, heroRegular)));
        Team teamTo = new Team(Race.MEN, new ArrayList<>(List.of(heroTo)));
        teamFrom.turn(teamTo);
        assertThat(heroTo.getHealth(), is(97));
    }

}