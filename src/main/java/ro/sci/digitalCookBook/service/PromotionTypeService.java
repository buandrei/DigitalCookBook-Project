package ro.sci.digitalCookBook.service;

import ro.sci.digitalCookBook.dao.PromotionTypeDAO;
import ro.sci.digitalCookBook.domain.PromotionType;

import java.util.Collection;

public class PromotionTypeService {
    private PromotionTypeDAO daoPromotionType;

    public Collection<PromotionType> listAll() {
        return daoPromotionType.getAll();
    }

    public PromotionTypeDAO getDao() {
        return daoPromotionType;
    }

    public void setDao(PromotionTypeDAO dao) {
        this.daoPromotionType = dao;
    }


    public PromotionType get(int id) {
        return daoPromotionType.findById(id);
    }
}
