package ru.job4j.game.utils;

import org.junit.Test;
import ru.job4j.game.Group;
import ru.job4j.game.Hero;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class LoadUtilTest {

    @Test
    public void whenLoadHeroesWhenShould4RacesLoaded() {
        List<Hero> heroes = new LoadUtil().loadHeroes(Group.REGULAR);
        assertThat(heroes.size(), is(12));
    }

    @Test
    public void whenLoadHeroesWhenShould12HeroesLoaded() {
        List<Hero> heroes = new LoadUtil().loadHeroes(Group.REGULAR);
        assertThat(heroes.stream().map(Hero::getRace).collect(Collectors.toSet()).size(), is(4));
    }


}