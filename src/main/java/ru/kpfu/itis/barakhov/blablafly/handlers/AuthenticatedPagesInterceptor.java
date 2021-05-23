//package ru.kpfu.itis.barakhov.blablafly.handlers;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.util.UrlPathHelper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//
//public class AuthenticatedPagesInterceptor implements HandlerInterceptor {
//    private UrlPathHelper urlPathHelper = new UrlPathHelper();
//
//    private final Logger LOG = LoggerFactory.getLogger(AuthenticatedPagesInterceptor.class);
//
//    private final String AUTHENTICATED_PATH = "/flights";
//
//    private final String[] HOME_PAGES = new String[]{
//            "/",
//            "/signup",
//            "/login"
//    };
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if (isAuthenticated() && requestedHomePages(request)) {
//            LOG.info("User is authenticated but trying to access home page, redirecting to /flights");
//            String encodedRedirectURL = response.encodeRedirectURL(
//                    request.getContextPath() + AUTHENTICATED_PATH);
//            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
//            response.setHeader("Location", encodedRedirectURL);
//
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean isAuthenticated() {
//        return SecurityContextHolder.getContext().getAuthentication() != null
//                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//    }
//
//    private boolean requestedHomePages(HttpServletRequest httpServletRequest) {
//        return Arrays.asList(HOME_PAGES).contains(httpServletRequest.getRequestURI());
//    }
//}
