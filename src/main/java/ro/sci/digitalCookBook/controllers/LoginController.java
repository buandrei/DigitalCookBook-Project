package ro.sci.digitalCookBook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.dao.db.JDBCEventsDAO;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("auth/login");

        return modelAndView;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("auth/access-denied");

        return modelAndView;
    }


    @RequestMapping("/onLogin")
    public ModelAndView onLogin(String username, String pass,
                                HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        ///use UserService to check the login
        boolean loginWithSuccess = true;
        if (loginWithSuccess) {
            ro.sci.digitalCookBook.domain.User user = new ro.sci.digitalCookBook.domain.User();
            user.setUserName(username);

            request.getSession().setAttribute("currentUser", user);
            modelAndView.setView(new RedirectView("/"));
        }

        return modelAndView;
    }

    @RequestMapping("/logout")
    public String onLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/";
    }
}
