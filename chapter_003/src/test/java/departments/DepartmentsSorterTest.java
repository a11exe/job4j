package departments;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 12.03.2019
 */
public class DepartmentsSorterTest {

    @Test
    public void sortDepatmentsAcs() {
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        String[] actual = new DepartmentsSorter(String::compareTo).sort(departments);
        String[] expected = {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void sortDepatmentsDesc() {
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        String[] actual = new DepartmentsSorter((Comparator.reverseOrder())).sort(departments);
        String[] expected = {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSortOneStringDepatmentsAcs() {
        String[] departments = {
                "K1\\SK1\\SSK1"
        };
        String[] actual = new DepartmentsSorter(String::compareTo).sort(departments);
        String[] expected = {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSortTwoStringDepatmentsDesc() {
        String[] departments = {
                "K1\\SK1\\SSK1",
                "K1\\SK2",
        };
        String[] actual = new DepartmentsSorter(Comparator.reverseOrder()).sort(departments);
        String[] expected = {
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSortFiveLevelDepatmentsDesc() {
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK2\\SSSK1",
                "K2",
                "K2\\SK1\\SSK2\\SSSK1"
        };
        String[] actual = new DepartmentsSorter((Comparator.reverseOrder())).sort(departments);
        String[] expected = {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK2\\SSSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK2\\SSSK1",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
    }
}
