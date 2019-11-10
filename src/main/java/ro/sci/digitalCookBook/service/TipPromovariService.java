package ro.sci.digitalCookBook.service;

import ro.sci.digitalCookBook.dao.PromovariDAO;
import ro.sci.digitalCookBook.dao.TipPromovariDAO;
import ro.sci.digitalCookBook.domain.TipPromovare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

public class TipPromovariService {
    private TipPromovariDAO daoTipPromovare;

        public Collection<TipPromovare> listAll() {
            return daoTipPromovare.getAll();
    }

    public TipPromovariDAO getDao() {
    return daoTipPromovare;
}

    public void setDao(TipPromovariDAO dao) {
        this.daoTipPromovare = dao;
    }

    public TipPromovare get(int id) {
        return daoTipPromovare.findTipById(id);
    }
}
