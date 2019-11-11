package ro.sci.digitalCookBook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.*;
import ro.sci.digitalCookBook.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromovariController {

    private static Logger LOGGER = LoggerFactory.getLogger("Promovari Controller");

    @Autowired
    private PromovariService promovariService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private TipPromovariService tipPromovariService;

    @Autowired
    private RecipePhotoService recipePhotoService;

    @RequestMapping("")
    public ModelAndView reroute(){
        ModelAndView modelAndView= add_promo_first_step();
        return modelAndView;
    }

    @RequestMapping("/add_promo_first_step")
    public ModelAndView add_promo_first_step() {
        ModelAndView result = new ModelAndView("promotion/add_promo_first_step");
        Collection<Recipe> recipes = recipeService.getAll(false);
        result.addObject("recipe_list", recipes);
        return result;
    }


    @RequestMapping("/add_promo_second_step")
    public ModelAndView add_promo_second_step(int id) {
        Recipe selectedRecipe = recipeService.get(id);
        RecipePhoto recipePhoto = recipePhotoService.get(selectedRecipe.getPhotoId());
        Collection<TipPromovare> promotionTypes = tipPromovariService.listAll();
        ModelAndView result = new ModelAndView("promotion/add_promo_second_step");
        result.addObject("recipe", selectedRecipe);
        result.addObject("recipePhoto", recipePhoto);
        result.addObject("promotionTypes", promotionTypes);
        result.addObject("promotion", new Promovari());
        return result;
    }


    @RequestMapping("/savePromotion")
    public ModelAndView savePromotion(@Valid Recipe recipe,
                                      @Valid Promovari promotion, BindingResult bindingResult,
                                      @RequestParam("idRecipe") Integer idRecipe) {
        recipe.setId(idRecipe);
        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                TipPromovare tipPromovare = new TipPromovare();
                tipPromovare = tipPromovariService.get(promotion.getIdTipPromovare());
                promovariService.addPromovare(promotion, tipPromovare, recipe);
                RedirectView redirectView = new RedirectView("../");
                modelAndView = new ModelAndView(redirectView);
            } catch (ValidationException e) {
                List<String> errors = new LinkedList<>();
                errors.add(e.getMessage());
                modelAndView = new ModelAndView("../");
                Collection<TipPromovare> promotionTypes = tipPromovariService.listAll();
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("promotionTypes", promotionTypes);
                modelAndView.addObject("recipe", recipe);
            }
        } else {
            List<String> errors = new LinkedList<>();
            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }
            modelAndView = new ModelAndView("promotion/add_promo_second_step");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("promovari", promotion);
        }
        return modelAndView;
    }

    @RequestMapping("/list_all_promotions")
    public ModelAndView list_all_promotions() {
        ModelAndView result = new ModelAndView("promotion/list_all_promotions");
        Collection<Promovari> lista_promovari = promovariService.listAll();
        result.addObject("listPromovari", lista_promovari);
        return result;
    }

    @RequestMapping("/list_promotion_second_step")
    public ModelAndView list_promotion_second_step(int id) {
        Recipe selectedRecipe = recipeService.get(id);
        RecipePhoto recipePhoto = recipePhotoService.get(selectedRecipe.getPhotoId());
        Promovari promovare=promovariService.findById(selectedRecipe.getIdTipPromotie());
        ModelAndView result = new ModelAndView("promotion/list_promotion_second_step");
/*        result.addObject("recipe", selectedRecipe);
        result.addObject("recipePhoto", recipePhoto);
        result.addObject("promotionTypes", promotionTypes);
        result.addObject("promotion", new Promovari());*/
        return result;
    }

    @RequestMapping("/delete_promotion")
    public ModelAndView delete_promotion_first_step() {
        ModelAndView result = new ModelAndView("promotion/delete_promotion_first_step");
        Collection<Recipe> recipes = recipeService.getAllWherePromotionNotNull();
        result.addObject("recipe_list", recipes);
        return result;
    }

    @RequestMapping("/delete_promotion_by_id")
    public ModelAndView delete_promotion_second_step(int id) {
        Promovari promotion=promovariService.findById(id);
        promovariService.delete(promotion);

        ModelAndView result = new ModelAndView("index");
        return result;
    }

}
