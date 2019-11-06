package ro.sci.digitalCookBook.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import java.util.*;

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




    @RequestMapping("/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("retete/view");
        modelAndView.addObject("recipe", new Recipe());
        return modelAndView;
    }
//
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

                System.out.println(recipe.isIstutorial());

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
                String path = System.getProperty("user.dir") + "/src/main/resources/static/";
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
