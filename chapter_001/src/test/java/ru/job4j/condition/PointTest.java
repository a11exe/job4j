package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 15.01.2019
 * @version 1
 */
public class PointTest {

    /**
     *  Test distanceTo
     */
    @Test
    public void distanceToPoint() {
        Point point = new Point(1,1);
        assertThat(point.distanceTo(new Point(3,8)),closeTo(7.28,0.01));
    }

}
