package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.Recipe;

public interface PromovariDAO extends BaseDAO<Promovari> {
    void add(Promovari promovare, Recipe recipe );
    boolean delete(Promovari promovare);
    Promovari listById(int id);
}
