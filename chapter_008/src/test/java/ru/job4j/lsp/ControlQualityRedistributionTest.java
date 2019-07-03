package ru.job4j.lsp;

import org.junit.Test;
import ru.lobj.lsp.ControllQuality;
import ru.lobj.lsp.food.Milk;
import ru.lobj.lsp.storage.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 02.07.2019
 */
public class ControlQualityRedistributionTest {

    private final LocalDate now = LocalDate.now();
    private List<Storage> storages;
    private Storage warehouse;
    private Storage warehouseNew;

    private final Milk milkExpaireRate5 = new Milk("milk", now.plusDays(95), now.minusDays(5), new BigDecimal("35.58"), 0);


    @Test
    public void whenExpaireRate5AndNonVegetableShouldAddToWarehouseNewWithoutDiscount() {

        warehouse = new Storage(new SkipStrategy());
        warehouseNew = new Storage(new WarehouseNonRefrigeratorStrategy(new WarehouseStrategy()));
        storages = List.of(warehouse, warehouseNew);

        ControllQuality controllQuality = new ControllQuality(storages);

        controllQuality.addToStorage(milkExpaireRate5);
        assertThat(warehouseNew.getFoods(), hasItem(milkExpaireRate5));
        assertThat(warehouse.getFoods(), not(hasItem(milkExpaireRate5)));

        warehouse.setStorageStrategy(new WarehouseNonRefrigeratorStrategy(new WarehouseStrategy()));
        warehouseNew.setStorageStrategy(new SkipStrategy());

        controllQuality.resort();
        assertThat(warehouseNew.getFoods(), not(hasItem(milkExpaireRate5)));
        assertThat(warehouse.getFoods(), hasItem(milkExpaireRate5));


    }

}
