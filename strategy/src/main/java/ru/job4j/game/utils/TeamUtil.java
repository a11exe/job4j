package ru.job4j.game.utils;

import ru.job4j.game.Hero;
import ru.job4j.game.HeroImpl;
import ru.job4j.game.Race;
import ru.job4j.game.Skill;
import ru.job4j.game.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.09.2019
 */
public class TeamUtil {

    private final Team team1;
    private final Team team2;

    public TeamUtil(List<Hero> heroes) {

        Set<Race> races = getRaces(heroes);
        Race team1Race = getRandomTeam1Race(races);
        Race team2Race = getRandomTeam2Race(races, team1Race);

        List<Hero> team1Heroes = new ArrayList<>();
        team1Heroes.add(findHero(heroes, team1Race, Skill.WIZARD));
        for (int i = 0; i < 3; i++) {
            team1Heroes.add(findHero(heroes, team1Race, Skill.ARCHER));
        }
        for (int i = 0; i < 4; i++) {
            team1Heroes.add(findHero(heroes, team1Race, Skill.FIGHTER));
        }

        List<Hero> team2Heroes = new ArrayList<>();
        team2Heroes.add(findHero(heroes, team2Race, Skill.WIZARD));
        for (int i = 0; i < 3; i++) {
            team2Heroes.add(findHero(heroes, team2Race, Skill.ARCHER));
        }
        for (int i = 0; i < 4; i++) {
            team2Heroes.add(findHero(heroes, team2Race, Skill.FIGHTER));
        }


        this.team1 = new Team(team1Race, team1Heroes);
        this.team2 = new Team(team2Race, team2Heroes);
    }

    private Hero findHero(List<Hero> heroes, Race race, Skill skill) {
        Hero prototype = heroes.stream()
                .filter(hero -> hero.getSkill().equals(skill))
                .filter(hero -> hero.getRace().equals(race))
                .findFirst().orElse(null);
        return new HeroImpl(Objects.requireNonNull(prototype));
    }

    private Set<Race> getRaces(List<Hero> heroes) {
        Set<Race> races = new HashSet<>();
        heroes.forEach(hero -> races.add(hero.getRace()));
        return races;
    }

    private Race getRandomTeam1Race(Set<Race> races) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, races.size() - 1);
        return new ArrayList<>(races).get(randomNum);
    }

    private Race getRandomTeam2Race(Set<Race> races, Race team1Race) {
        List<Race> opponentRaces = races.stream().filter(race -> race.getI() != team1Race.getI()).collect(Collectors.toList());
        int randomNum = ThreadLocalRandom.current().nextInt(0, opponentRaces.size() - 1);
        return opponentRaces.get(randomNum);
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
}
