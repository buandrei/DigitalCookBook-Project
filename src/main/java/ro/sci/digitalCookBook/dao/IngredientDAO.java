package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Ingredient;

import java.util.Collection;

public interface IngredientDAO extends BaseDAO<Ingredient> {

    Collection<Ingredient> searchByName(String querry);
}
