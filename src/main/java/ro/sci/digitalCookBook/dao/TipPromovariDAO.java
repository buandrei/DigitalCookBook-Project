package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.TipPromovare;

public interface TipPromovariDAO extends BaseDAO<TipPromovare>{
    TipPromovare findTipById(int id);

}