package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Recipe;

import java.util.Collection;


public interface RecipeDAO extends BaseDAO<Recipe> {

    Collection<Recipe> searchForRecipe(String name, String categoryId);

    Collection<Recipe> searchForRecipeByIngredients(String ingredients, boolean moreIngredients);

    boolean giveRating(Recipe recipe, long rating);


}
