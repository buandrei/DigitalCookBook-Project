package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Promotion;
import ro.sci.digitalCookBook.domain.Recipe;

import java.util.Collection;

public interface PromotionDAO extends BaseDAO<Promotion> {
    void add(Promotion promotion, Recipe recipe);

    boolean delete(Promotion promotion);

    Promotion findById(int id);

    Collection<Promotion> getByUserId(int id);

}
