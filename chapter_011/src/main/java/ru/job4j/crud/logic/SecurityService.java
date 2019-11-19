package ru.job4j.crud.logic;

import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SecurityService {

    User getLoggedUser(HttpSession session);

    boolean hasEditRight(HttpSession session, User user);

    List<Role> getLoggedUserAvailableRoles(HttpSession session);

}
