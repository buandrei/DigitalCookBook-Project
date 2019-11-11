package ro.sci.digitalCookBook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.*;

import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/retete")
public class RecipeController {

    private static Logger LOGGER = LoggerFactory.getLogger("Recipe Controller");

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipePhotoService recipePhotoService;

    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @Autowired
    private RecipeIngredientsService recipeIngredientsService;

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView redirectIfNotAValidLink() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/page_not_found");
        return modelAndView;
    }

    @RequestMapping("/cauta_dupa_ingrediente")
    public ModelAndView searchForRecipeBySpecificIngredients() {

        ModelAndView modelAndView = new ModelAndView();

        List<Ingredient> ingredientList = new ArrayList<>(ingredientService.listAll());

        Map<String, List<Ingredient>> ingredientMap = new HashMap<>();
        for(Ingredient i : ingredientList){

            String firstCharKey = String.valueOf(i.getName().charAt(0));
            if(ingredientMap.get(firstCharKey) == null){
                ingredientMap.put(firstCharKey, new ArrayList(Arrays.asList(i)));
            }
            else{
                ingredientMap.get(firstCharKey).add(i);
            }
        }

        modelAndView.addObject("ingredientsMap", ingredientMap);

        modelAndView.setViewName("/retete/search_by_ingredients");
        return modelAndView;
    }


    @RequestMapping(value = {"/retete_rezultate", "/retete_rezultate/{page}"}, method = RequestMethod.POST)
    public ModelAndView getAndShowRecipesForSpecificIngredients(@PathVariable(required = false, name = "page") String page,
                                                                HttpServletRequest request,
                                                                @RequestParam("ingredientsId") String ingredientIds,
                                                                @RequestParam("isMoreIngredientsChecked") String  moreIngredients) {

        boolean isMoreIngredients = moreIngredients.contains("yes");

        ModelAndView modelAndView = new ModelAndView();
        Collection<Recipe> recipes = recipeService.searchBySpecificIngredients(ingredientIds,isMoreIngredients);
        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
        System.out.println(recipes);
        if (recipes.size() == 0) {
            String nothingFound = "Nu au fost gasite retete! <a href=\"/retete/cauta_dupa_ingrediente\"> Inapoi la lista! </a>";
            modelAndView.addObject("nothingFound", nothingFound);
        }

        if (setAndGetRecipePage(page, request, modelAndView, recipes)) return modelAndView;

        modelAndView.addObject("categories", recipeCategories);
        modelAndView.setViewName("/retete/recipe_ingredient_search_result");
        return modelAndView;

    }

    @RequestMapping(value = {"/cauta_retete", "/cauta_retete/{page}"}, method = RequestMethod.POST)
    public ModelAndView searchForRecipeByNameOrAndCategory(@PathVariable(required = false, name = "page") String page,
                                        HttpServletRequest request,
                                        @RequestParam("name_search") String name,
                                        @RequestParam("category_search") String categoryId) {

        ModelAndView modelAndView = new ModelAndView();
        Collection<Recipe> recipes = recipeService.searchRecipe(name, categoryId);

        if (recipes.size() == 0) {
            String nothingFound = "Nu au fost gasite retete! <a href=\"/retete/list_all\"> Inapoi la lista! </a>";
            modelAndView.addObject("nothingFound", nothingFound);
        }

        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
        modelAndView.addObject("categories", recipeCategories);

        if (setAndGetRecipePage(page, request, modelAndView, recipes)) return modelAndView;

        modelAndView.setViewName("/retete/search_for_recipe");
        return modelAndView;

    }


    @RequestMapping(value = {"/list_all", "/list_all/{page}"}, method = RequestMethod.GET)
    public ModelAndView list(@PathVariable(required = false, name = "page") String page,
                             HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Recipe> recipes = recipeService.getAll();
        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
        modelAndView.addObject("categories", recipeCategories);


        if (setAndGetRecipePage(page, request, modelAndView, recipes)) return modelAndView;

        modelAndView.setViewName("/retete/list_all");
        return modelAndView;
    }

    private boolean setAndGetRecipePage(@PathVariable(required = false, name = "page") String page, HttpServletRequest request, ModelAndView modelAndView, Collection<Recipe> recipes) {
        PagedListHolder<Recipe> pagedRecipeList;
        if (page == null) {
            List<Recipe> recipeList = new ArrayList<>(recipes);
            pagedRecipeList = new PagedListHolder<>();
            pagedRecipeList.setSource(recipeList);
            pagedRecipeList.setPageSize(3); // how many objects to display in one page
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
            RedirectView redirectView = new RedirectView("/retete/list_all");
            modelAndView.setView(redirectView);
            return true;
        }
        return false;
    }

    @RequestMapping("/vizualizare_reteta")
    public ModelAndView view(int id) {
        Recipe recipe = recipeService.get(id);
        RecipeCategory recipeCategory = recipeCategoryService.get(recipe.getRecipeCategory().getId());
        RecipePhoto recipePhoto = recipePhotoService.get(recipe.getPhoto().getId());
        RecipeIngredient recipeIngredient = recipeIngredientsService.get(recipe.getRecipeIngredient().getId());
        Collection<Ingredient> ingredients = ingredientService.get(recipeIngredient.getIngredientsId());

        String cookingTime = getPreparationTime(recipe.getCookingTime());
        String preparationTime = getPreparationTime(recipe.getPreparationTime());

        byte[] encodePhoto = Base64.getEncoder().encode(recipePhoto.getContent());

        ModelAndView modelAndView = new ModelAndView("retete/view_recipe");
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("recipeCategory", recipeCategory);
        modelAndView.addObject("recipePhoto", new String(encodePhoto));
        modelAndView.addObject("recipeIngredient", recipeIngredient);
        modelAndView.addObject("ingredients", ingredients);
        modelAndView.addObject("cookingTime", cookingTime);
        modelAndView.addObject("preparationTime", preparationTime);


        return modelAndView;
    }


    @RequestMapping("/upload_recipe")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("retete/upload_recipe");
        modelAndView.addObject("photo", new RecipePhoto());
        modelAndView.addObject("recipe", new Recipe());

        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
        modelAndView.addObject("categories", recipeCategories);

        modelAndView.addObject("recipeIngredients", new RecipeIngredient());
        List<Ingredient> ingredientList = new ArrayList<>(ingredientService.listAll());

        Map<String, List<Ingredient>> ingredientMap = new HashMap<>();
        for(Ingredient i : ingredientList){

            String firstCharKey = String.valueOf(i.getName().charAt(0));
            if(ingredientMap.get(firstCharKey) == null){
                ingredientMap.put(firstCharKey, new ArrayList(Arrays.asList(i)));
            }
            else{
                ingredientMap.get(firstCharKey).add(i);
            }
        }
        modelAndView.addObject("ingredientsMap" , ingredientMap);
        return modelAndView;
    }


    @RequestMapping("/editare_reteta")
    public ModelAndView edit(int id) {
        Recipe recipe = recipeService.get(id);
        RecipePhoto recipePhoto = recipePhotoService.get(recipe.getPhoto().getId());

        byte[] encode = Base64.getEncoder().encode(recipePhoto.getContent());

        Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();

        RecipeIngredient recipeIngredient = recipeIngredientsService.get(recipe.getRecipeIngredient().getId());

        List<Ingredient> ingredients = new ArrayList<>(ingredientService.listAll());
        ingredients.sort(Comparator.comparing(Ingredient::getName));

        String ingredientIdArrayToString = "";

        for (int i = 0; i <= recipeIngredient.getIngredientsId().size(); i++) {
            ingredientIdArrayToString = recipeIngredient.getIngredientsId().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
        }

        ModelAndView modelAndView = new ModelAndView("retete/edit_recipe");
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("photo", recipePhoto);
        modelAndView.addObject("categories", recipeCategories);
        modelAndView.addObject("recipeIngredients", recipeIngredient);
        modelAndView.addObject("recipePhoto", new String(encode));
        modelAndView.addObject("ingredientArrayValue", ingredientIdArrayToString);

        modelAndView.addObject("ingredients", ingredients);
        return modelAndView;

    }

    @RequestMapping(value = "/salvare_reteta_editata", method = RequestMethod.POST)
    public ModelAndView saveEdit(@Valid Recipe recipe,
                                 BindingResult bindingResult,
                                 RecipeIngredient recipeIngredient,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("ingredientsId") ArrayList<Integer> idIngrediente,
                                 @RequestParam("instructions") String instructiuni
    ) {

        recipeIngredient.setId(recipe.getRecipeId());
        RecipePhoto recipePhoto;
        if (!file.isEmpty()) {
            recipePhoto = createRecipePhoto(file);
        } else {
            recipePhoto = recipePhotoService.get(recipe.getPhotoId());
        }

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                getAndSaveRecipeObjects(recipe, recipeIngredient, recipePhoto);
                RedirectView redirectView = new RedirectView("/retete/vizualizare_reteta?id=" + recipe.getId());
                modelAndView.setView(redirectView);
            } catch (ValidationException e) {

                LOGGER.error("Validation recipe error:", e);

                List<String> errors = new LinkedList<>();
                errors.add(e.getMessage());

                modelAndView = new ModelAndView("retete/recipe_save_error");
                modelAndView.addObject("errors", errors);
            }
        } else {
            modelAndView = getModelAndViewIfSaveError(recipe, bindingResult);
        }

        return modelAndView;
    }


    @RequestMapping("/delete")
    public String delete(int id) {
        recipeService.delete(id);
        return "redirect:/retete";
    }


    private ModelAndView getModelAndViewIfSaveError(@Valid Recipe recipe, BindingResult bindingResult) {
        ModelAndView modelAndView;
        List<String> errors = new LinkedList<>();

        for (FieldError error :
                bindingResult.getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }

        modelAndView = new ModelAndView("retete/recipe_save_error");
        modelAndView.addObject("errors", errors);
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }

    private void getAndSaveRecipeObjects(Recipe recipe, RecipeIngredient recipeIngredient, RecipePhoto recipePhoto) throws ValidationException {
        System.out.println(recipeIngredient.getId());
        recipePhotoService.save(recipePhoto);
        recipe.setPhoto(recipePhoto);
        recipeIngredientsService.save(recipeIngredient);
        recipe.setRecipeIngredient(recipeIngredient);
        recipe.setRecipeCategory(recipeCategoryService.get(recipe.getIdCategoria()));
        recipeService.save(recipe);
    }


    @RequestMapping(value = "/salvare_reteta", method = RequestMethod.POST)
    public ModelAndView save(@Valid Recipe recipe,
                             @Valid RecipeIngredient recipeIngredient,
                             BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file
    ) {
        System.out.println(recipe);
        RecipePhoto recipePhoto = createRecipePhoto(file);

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                getAndSaveRecipeObjects(recipe, recipeIngredient, recipePhoto);
                RedirectView redirectView = new RedirectView("/retete/vizualizare_reteta?id=" + recipe.getId());
                modelAndView.setView(redirectView);
            } catch (ValidationException e) {

                LOGGER.error("Validation recipe error:", e);

                List<String> errors = new LinkedList<>();
                errors.add(e.getMessage());

                modelAndView = new ModelAndView("retete/recipe_save_error");
                modelAndView.addObject("errors", errors);
            }
        } else {
            modelAndView = getModelAndViewIfSaveError(recipe, bindingResult);
        }
        return modelAndView;
    }

    private RecipePhoto createRecipePhoto(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String name = String.valueOf(new Date().getTime()) + ".jpeg";
                RecipePhoto result = new RecipePhoto();
                result.setContent(file.getBytes());
                result.setFileName(name);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Couldn't load image!");
        }
        return null;
    }

    @RequestMapping(value = "/give_rating", method = RequestMethod.POST)
    private @ResponseBody
    String give_rating(@RequestParam("id") int id, @RequestParam("rvalue") long rating) {
        String returnText;

        recipeService.giveRating(id, rating);
        returnText = "Multumim pentru feedback!";

        return returnText;
    }

    @GetMapping("/pdfview")
    public String handleForexRequest(Model model, int id) {

        Recipe recipe = recipeService.get(id);
        RecipeIngredient recipeIngredient = recipeIngredientsService.get(recipe.getRecipeIngredient().getId());
        Collection<Ingredient> ingredients = ingredientService.get(recipeIngredient.getIngredientsId());
        StringBuilder listString = new StringBuilder();

        for (Ingredient ingredient : ingredients) {
            listString.append(ingredient.getName() + "\n");
        }
        ModelAndView modelAndView = new ModelAndView("retete/vizualizare_reteta");

        model.addAttribute("reteta", recipe);
        model.addAttribute("instructions", recipeIngredient);
        model.addAttribute("ingredients", listString.toString());
        return "pdfview";
    }

    public Recipe getRecipe(int id) {
        Recipe recipe = recipeService.get(id);
        return recipe;
    }

    private String getPreparationTime(long minutes) {
        String hms = String.format("%02d:%02d", TimeUnit.MINUTES.toHours(minutes),
                TimeUnit.MINUTES.toMinutes(minutes) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutes)));
        return hms;
    }
}


