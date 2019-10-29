package ru.job4j.crud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.logic.ServletUtilImpl;
import ru.job4j.crud.logic.ServletUtil;
import ru.job4j.crud.logic.ServletUtilStub;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ServletUtilImpl.class, ValidateService.class})
public class UserCreateControllerTest {

    @Test
    public void whenAddUserThenStoreIt() throws IOException {

        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.mockStatic(ServletUtilImpl.class);

        Map<String, String> items = new HashMap<>();
        items.put("name", "Tedd");
        items.put("login", "Tedd");

        ServletUtil servletUtilStub = new ServletUtilStub(items, null, "");
        when(ServletUtilImpl.getInstance()).thenReturn(servletUtilStub);

        Validate validateStub = new ValidateStub();
        when(ValidateService.getInstance()).thenReturn(validateStub);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserCreateController controller = new UserCreateController();

        controller.doPost(req, resp);
        assertThat(validateStub.findAll().iterator().next().getName(), is("Tedd"));
    }

}

