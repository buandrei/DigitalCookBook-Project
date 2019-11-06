package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.TipPromovare;

public interface TipPromovariDAO extends BaseDAO<TipPromovare>{
    TipPromovare findTipById(int id);

}
