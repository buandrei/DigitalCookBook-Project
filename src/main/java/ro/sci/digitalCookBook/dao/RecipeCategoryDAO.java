package ro.sci.digitalCookBook.dao;
import ro.sci.digitalCookBook.domain.RecipeCategory;

import java.util.Collection;

public interface RecipeCategoryDAO extends BaseDAO<RecipeCategory> {
    Collection<RecipeCategory> searchByName(String querry);
}
