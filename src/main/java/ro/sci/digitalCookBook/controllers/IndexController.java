package ro.sci.digitalCookBook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.service.RecipeCategoryService;
import ro.sci.digitalCookBook.service.RecipeIngredientsService;
import ro.sci.digitalCookBook.service.RecipePhotoService;
import ro.sci.digitalCookBook.service.RecipeService;

import java.util.*;

/**
 * @author Andrei Bu
 * <p>
 * class for the homepage
 */

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipePhotoService recipePhotoService;

    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @Autowired
    private RecipeIngredientsService recipeIngredientsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView();
        List<Recipe> recipesMappedByPromotion = new ArrayList<>(recipeService.getAll(true, false));
        Map<String, List<Recipe>> recipeUnsortedMap = new HashMap<>();

        for (Recipe recipe : recipesMappedByPromotion) {
            String promotionType;
            if (recipe.getIdTipPromotie() == 1) {
                promotionType = "PLATINUM";
            } else if (recipe.getIdTipPromotie() == 2) {
                promotionType = "GOLD";
            } else {
                promotionType = "BRONZE";
            }

            if (recipeUnsortedMap.get(promotionType) == null) {
                recipeUnsortedMap.put(promotionType, new ArrayList<>(Arrays.asList(recipe)));
            } else {
                recipeUnsortedMap.get(promotionType).add(recipe);
            }
        }

        Map<String, List<Recipe>> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());
        reverseSortedMap.putAll(recipeUnsortedMap);

        modelAndView.addObject("recipeMap", reverseSortedMap);
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
