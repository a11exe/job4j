package ru.lobj.lsp.food;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Food
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class Food {

    private String name;
    private LocalDate expaireDate;
    private LocalDate createDate;
    private BigDecimal price;
    private int discount;

    public Food(String name, LocalDate expaireDate, LocalDate createDate, BigDecimal price, int discount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpaireDate() {
        return expaireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getExpaireRate() {
        int expaireRate;
        LocalDate now = LocalDate.now();

        long remainStorageDays = Duration.between(now.atStartOfDay(), getExpaireDate().atStartOfDay()).toDays();
        remainStorageDays = remainStorageDays < 0 ? 0 : remainStorageDays;

        long storageDays = Duration.between(getCreateDate().atStartOfDay(), getExpaireDate().atStartOfDay()).toDays();
        storageDays = storageDays <= 0 ? 1 : storageDays;

        expaireRate = 100 - Math.toIntExact(remainStorageDays * 100 / storageDays);

        return expaireRate;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expaireDate=" + expaireDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
