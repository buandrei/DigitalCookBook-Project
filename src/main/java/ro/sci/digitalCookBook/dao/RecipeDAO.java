package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Recipe;

import java.util.Collection;

public interface RecipeDAO extends BaseDAO<Recipe> {

    Collection<Recipe> searchByName(String querry);
}
