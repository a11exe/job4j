package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* @author Alexandr Abramov (alllexe@mail.ru)
* @since 08.01.2019
* @version 1
*/
public class CalculateTest {
	/**
	* Test echo.
	*/
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "Petr Arsentev";
		String expect = "Echo, echo, echo : Petr Arsentev"; 
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}