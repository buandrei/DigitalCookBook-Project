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

	@RequestMapping(value =  "", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView();
		List<Recipe> recipesMappedByPromotion = new ArrayList<>(recipeService.getAll(true));
		System.out.println(recipesMappedByPromotion);
		Map<String, List<Recipe>> recipeMap = new HashMap<>();

		for(Recipe recipe : recipesMappedByPromotion){
			String promotionType;
			if(recipe.getIdTipPromotie() == 1){
				promotionType = "Platinum";
			}else if(recipe.getIdTipPromotie() == 2){
				promotionType = "Gold";
			}else{
				promotionType = "Bronze";
			}

			if(recipeMap.get(promotionType) == null){
				recipeMap.put(promotionType, new ArrayList<>(Arrays.asList(recipe)));
			}else{
				recipeMap.get(promotionType).add(recipe);
			}
		}

		System.out.println(recipeMap);

		modelAndView.addObject("recipeMap", recipeMap);

		modelAndView.setViewName("index");
		return modelAndView;
	}


}
