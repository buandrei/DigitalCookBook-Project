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
import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.RecipeService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/retete")
public class RecipeController {

    private static Logger LOGGER = LoggerFactory.getLogger("Recipe Controller");

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("retete/list_all");


        Collection<Recipe> recipes = recipeService.listAll();
        result.addObject("recipes", recipes);

        return result;
    }

    @RequestMapping("/upload_recipe")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("recipe/upload_recipe");
        modelAndView.addObject("recipe", new Recipe());
        return modelAndView;
    }
//
//    @RequestMapping("/view")
//    public ModelAndView view() {
//        ModelAndView modelAndView = new ModelAndView("employee/view");
//        modelAndView.addObject("employee", new Employee());
//        return modelAndView;
//    }
//
//    @RequestMapping("/edit")
//    public ModelAndView edit(long id) {
//        Employee employee = employeeService.get(id);
//        ModelAndView modelAndView = new ModelAndView("employee/add");
//        modelAndView.addObject("employee", employee);
//        return modelAndView;
//    }
//
//    @RequestMapping("/delete")
//    public String delete(long id) {
//        employeeService.delete(id);
//        return "redirect:/employee";
//    }
//
    @RequestMapping("/save")
    public ModelAndView save(@Valid Recipe recipe,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                recipeService.save(recipe);
                RedirectView redirectView = new RedirectView("/");
                modelAndView.setView(redirectView);
            } catch (ValidationException e) {

                LOGGER.error("Validation error:", e);

                List<String> errors = new LinkedList<>();
                errors.add(e.getMessage());
                modelAndView = new ModelAndView("retete/upload_recipe");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("recipe", recipe);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }

            modelAndView = new ModelAndView("retete/upload_recipe");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("recipe", recipe);
        }

        return modelAndView;
    }

}
