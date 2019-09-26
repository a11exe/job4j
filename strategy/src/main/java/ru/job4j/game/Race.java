package ru.job4j.game;

public enum Race {
    ELF(false),
    ORC(true),
    MEN(false),
    ZOMBIE(true);

    boolean i;

    Race(boolean i) {
        this.i = i;
    }

    public boolean getI() {
        return i;
    }
}
