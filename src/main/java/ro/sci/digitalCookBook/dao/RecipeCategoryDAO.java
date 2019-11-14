package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.RecipeCategory;

import java.util.Collection;

/**
 * DAO interface for  {@link ro.sci.digitalCookBook.domain.RecipeCategory}.
 *
 * @author Andrei Bu
 */

public interface RecipeCategoryDAO extends BaseDAO<RecipeCategory> {


    RecipeCategory save(RecipeCategory recipeCategory);
}
