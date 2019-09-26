package ru.job4j.game;

public enum Group {

    REGULAR(2, 100),
    DISEASED(2, 50),
    EXTRA(1, 150);

    private final Integer order;
    private final Integer damageRatio;

    Group(Integer order, Integer damageRatio) {
        this.order = order;
        this.damageRatio = damageRatio;
    }

    public Integer getDamageRatio() {
        return damageRatio;
    }

    public Integer getOrder() {
        return order;
    }

}
