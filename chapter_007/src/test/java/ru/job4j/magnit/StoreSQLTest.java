package ru.job4j.magnit;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class StoreSQLTest {

    @Test
    public void whenGenerate1000ShouldBe1000() {
        Config config = new Config();
        config.init();

        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(1000);
        List<Entry> list = storeSQL.load();
        assertThat(list.size(), is(1000));
    }

}