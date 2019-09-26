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
public class ActionImplTest {

    @Test
    public void whenActionDamageWhenHeroHealthDecrease() {

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
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);
        action.doAction(heroFrom, heroTo);
        assertThat(heroTo.getHealth(), is(93));
    }

    @Test
    public void whenActionImproveWhenHeroMovedToExtraGroup() {

        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "наложение улучшения на персонажа своего отряда",
                0,
                true,
                Group.EXTRA,
                List.of(Group.REGULAR, Group.DISEASED));
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroFrom = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, defaultGroup, 100);
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);
        action.doAction(heroFrom, heroTo);
        assertThat(heroTo.getGroup(), is(Group.EXTRA));
    }

    @Test
    public void whenActionDiseasedWhenHeroMovedToDiseasedGroup() {

        Group defaultGroup = Group.REGULAR;
        Action action = new ActionImpl(
                "наслать недуг (уменьшение силы урона персонажа противника на 50% на один ход)",
                0,
                false,
                Group.DISEASED,
                List.of(Group.REGULAR, Group.EXTRA));
        List<Action> archerActions = new ArrayList<>();
        archerActions.add(action);
        Hero heroFrom = new HeroImpl(Race.ELF, Skill.ARCHER, archerActions, defaultGroup, 100);
        Hero heroTo = new HeroImpl(Race.MEN, Skill.ARCHER, null, defaultGroup, 100);
        action.doAction(heroFrom, heroTo);
        assertThat(heroTo.getGroup(), is(Group.DISEASED));
    }

}