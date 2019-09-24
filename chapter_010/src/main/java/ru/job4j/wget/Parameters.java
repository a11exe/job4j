package ru.job4j.wget;

import java.net.URL;
/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class Parameters {
    URL url;
    int maxSpeed;

    Parameters(URL url, int maxSpeed) {
        this.url = url;
        this.maxSpeed = maxSpeed * 1000;
    }
}
