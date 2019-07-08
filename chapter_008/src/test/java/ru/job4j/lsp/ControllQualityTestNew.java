package ru.job4j.lsp;


import org.junit.Before;
import org.junit.Test;
import ru.job4j.lsp.ControllQuality;
import ru.job4j.lsp.food.Corn;
import ru.job4j.lsp.food.Meat;
import ru.job4j.lsp.food.Milk;
import ru.job4j.lsp.storage.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.06.2019
 */
public class ControllQualityTestNew {

    private final LocalDate now = LocalDate.now();
    private List<Storage> storages;
    private Storage trash;
    private Storage reproduct;
    private Storage shop;
    private Storage warehouse;
    private Storage warehouseNew;
    private Storage refrigerator;

    private final Milk milkExpaireRate5 = new Milk("milk", now.plusDays(95), now.minusDays(5), new BigDecimal("35.58"), 0);
    private final Corn cornExpaireRate5 = new Corn("corn", now.plusDays(95), now.minusDays(5), new BigDecimal("35.58"), 0);
    private final Milk milkExpaireRate100 = new Milk("milk", now.plusDays(0), now.minusDays(100), new BigDecimal("35.58"), 0);
    private final Meat meatExpaireRate100 = new Meat("meat", now.plusDays(0), now.minusDays(100), new BigDecimal("35.58"), 0);


    @Before
    public void before() {
        trash = new Storage(new TrashNonReproductStrategy(new TrashStrategy()));
        reproduct = new Storage(new ReproductStrategy(new TrashStrategy()));
        shop = new Storage(new ShopStrategy());
        warehouse = new Storage(new SkipStrategy());
        warehouseNew = new Storage(new WarehouseNonRefrigeratorStrategy(new WarehouseStrategy()));
        refrigerator = new Storage(new RefrigeratorStrategy(new WarehouseStrategy()));

        storages = List.of(trash, shop, warehouse, warehouseNew, reproduct, refrigerator);
    }

    @Test
    public void whenExpaireRate5AndNonVegetableShouldAddToWarehouseNewWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate5);

        assertThat(milkExpaireRate5.getExpaireRate(), is(5));
        assertThat(milkExpaireRate5.getDiscount(), is(0));
        assertThat(warehouseNew.getFoods(), hasItem(milkExpaireRate5));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate5)));
    }

    @Test
    public void whenExpaireRate5AndVegetableShouldAddToRefrigeratorWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(cornExpaireRate5);

        assertThat(cornExpaireRate5.getExpaireRate(), is(5));
        assertThat(cornExpaireRate5.getDiscount(), is(0));
        assertThat(refrigerator.getFoods(), hasItem(cornExpaireRate5));
        assertThat(warehouseNew.getFoods(), not(hasItem(cornExpaireRate5)));
        assertThat(warehouse.getFoods(), not(hasItem(cornExpaireRate5)));
    }

    @Test
    public void whenExpaireRate100AndReproductShouldAddToReproductWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(milkExpaireRate100);

        assertThat(milkExpaireRate100.getExpaireRate(), is(100));
        assertThat(milkExpaireRate100.getDiscount(), is(0));
        assertThat(reproduct.getFoods(), hasItem(milkExpaireRate100));
        assertThat(trash.getFoods(), not(hasItem(milkExpaireRate100)));
    }

    @Test
    public void whenExpaireRate100AndNonReproductShouldAddToTrashWithoutDiscount() {
        ControllQuality controllQuality = new ControllQuality(storages);
        controllQuality.addToStorage(meatExpaireRate100);

        assertThat(meatExpaireRate100.getExpaireRate(), is(100));
        assertThat(meatExpaireRate100.getDiscount(), is(0));
        assertThat(trash.getFoods(), hasItem(meatExpaireRate100));
        assertThat(reproduct.getFoods(), not(hasItem(meatExpaireRate100)));
    }


}