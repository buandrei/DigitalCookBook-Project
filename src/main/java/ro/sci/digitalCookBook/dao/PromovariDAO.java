package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Promovari;

public interface PromovariDAO extends BaseDAO<Promovari> {
    void add(Promovari promovare);
    Promovari listById(int id);
}
