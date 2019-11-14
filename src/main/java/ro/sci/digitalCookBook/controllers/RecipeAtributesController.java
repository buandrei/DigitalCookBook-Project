package ro.sci.digitalCookBook.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.Ingredient;
import ro.sci.digitalCookBook.domain.RecipeCategory;
import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.IngredientService;
import ro.sci.digitalCookBook.service.RecipeCategoryService;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrei Bu
 * Recipe Atributes like Category or Ingredients
 */

@Controller
@RequestMapping("/recipe_atributes")
public class RecipeAtributesController {

    private static Logger LOGGER = LoggerFactory.getLogger("Recipe Atributes Controller");
    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @Autowired
    private IngredientService ingredientService;

    /**
     * Recipe Category part
     */

    @RequestMapping("/add_recipe_category")
    public ModelAndView add_recipe_category() {
        ModelAndView modelAndView = new ModelAndView("recipe_atributes/add_recipe_categories");
        return modelAndView;
    }

    @RequestMapping("/save_recipe_category")
    public ModelAndView save_recipe_category(@Valid RecipeCategory recipeCategory,
                                             BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (!bindingResult.hasErrors()) {
            try {
                recipeCategoryService.save(recipeCategory);
                RedirectView redirectView = new RedirectView("/admin/view_categories");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("Validation error: ", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("recipe_atributes/add_recipe_category");
                modelAndView.addObject("errors", errors);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }

            modelAndView = new ModelAndView("recipe_atributes/add_recipe_category");
            modelAndView.addObject("errors", errors);
        }

        return modelAndView;
    }

    @RequestMapping("/delete_category")
    public String delete_category(int id) {
        recipeCategoryService.delete(id);
        return "redirect:/admin/view_categories";
    }

    /**
     * Ingredient part
     */

    @RequestMapping("/add_ingredient")
    public ModelAndView add_ingredient() {
        ModelAndView modelAndView = new ModelAndView("recipe_atributes/add_ingredient");
        return modelAndView;
    }

    @RequestMapping("/save_ingredient")
    public ModelAndView save_ingredient(@Valid Ingredient ingredient,
                                        BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (!bindingResult.hasErrors()) {
            try {
                ingredientService.save(ingredient);
                RedirectView redirectView = new RedirectView("/admin/view_ingredients");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("Validation error: ", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("recipe_atributes/add_ingredient");
                modelAndView.addObject("errors", errors);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }

            modelAndView = new ModelAndView("recipe_atributes/add_ingredient");
            modelAndView.addObject("errors", errors);
        }

        return modelAndView;
    }

    @RequestMapping("/delete_ingredient")
    public String delete_ingredient(int id) {
        ingredientService.delete(id);
        return "redirect:/admin/view_ingredients";
    }

}