package ru.job4j.game;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.09.2019
 */
public interface Action {

    String getName();

    boolean isActionOnThisTeamHero();

    void doAction(Hero heroFrom, Hero heroTo);

    List<Group> groupsForAction();

}
