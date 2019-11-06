package ro.sci.digitalCookBook.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.sci.digitalCookBook.domain.*;

import ro.sci.digitalCookBook.exception.ValidationException;
import ro.sci.digitalCookBook.service.*;

import javax.validation.Valid;

import java.io.*;
import java.text.SimpleDateFormat;
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


    @RequestMapping("/list_all")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("retete/list_all");


        Collection<Recipe> recipes = recipeService.listAll();
        result.addObject("recipes", recipes);

        return result;
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
        ingredientList.sort(Comparator.comparing(Ingredient::getDenumire));
        modelAndView.addObject("ingredients", ingredientList);

        return modelAndView;
    }




    @RequestMapping("/vizualizare_reteta")
    public ModelAndView view(int id) {
        Recipe recipe = recipeService.get(id);
        RecipeCategory recipeCategory = recipeCategoryService.get(recipe.getIdCategoria());
        RecipePhoto recipePhoto = recipePhotoService.get(recipe.getIdPoza());
        RecipeIngredient recipeIngredient= recipeIngredientsService.get(recipe.getIdRetetar());

        Collection<Ingredient> ingredients = ingredientService.get(recipeIngredient.getIdIngrediente());

        String cookingTime =  getTime(recipe.getTimp_gatire());
        String preparationTime =  getTime(recipe.getTimp_preparare());

        ModelAndView modelAndView = new ModelAndView("retete/vizualizare_reteta");
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("recipeCategory", recipeCategory);
        modelAndView.addObject("recipePhoto", recipePhoto);
        modelAndView.addObject("recipeIngredient", recipeIngredient);
        modelAndView.addObject("ingredients", ingredients);
        modelAndView.addObject("cookingTime", cookingTime);
        modelAndView.addObject("preparationTime", preparationTime);

        return modelAndView;
    }

    private String getTime(long minutes){

        String hms = String.format("%02d:%02d", TimeUnit.MINUTES.toHours(minutes),
                TimeUnit.MINUTES.toMinutes(minutes) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutes)));
      return hms;
    }


    @GetMapping("/pdfview")
    public String handleForexRequest(Model model, int id) {

        Recipe recipe = recipeService.get(id);
        RecipeIngredient recipeIngredient= recipeIngredientsService.get(recipe.getIdRetetar());

        Collection<Ingredient> ingredients = ingredientService.get(recipeIngredient.getIdIngrediente());
        StringBuilder listString = new StringBuilder();

        for (Ingredient ingredient : ingredients)
        {
            listString.append(ingredient.getDenumire() + "\n");
        }
        ModelAndView modelAndView = new ModelAndView("retete/vizualizare_reteta");

        model.addAttribute("reteta", recipe);
        model.addAttribute("instructions", recipeIngredient);
        model.addAttribute("ingredients",listString.toString());
        return "pdfview";
    }

    public Recipe getRecipe(int id) {

        Recipe recipe = recipeService.get(id);

        return recipe;
    }



    @RequestMapping("/edit_recipe")
    public ModelAndView edit(int id) {
        Recipe recipe = recipeService.get(id);
        ModelAndView modelAndView = new ModelAndView("retete/salvare_reteta");
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }



    @RequestMapping("/delete")
    public String delete(int id) {
        recipeService.delete(id);
        return "redirect:/retete";
    }





    @RequestMapping(value = "/salvare_reteta", method =  RequestMethod.POST)
    public ModelAndView save(@Valid Recipe recipe,
                             @Valid RecipePhoto recipePhoto,
                             @Valid RecipeIngredient recipeIngredient,
                             BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file
                             ) {
        ModelAndView modelAndView = new ModelAndView();
        String imageName = uploadFile(file);

        if (!bindingResult.hasErrors()) {
            if (imageName != null) {
                recipePhoto.setCale_fisier(imageName);
            }

            try {
                recipePhotoService.save(recipePhoto);
                recipe.setIdPoza(recipePhoto.getId());
                recipeIngredientsService.save(recipeIngredient);
                recipe.setIdRetetar(recipeIngredient.getId());
                recipeService.save(recipe);

                RedirectView redirectView = new RedirectView("/retete/list_all");
                modelAndView.setView(redirectView);
            } catch (ValidationException e) {

                LOGGER.error("Validation error:", e);

                List<String> errors = new LinkedList<>();
                errors.add(e.getMessage());

                modelAndView = new ModelAndView("retete/upload_recipe");
                Collection<RecipeCategory> recipeCategories = recipeCategoryService.listAll();
                List<Ingredient> ingredientList = new ArrayList<>(ingredientService.listAll());
                ingredientList.sort(Comparator.comparing(Ingredient::getDenumire));

                modelAndView.addObject("ingredients", ingredientList);
                modelAndView.addObject("categories", recipeCategories);
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("recipeIngredients", recipeIngredient);
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

    private String uploadFile(MultipartFile file){
        if(!file.isEmpty()){
            try{
                byte[] bytes = file.getBytes();
                String path = System.getProperty("user.dir") + "/src/main/resources/";
                File dir = new File(path + File.separator + "recipe_images");
                if(!dir.exists())
                    dir.mkdirs();

                String name = String.valueOf(new Date().getTime()) + ".jpeg";
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return name;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{

        }
        return null;
    }
}
