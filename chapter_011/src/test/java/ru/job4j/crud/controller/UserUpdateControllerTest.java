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
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class, ServletUtilImpl.class, SecurityServiceImpl.class})
public class UserUpdateControllerTest {

    @Test
    public void whenUpdateUserThenUpdateIt() throws IOException {

        PowerMockito.mockStatic(SecurityServiceImpl.class);
        PowerMockito.mockStatic(ServletUtilImpl.class);

        User user = new User.Builder().withId(1).withName("Tedd").withRole(Role.ADMIN).build();
        SecurityService securityService = new SecurityServiceStub(user);
        when(SecurityServiceImpl.getInstance()).thenReturn(securityService);

        Validate validateStub = new ValidateStub();
        validateStub.add(user, null, "");

        Map<String, String> items = new HashMap<>();
        items.put("id", "1");
        items.put("name", "Tedd2");
        items.put("login", "Tedd");

        ServletUtil servletUtilStub = new ServletUtilStub(items, null, "");
        when(ServletUtilImpl.getInstance()).thenReturn(servletUtilStub);

        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validateStub);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserUpdateController controller = new UserUpdateController();

        assertThat(validateStub.findAll().iterator().next().getName(), is("Tedd"));
        controller.doPost(req, resp);
        assertThat(validateStub.findAll().iterator().next().getName(), is("Tedd2"));
    }
}