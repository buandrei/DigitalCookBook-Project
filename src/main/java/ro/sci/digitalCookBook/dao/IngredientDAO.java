package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Ingredient;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DAO interface for  {@link ro.sci.digitalCookBook.domain.Ingredient}.
 *
 * @author Andrei Bu
 */

public interface IngredientDAO extends BaseDAO<Ingredient> {

    Collection<Ingredient> searchByName(String querry);

    Collection<Ingredient> findByMultipleId(ArrayList<Integer> ids);

    Ingredient save(Ingredient ingredient);

}
