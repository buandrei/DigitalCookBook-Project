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

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("user/list");
        Collection<User> user = userService.listAll();
        result.addObject("user", user);
        return result;
    }

    @RequestMapping("/yourlist")
    public ModelAndView yourlist() {
        ModelAndView result = new ModelAndView("user/yourlist");
        Collection<User> user = userService.listAllByUser();
        result.addObject("user", user);
        return result;
    }


    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("user/add");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("user/add");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView admin(Integer id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("user/admin");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid User user,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                userService.save(user);
                RedirectView redirectView = new RedirectView("/");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("user/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("user", user);
            }

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
