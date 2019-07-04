package ru.lobj.lsp.food;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Milk
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class Milk extends Food {
    public Milk(String name, LocalDate expaireDate, LocalDate createDate, BigDecimal price, int discount) {
        super(name, expaireDate, createDate, price, discount);
    }

    @Override
    public boolean isCanReproduct() {
        return true;
    }

    @Override
    public boolean isVegetable() {
        return false;
    }
}
