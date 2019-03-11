package students;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.03.2019
 */
public class StudentTest {

    @Test
    public void levelOf() {

        Student student1 = new Student("Bob", 3);
        Student student2 = new Student("Bill", 1);
        Student student3 = new Student("Anita", 2);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(null);
        students.add(student2);
        students.add(null);
        students.add(student3);
        students.add(null);

        List<Student> expected = List.of(student1, student3);
        List<Student> actual = student1.levelOf(students, 1);
        actual.forEach(s->System.out.println(s.getName()));

        assertThat(student1.levelOf(students, 1), is(expected));
    }
}