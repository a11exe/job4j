package ru.job4j.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.09.2019
 */
public class Team {

    private final Race race;
    private final Map<Integer, List<Group>> groupsByOrder;
    private final List<Hero> heroes;
    private static final Logger LOG = LogManager.getLogger(Team.class);


    public Team(Race race, List<Hero> heroes) {
        this.race = race;
        this.heroes = heroes;
        this.groupsByOrder = new TreeMap<>();
        initGroupsByOrder();
    }

    private void initGroupsByOrder() {
        this.groupsByOrder.clear();
        Set<Group> groups = heroes.stream().map(Hero::getGroup).collect(Collectors.toSet());
        groups.forEach(group -> groupsByOrder.putIfAbsent(group.getOrder(), new ArrayList<>()));
        groups.forEach(group -> groupsByOrder.get(group.getOrder()).add(group));
    }


    public void turn(Team teamEnemy) {
        if (isTeamDead()) {
            return;
        }
        initGroupsByOrder();
        Hero hero = getHeroForActionByOrder();
        if (hero == null) {
            LOG.info("skip turn");
            return;
        }
        Action action = hero.getRandomAction();
        List<Group> groupsForAction = action.groupsForAction();
        LOG.info(hero.getName() + " " + action.getName());
        if (action.isActionOnThisTeamHero()) {
            performActionHero(hero, getRandomHero(groupsForAction), action, this);
        } else {
            performActionHero(hero, teamEnemy.getRandomHero(groupsForAction), action, teamEnemy);
        }
    }

    private void performActionHero(Hero heroFrom, Hero heroTo, Action action, Team team) {
        action.doAction(heroFrom, heroTo);
        if (heroTo.isDead()) {
            LOG.info("+++++++++ " + heroTo.getName() + " is dead");
            team.removeHero(heroTo);
        }
    }

    private Hero getHeroForActionByOrder() {
        Hero hero = null;
        for (Map.Entry<Integer, List<Group>> entry: groupsByOrder.entrySet()
             ) {
            hero = getRandomHero(entry.getValue());
            if (hero != null) {
                break;
            }
        }
        return hero;
    }

    public boolean isTeamDead() {
        return heroes.size() == 0;
    }

    private Hero getRandomHero(List<Group> groups) {
        Hero hero;
        List<Hero> heroesForSearch = heroes;
        if (groups != null) {
            heroesForSearch = heroes.stream().filter(h -> groups.contains(h.getGroup())).collect(Collectors.toList());
        }
        int randomNum = heroesForSearch.size() == 1 ? 0 : ThreadLocalRandom.current().nextInt(0, heroesForSearch.size() - 1);
        hero = heroesForSearch.get(randomNum);
        if (hero == null) {
            LOG.info("cant find hero in groups " + groups);
        }
        return hero;
    }

    private void removeHero(Hero hero) {
        this.heroes.remove(hero);
    }

    public Race getRace() {
        return race;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }
}
