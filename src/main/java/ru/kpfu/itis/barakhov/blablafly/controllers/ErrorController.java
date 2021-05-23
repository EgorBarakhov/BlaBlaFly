package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class ErrorController {

    private final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/error")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "HTTP Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "HTTP Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "HTTP Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "HTTP Error Code: 500. Internal Server Error";
                break;
            }
        }
        errorPage.addObject("message", errorMsg);
        LOG.error("Got an {} http error with message {}", httpErrorCode, errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

}
