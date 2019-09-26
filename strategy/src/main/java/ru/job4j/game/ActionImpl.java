package ru.job4j.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.09.2019
 */
public class ActionImpl implements Action {

    private final String name;
    private final int damage;
    private final boolean actionOnThisTeamHero;
    private final Group group;
    private final List<Group> groupsForAction;
    private static final Logger LOG = LogManager.getLogger(ActionImpl.class);

    public ActionImpl(String name, int damage, boolean actionOnThisTeamHero, Group group, List<Group> groupsForAction) {
        this.name = name;
        this.damage = damage;
        this.actionOnThisTeamHero = actionOnThisTeamHero;
        this.group = group;
        this.groupsForAction = groupsForAction;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isActionOnThisTeamHero() {
        return this.actionOnThisTeamHero;
    }

    @Override
    public List<Group> groupsForAction() {
        return this.groupsForAction;
    }

    @Override
    public void doAction(Hero heroFrom, Hero heroTo) {
        int damageRatio = heroFrom.getGroup().getDamageRatio();
        int totalDamage = this.damage * damageRatio / 100;
        if (totalDamage > 0) {
            heroTo.takeDamage(totalDamage);
            LOG.info(heroTo.getName() + " take damage " + totalDamage + " health: " + heroTo.getHealth());
        }
        if (this.group != null) {
            moveHeroToGroup(heroTo, this.group);
        }
        if (!heroFrom.equals(heroTo)) {
            moveHeroToGroup(heroFrom, Group.REGULAR);
        }
    }

    private void moveHeroToGroup(Hero hero,  Group group) {
        if (!hero.getGroup().equals(group)) {
            hero.setGroup(group);
            LOG.info(hero.getName() + " set group " + group);
        }
    }
}
