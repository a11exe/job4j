package ru.job4j.coffeemachine;

import java.util.*;

/**
 * Created by abramov_av.sit on 25.02.2019.
 */
public class CoffeeMachine {

    private final Map<Coin, Integer> coins = new TreeMap<>();

    /**
     * Создает кофе машину с монетами для сдачи.
     * @param coins - массив с номиналами монет.
     */
    public CoffeeMachine(Integer... coins) {
        Arrays.stream(coins).forEach(this::addCoin);
    }

    /**
     * Добавить монеты для сдачи в кофемашину.
     * @param nominal - номинал монеты.
     */
    public void addCoin(int nominal) {
       this.coins.merge(new Coin(nominal), 1, (oldVal, newVal) -> oldVal + newVal);
    }

    /**
     * Вычисляет сдачу.
     * @param value - Внесенная сумма.
     * @param price - Цена продукта.
     * @return - массив с наименьшим количеством монет для сдачи.
     * @throws NotEnoughMoney - внесенной суммы недостаточно для операции.
     * @throws NoCoinsForChange - нет монет для выдачи сдачи.
     */
    public int[] changes(int value, int price) throws NotEnoughMoney, NoCoinsForChange {
        int[] coins = new int[]{};
        List<Coin> coinsList = new ArrayList<>();
        int change = value - price;
        if (change < 0) {
            throw new NotEnoughMoney();
        }
        if (change > 0) {
            for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
                if (change / entry.getKey().getNominal() > 0 && entry.getValue() > 0) {
                    int availableCoins = Math.min(change / entry.getKey().getNominal(), entry.getValue());
                    for (int i = 0; i < availableCoins; i++) {
                        coinsList.add(entry.getKey());
                    }
                    change = change - availableCoins * entry.getKey().getNominal();
                }
            }
            if (change > 0) {
                throw new NoCoinsForChange();
            }
            if (change == 0) {
                coinsList.forEach(coin -> this.coins.put(coin, this.coins.get(coin) - 1));
                coins = coinsList.stream().mapToInt(Coin::getNominal).toArray();
            }
        }
        return coins;
    }

    private class Coin implements Comparable<Coin> {

        private final int nominal;

        public Coin(int nominal) {
            this.nominal = nominal;
        }

        @Override
        public int compareTo(Coin o) {
            return o.getNominal() - this.getNominal();
        }

        public int getNominal() {
            return nominal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Coin)) {
                return false;
            }
            Coin coin = (Coin) o;
            return nominal == coin.nominal;
        }

        @Override
        public int hashCode() {

            return Objects.hash(nominal);
        }
    }
}
