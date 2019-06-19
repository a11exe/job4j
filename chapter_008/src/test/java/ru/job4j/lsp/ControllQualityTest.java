package ru.job4j.lsp;


import org.junit.Before;
import org.junit.Test;
import ru.lobj.lsp.ControllQuality;
import ru.lobj.lsp.food.Milk;
import ru.lobj.lsp.storage.ShopStrategy;
import ru.lobj.lsp.storage.Storage;
import ru.lobj.lsp.storage.TrashStrategy;
import ru.lobj.lsp.storage.WarehouseStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.06.2019
 */
public class ControllQualityTest {

    private final LocalDate now = LocalDate.now();
    private List<Storage> storages;
    private Storage trash;
    private Storage shop;
    private Storage warehouse;

    private final Milk milkExpaireRate5 = new Milk("milk", now.plusDays(95), now.minusDays(5), new BigDecimal("35.58"), 0);
    private final Milk milkExpaireRate25 = new Milk("milk", now.plusDays(75), now.minusDays(25), new BigDecimal("35.58"), 0);
    private final Milk milkExpaireRate75 = new Milk("milk", now.plusDays(25), now.minusDays(75), new BigDecimal("35.58"), 0);
    private final Milk milkExpaireRate76 = new Milk("milk", now.plusDays(24), now.minusDays(76), new BigDecimal("35.58"), 0);
    private final Milk milkExpaireRate100 = new Milk("milk", now.plusDays(0), now.minusDays(100), new BigDecimal("35.58"), 0);
    private final Milk milkExpaired = new Milk("milk", now.minusDays(1), now.minusDays(101), new BigDecimal("35.58"), 0);


    @Before
    public void before() {
        trash = new Storage(new TrashStrategy());
        shop = new Storage(new ShopStrategy());
        warehouse = new Storage(new WarehouseStrategy());
        storages = List.of(trash, shop, warehouse);
    }

    @Test
    public void whenExpaireRate76ShouldAddToShopWithDiscount5() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate76);

        assertThat(milkExpaireRate76.getExpaireRate(), is(76));
        assertThat(milkExpaireRate76.getDiscount(), is(5));
        assertThat(shop.getFoods(), hasItem(milkExpaireRate76));
        assertThat(trash.getFoods(), not(hasItem(milkExpaireRate76)));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate76)));
    }

    @Test
    public void whenExpaireRate75ShouldAddToShopWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate75);

        assertThat(milkExpaireRate75.getExpaireRate(), is(75));
        assertThat(milkExpaireRate75.getDiscount(), is(0));
        assertThat(shop.getFoods(), hasItem(milkExpaireRate75));
        assertThat(trash.getFoods(), not(hasItem(milkExpaireRate75)));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate75)));
    }

    @Test
    public void whenExpaireRate25ShouldAddToShopWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate25);

        assertThat(milkExpaireRate25.getExpaireRate(), is(25));
        assertThat(milkExpaireRate25.getDiscount(), is(0));
        assertThat(shop.getFoods(), hasItem(milkExpaireRate25));
        assertThat(trash.getFoods(), not(hasItem(milkExpaireRate25)));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate25)));
    }

    @Test
    public void whenExpaireRate5ShouldAddToWarehouseWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate5);

        assertThat(milkExpaireRate5.getExpaireRate(), is(5));
        assertThat(milkExpaireRate5.getDiscount(), is(0));
        assertThat(warehouse.getFoods(), hasItem(milkExpaireRate5));
        assertThat(trash.getFoods(), not(hasItem(milkExpaireRate5)));
        assertThat(shop.getFoods(), not(hasItem(milkExpaireRate5)));
    }

    @Test
    public void whenExpaireRate100ShouldAddToTrashWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate100);

        assertThat(milkExpaireRate100.getExpaireRate(), is(100));
        assertThat(milkExpaireRate100.getDiscount(), is(0));
        assertThat(trash.getFoods(), hasItem(milkExpaireRate100));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate100)));
        assertThat(shop.getFoods(), not(hasItem(milkExpaireRate100)));
    }

    @Test
    public void whenExpairedShouldAddToTrashWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaired);

        assertThat(milkExpaired.getExpaireRate(), is(100));
        assertThat(milkExpaired.getDiscount(), is(0));
        assertThat(trash.getFoods(), hasItem(milkExpaired));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaired)));
        assertThat(shop.getFoods(), not(hasItem(milkExpaired)));
    }




}