package ru.job4j.game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.09.2019
 */
public class HeroImpl implements Hero {

    private final Race race;
    private final Skill skill;
    private final List<Action> actions;
    private Group group;
    private int health;

    public HeroImpl(Race race, Skill skill, List<Action> actions, Group group, int health) {
        this.race = race;
        this.skill = skill;
        this.actions = actions;
        this.group = group;
        this.health = health;
    }

    public HeroImpl(Hero hero) {
        this.race = hero.getRace();
        this.skill = hero.getSkill();
        this.actions = hero.getActions();
        this.group = hero.getGroup();
        this.health = hero.getHealth();
    }

    @Override
    public String getName() {
        return "" + getRace() + " " + getSkill() + " " + hashCode();
    }

    @Override
    public Race getRace() {
        return this.race;
    }

    @Override
    public Skill getSkill() {
        return this.skill;
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

    @Override
    public Action getRandomAction() {
        int randomNum = actions.size() == 1 ? 0 : ThreadLocalRandom.current().nextInt(0, actions.size() - 1);
        return actions.get(randomNum);
    }

    @Override
    public void takeDamage(int damage) {
        this.health = this.health <= damage ? 0 : this.health - damage;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public boolean isDead() {
        return this.health <= 0;
    }
}
