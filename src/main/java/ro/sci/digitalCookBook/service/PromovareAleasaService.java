package ro.sci.digitalCookBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sci.digitalCookBook.dao.PromovareAleasaDAO;
import ro.sci.digitalCookBook.dao.PromovariDAO;
import ro.sci.digitalCookBook.dao.db.JDBCPromovareAleasaDAO;
import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.domain.Promovari;

import java.util.Collection;

public class PromovareAleasaService {
    @Autowired
    private PromovareAleasaDAO dao;

    public PromovareAleasaDAO getDao() {
        return dao;
    }

    public void setDao(PromovareAleasaDAO dao) {
        this.dao = dao;
    }
}
