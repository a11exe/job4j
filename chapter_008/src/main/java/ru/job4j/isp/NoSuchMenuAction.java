package ru.job4j.isp;

public class NoSuchMenuAction extends RuntimeException {

    public NoSuchMenuAction() {
    }

    public NoSuchMenuAction(String message) {
        super(message);
    }
}
