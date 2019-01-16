package ru.job4j.condition;

/**
 * Вычисление площади треугольника.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 16.01.2019
 * @version 1
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     *
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Полупериметр.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод должен вычислить площадь треугольника.
     *
     * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1; // мы устанавливаем значение -1, так как может быть что треугольника нет. Это значение говорит о том. что треугольника нет.
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            // написать формулу для расчета площади треугольника.
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     * Подумайте какое надо написать условие, чтобы определить можно ли построить треугольник.
     *
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return Возможно построить треугольник со сторанами длинной ab, ac, bc
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab);
    }
}
