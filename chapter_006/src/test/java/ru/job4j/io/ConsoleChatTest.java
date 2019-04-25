package ru.job4j.io;

import org.junit.*;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.04.2019
 */
public class ConsoleChatTest {

    @Test
    public void when3QuestionsShould5Lines() {
        MockLogger logger = new MockLogger(List.of(
                "Привет",
                "Как дела",
                "закончить"
        ));
        ConsoleChat consoleChat = new ConsoleChat(logger);
        consoleChat.init();
        Stream<String> stream = logger.getLog().lines();
        assertThat(stream.count(), is(5L));
    }

    @Test
    public void when4QuestionsAndStopWordShould5Lines() {
        MockLogger logger = new MockLogger(List.of(
                "Привет",
                "стоп",
                "Как дела",
                "закончить"
        ));
        ConsoleChat consoleChat = new ConsoleChat(logger);
        consoleChat.init();
        Stream<String> stream = logger.getLog().lines();
        assertThat(stream.count(), is(5L));
    }

    @Test
    public void whenFinishWordShould1Line() {
        MockLogger logger = new MockLogger(List.of(
                "закончить",
                "Опа",
                "Как дела",
                "закончить"
        ));
        ConsoleChat consoleChat = new ConsoleChat(logger);
        consoleChat.init();
        Stream<String> stream = logger.getLog().lines();
        assertThat(stream.count(), is(1L));
    }

    @Test
    public void when6QuestionsStopAndContinueWordShould9Line() {
        MockLogger logger = new MockLogger(List.of(
                "Привет",
                "Опа",
                "стоп",
                "мой ход",
                "продолжить",
                "закончить"
        ));
        ConsoleChat consoleChat = new ConsoleChat(logger);
        consoleChat.init();
        Stream<String> stream = logger.getLog().lines();
        assertThat(stream.count(), is(9L));
    }

}