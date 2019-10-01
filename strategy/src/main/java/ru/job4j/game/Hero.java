package ru.job4j.game;

import java.util.List;

public interface Hero {

    String getName();

    Race getRace();

    Skill getSkill();

    List<Action> getActions();

    Action getRandomAction();

    void takeDamage(int damage);

    Group getGroup();

    void setGroup(Group group);

    int getHealth();

    boolean isDead();

}
