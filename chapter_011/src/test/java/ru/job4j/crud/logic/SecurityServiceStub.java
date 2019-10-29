package ru.job4j.crud.logic;

import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
public class SecurityServiceStub implements SecurityService {

    private final User loggedUser;

    public SecurityServiceStub(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public User getLoggedUser(HttpSession session) {
        return this.loggedUser;
    }

    @Override
    public boolean hasEditRight(HttpSession session, User user) {
        User loggedUser = getLoggedUser(session);
        return loggedUser.isAdmin() || loggedUser.equals(user);
    }

    @Override
    public List<Role> getLoggedUserAvailableRoles(HttpSession session) {
        User loggedUser = getLoggedUser(session);
        List<Role> loggedUserAvailableRoles = new ArrayList<>();
        loggedUserAvailableRoles.add(loggedUser.getRole());
        return loggedUserAvailableRoles;
    }
}
