package ru.kpfu.itis.barakhov.blablafly.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.kpfu.itis.barakhov.blablafly.controllers.AdminController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthenticatedFilter extends GenericFilterBean {
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private final String[] HOME_PAGES = new String[]{
            "/",
            "/signup",
            "/login"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isAuthenticated() && requestedHomePages((HttpServletRequest) servletRequest)) {
            LOG.info("User is authenticated but trying to access home page, redirecting to /flights");
            ((HttpServletResponse) servletResponse).sendRedirect("/flights");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    private boolean requestedHomePages(HttpServletRequest httpServletRequest) {
        return Arrays.asList(HOME_PAGES).contains(httpServletRequest.getRequestURI());
    }

}
