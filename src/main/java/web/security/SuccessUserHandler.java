package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    private final UserDao userDao;

    @Autowired
    public SuccessUserHandler(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/" + userDao.getUserByName(authentication.getName()).getId());
        } else {
            httpServletResponse.sendRedirect("/admin");
        }
    }
}
