package ro.sci.digitalCookBook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.domain.RecipeCategory;
import ro.sci.digitalCookBook.service.RecipeCategoryService;
import ro.sci.digitalCookBook.service.RecipeIngredientsService;
import ro.sci.digitalCookBook.service.RecipePhotoService;
import ro.sci.digitalCookBook.service.RecipeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Class that executes DAO methods for Administration Page
 *
 * @author Andrei Bu
    @author Dorin Bria
    @author Gergely Laszlo
    @author Marius Butaciu
 */

@Controller
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipePhotoService recipePhotoService;

    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @Autowired
    private RecipeIngredientsService recipeIngredientsService;

    @RequestMapping(value =  "", method = RequestMethod.GET)
    public ModelAndView mainMenu() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/main_menu");

        return modelAndView;
    }


    @RequestMapping(value = {"/view_recipes", "/view_recipes/{page}"}, method = RequestMethod.GET)
    public ModelAndView list(@PathVariable(required = false, name = "page") String page,
                             HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Recipe> recipes = recipeService.getAll();
        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
        modelAndView.addObject("categories", recipeCategories);

        if (setAndGetRecipePage(page, request, modelAndView, recipes)) return modelAndView;

        modelAndView.setViewName("/admin/list_all_recipes");
        return modelAndView;
    }

    private boolean setAndGetRecipePage(@PathVariable(required = false, name = "page") String page, HttpServletRequest request, ModelAndView modelAndView, Collection<Recipe> recipes) {
        PagedListHolder<Recipe> pagedRecipeList;
        if (page == null) {
            List<Recipe> recipeList = new ArrayList<>(recipes);
            pagedRecipeList = new PagedListHolder<>();
            pagedRecipeList.setSource(recipeList);
            pagedRecipeList.setPageSize(10); // how many objects to display in one page
            request.getSession().setAttribute("recipeList", pagedRecipeList);

        } else if (page.equals("prev")) {
            pagedRecipeList = (PagedListHolder<Recipe>) request.getSession().getAttribute("recipeList");
            if (checkPageListSessionAttributeIfNull(modelAndView, pagedRecipeList)) return true;
            pagedRecipeList.previousPage();
        } else if (page.equals("next")) {
            pagedRecipeList = (PagedListHolder<Recipe>) request.getSession().getAttribute("recipeList");
            if (checkPageListSessionAttributeIfNull(modelAndView, pagedRecipeList)) return true;
            pagedRecipeList.nextPage();
        } else {
            int pageNr = Integer.parseInt(page);
            pagedRecipeList = (PagedListHolder<Recipe>) request.getSession().getAttribute("recipeList");
            if (checkPageListSessionAttributeIfNull(modelAndView, pagedRecipeList)) return true;
            pagedRecipeList.setPage(pageNr - 1);
        }
        return false;
    }

    private boolean checkPageListSessionAttributeIfNull(ModelAndView modelAndView, PagedListHolder<Recipe> pagedRecipeList) {
        if (pagedRecipeList == null) {
            RedirectView redirectView = new RedirectView("/admin");
            modelAndView.setView(redirectView);
            return true;
        }
        return false;
    }

    @RequestMapping("/delete_recipe")
    public String deleteRecipe(int id) {
        recipeService.delete(id);
        return "redirect:/admin/view_recipes";
    }

    @RequestMapping("inactivate_recipe")
    public String inactivateRecipe(int id){
        recipeService.inactivateRecipe(id);
        return "redirect:/admin/view_recipes";
    }
    @RequestMapping("activate_recipe")
    public String activateRecipe(int id){
        recipeService.activateRecipe(id);
        return "redirect:/admin/view_recipes";
    }
}
