package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Recipe;

import java.util.Collection;

/**
 * DAO interface for  {@link ro.sci.digitalCookBook.domain.Recipe}.
 *
 * @author Andrei Bu
 */

public interface RecipeDAO extends BaseDAO<Recipe> {

    Collection<Recipe> searchForRecipe(String name, String categoryId);

    Collection<Recipe> searchForRecipeByIngredients(String ingredients, boolean moreIngredients);

    Collection<Recipe> getAllWherePromotionNotNull();

    boolean giveRating(Recipe recipe, long rating);

    Collection<Recipe> getAll(boolean isOnlyPromotedForHomepage, boolean getOnlyTutorialRecipes);

    Collection<Recipe> getRecipesByUser(String userEmail);

    boolean inactivate(Recipe recipe);

    boolean activateRecord(Recipe recipe);

}
