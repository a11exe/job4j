package ru.job4j.condition;

/**
 * Описывает точку в системе координат x и y.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 15.01.2019
 * @version 1
 */
public class Point {

    private int x;
    private int y;

    /**
     * Конструктор.
     * @param x значение по оси x.
     * @param y значение по оси y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Расстояние до переданной точки.
     * @param that - координаты точки до которой вычисляется расстояние.
     * @return - расстояние до точки that.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        // сделаем вызов метода
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }


}
