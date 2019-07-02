package ru.lobj.lsp.food;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Meat
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class Meat extends Food {
    public Meat(String name, LocalDate expaireDate, LocalDate createDate, BigDecimal price, int discount) {
        super(name, expaireDate, createDate, price, discount);
    }
}
