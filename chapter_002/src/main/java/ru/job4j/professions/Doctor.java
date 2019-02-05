package ru.job4j.professions;

/**
 * Доктор
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 31.01.2019
 */
public class Doctor extends Profession {

    public void cure(Patient patient) {

    }

    public Diagnose heal(Patient patient) {
        return new Diagnose();
    }

}
