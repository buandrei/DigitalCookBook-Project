package ro.sci.digitalCookBook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.domain.User;
import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger("UserController");

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("user/list");


        Collection<User> users = userService.listAll();
        result.addObject("users", users);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("user/add");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping("/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("user/view");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(int id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("user/add");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        userService.delete(id);
        return "redirect:/employee";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid User user,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            userService.save(user);
            RedirectView redirectView = new RedirectView("/user");
            modelAndView.setView(redirectView);

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }

            modelAndView = new ModelAndView("user/add");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("user", user);
        }

        return modelAndView;
    }
}
