package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.TipPromovare;

public interface PromovareAleasaDAO extends BaseDAO<Promovari> {
    boolean add(PromovareAleasa promovareAleasa);
    TipPromovare findTipByName(PromovareAleasa model);

}
