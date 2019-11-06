package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.domain.Promovari;

import java.util.Collection;

public interface PromovariDAO extends BaseDAO<Promovari> {
    Promovari add(Promovari promovare);
    Promovari listById(int id);
}
