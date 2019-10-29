package ru.job4j.crud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.logic.*;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 16.10.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ServletUtilImpl.class, ValidateService.class})
public class UserDeleteControllerTest {

    @Test
    public void whenDeleteUserThenDeleteIt() throws IOException {

        PowerMockito.mockStatic(ServletUtilImpl.class);

        User user = new User.Builder().withId(1).withName("Tedd").withRole(Role.ADMIN).build();
        Validate validateStub = new ValidateStub();
        validateStub.add(user, null, "");

        ServletUtil servletUtilStub = new ServletUtilStub(new HashMap<>(), null, "");
        when(ServletUtilImpl.getInstance()).thenReturn(servletUtilStub);

        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validateStub);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("1");

        UserDeleteController controller = new UserDeleteController();

        assertThat(validateStub.findAll().iterator().next().getName(), is("Tedd"));
        controller.doGet(req, resp);
        assertThat(validateStub.findAll().size(), is(0));
    }

}