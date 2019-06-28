package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.isp.action.HelloAction;
import ru.job4j.isp.action.PrintAction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public class BulletMenuTest {

    private BulletActionMenu root;
    private BulletActionMenu bullet1;
    private BulletActionMenu bullet11;
    private BulletActionMenu bullet12;

    @Before
    public void initMenu() {
        root = new BulletActionMenu(new SimpleMenu("root (1)"), new PrintAction());
        bullet1 = new BulletActionMenu(new SimpleMenu("bullet (1.1)"), new PrintAction());
        bullet11 = new BulletActionMenu(new SimpleMenu("bullet (1.1.1)"), new HelloAction());
        bullet12 = new BulletActionMenu(new SimpleMenu("bullet (1.1.2)"), null);
        BulletActionMenu bullet2 = new BulletActionMenu(new SimpleMenu("bullet (1.2)"), new PrintAction());
        BulletActionMenu bullet21 = new BulletActionMenu(new SimpleMenu("bullet (1.2.1)"), new HelloAction());
        BulletActionMenu bullet3 = new BulletActionMenu(new SimpleMenu("bullet (1.3)"), null);
        BulletActionMenu bullet31 = new BulletActionMenu(new SimpleMenu("bullet (1.3.1)"), new PrintAction());
        BulletActionMenu bullet32 = new BulletActionMenu(new SimpleMenu("bullet (1.3.2)"), new PrintAction());
        BulletActionMenu bullet33 = new BulletActionMenu(new SimpleMenu("bullet (1.3.3)"), new PrintAction());

        root.addMenuItem(bullet1);
        root.addMenuItem(bullet2);
        root.addMenuItem(bullet3);
        bullet1.addMenuItem(bullet11);
        bullet1.addMenuItem(bullet12);
        bullet2.addMenuItem(bullet21);
        bullet3.addMenuItem(bullet31);
        bullet3.addMenuItem(bullet32);
        bullet3.addMenuItem(bullet33);
    }

    @Test
    public void when3LevelBulletMenuShouldPrint3LevelWithBullet() {

        String actual = root.getFullMenu();

        String expected = "root (1) 1"
                + "-- bullet (1.1) 1.1"
                + "---- bullet (1.1.1) 1.1.1"
                + "---- bullet (1.1.2) 1.1.2"
                + "-- bullet (1.2) 1.2"
                + "---- bullet (1.2.1) 1.2.1"
                + "-- bullet (1.3) 1.3"
                + "---- bullet (1.3.1) 1.3.1"
                + "---- bullet (1.3.2) 1.3.2"
                + "---- bullet (1.3.3) 1.3.3";

        assertThat(actual, is(expected));

    }

    @Test
    public void whenDoActionShouldDoRightAction() {
        ByteArrayOutputStream br = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(br);
        System.setOut(ps);

        bullet1.doAction();
        assertThat(br.toString(), is("some print action" + System.lineSeparator()));
        br.reset();

        bullet11.doAction();
        assertThat(br.toString(), is("hello" + System.lineSeparator()));
        br.reset();

        System.setOut(System.out);
    }

    @Test(expected = NoSuchMenuAction.class)
    public void whenNoActionShouldException() {
        bullet12.doAction();
    }
}