package ro.sci.digitalCookBook.service;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;

import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.exception.ValidationException;

/**
 * Class that executes DAO methods for Recipe
 *
 * @author Andrei Bu
 */


public class RecipeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeDAO dao;

    public Collection<Recipe> getAll(boolean isOnlyPromotedForHomePage, boolean onlyTutorialRecipes) {
        return dao.getAll(isOnlyPromotedForHomePage, onlyTutorialRecipes);
    }

    public Collection<Recipe> getAll() {
        return dao.getAll();
    }

    public Collection<Recipe> searchRecipe(String name, String categoryId) {
        LOGGER.debug("Searching for recipes with name " + name + " and categoryId = " + categoryId);
        return dao.searchForRecipe(name, categoryId);
    }

    public Collection<Recipe> searchBySpecificIngredients(String ingredients, boolean moreIngredients) {
        LOGGER.debug("Searching for recipes with ingredients " + ingredients + " and with the more ingredients option selected");
        return dao.searchForRecipeByIngredients(ingredients, moreIngredients);
    }


    public Collection<Recipe> getAllWherePromotionNotNull() {
        return dao.getAllWherePromotionNotNull();
    }

    public Collection<Recipe> getRecipesByUser(String email) {
        return dao.getRecipesByUser(email);
    }


    public boolean delete(int id) {
        LOGGER.debug("Deleting recipe with id =  " + id);
        Recipe recipe = null;
        try {
            recipe = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Nu s-a putut sterge.Nu am gasit  ID: " + id + " .Contactati suport!");
            return false;
        }
        if (recipe != null) {
            dao.delete(recipe);
            return true;
        }

        return false;
    }

    public boolean inactivateRecipe(int id) {
        LOGGER.debug("Inactivating recipe with id =  " + id);
        Recipe recipe = null;
        try {
            recipe = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Nu s-a putut inactiva.Nu am gasit  ID: " + id + " .Contactati suport!");
            return false;
        }
        if (recipe != null) {
            dao.inactivate(recipe);
            return true;
        }

        return false;
    }

    public boolean activateRecipe(int id) {
        LOGGER.debug("Inactivating recipe with id =  " + id);
        Recipe recipe = null;
        try {
            recipe = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Nu s-a putut inactiva.Nu am gasit  ID: " + id + " .Contactati suport!");
            return false;
        }
        if (recipe != null) {
            dao.activateRecord(recipe);
            return true;
        }

        return false;
    }

    public Recipe get(int id) {
        LOGGER.debug("Getting recipe for id: " + id);
        return dao.findById(id);
    }

    public void save(Recipe recipe) throws ValidationException {
        LOGGER.debug("Saving: " + recipe);
        validate(recipe);

        dao.update(recipe);
    }

    private void validate(Recipe recipe) throws ValidationException {
        List<String> errors = new LinkedList<>();
        if (StringUtils.isEmpty(recipe.getName())) {
            errors.add("Nu s-a completat denumirea! ");
        }

        if (StringUtils.isEmpty(recipe.getDescription())) {
            errors.add("Nu s-a completat descrierea!");
        }

        if (recipe.getIdCategoria() == 0) {
            errors.add("Fara categorie!");
        }

        if (StringUtils.isEmpty(recipe.getLink())) {
            errors.add("Reteta nu are link!");
        }


        if (StringUtils.isEmpty(recipe.getCookingTime())) {
            errors.add("Nu a fost completat timpul de gatire!");
        }

        if (StringUtils.isEmpty(recipe.getLink())) {
            errors.add("Nu a fost completat timpul de preparare!");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public boolean giveRating(int id, long rating) {
        LOGGER.debug("Giving a rating to the recipe with id =  " + id);
        Recipe recipe = null;

        try {
            recipe = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Nu s-a putut sterge.Nu am gasit  ID: " + id + " .Contactati suport!");
            return false;
        }
        if (recipe != null) {
            dao.giveRating(recipe, rating);
            return true;
        }

        return false;
    }

    public RecipeDAO getDao() {
        return dao;
    }

    public void setDao(RecipeDAO dao) {
        this.dao = dao;
    }

}
