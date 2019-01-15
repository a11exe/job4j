package ru.job4j.converter;

/**
 * Конвертор валюты.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 15.01.2019
 * @version 1
 */
public class Converter {

    private final int rubleToEuroExchangeRate = 70;
    private final int rubleToDollarExchangeRate = 60;

    /**
     * Конвертируем рубли в евро
     * @param value рубли
     * @return евро
     */
    public int rubleToEuro(int value) {
        return  value / this.rubleToEuroExchangeRate;
    }

    /**
     * Конвертируем рубли в долллары
     * @param value рубли
     * @return доллары
     */
    public int rubleToDollar(int value) {
        return value / this.rubleToDollarExchangeRate;
    }

    /**
     * Конвертируем евро в рубли
     * @param value евро
     * @return рубли
     */
    public int euroToRuble(int value) {
        return value * this.rubleToEuroExchangeRate;
    }

    /**
     * Конвертируем доллары в рубли
     * @param value доллары
     * @return рубли
     */
    public int dollarToRuble(int value) {
        return value * this.rubleToDollarExchangeRate;
    }

}
