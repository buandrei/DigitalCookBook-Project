package ro.sci.digitalCookBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sci.digitalCookBook.dao.PromotionDAO;
import ro.sci.digitalCookBook.dao.PromotionTypeDAO;
import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.Promotion;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.domain.PromotionType;

import java.time.LocalDateTime;
import java.util.Collection;

import static java.time.LocalDate.now;

public class PromotionService {
    @Autowired
    private RecipeDAO daoRecipe;
    @Autowired
    private PromotionDAO daoPromotion;
    @Autowired
    private PromotionTypeDAO daoPromotionType;

    public void addPromotion(Promotion promotion, PromotionType promotionType, Recipe recipe) {
        promotion.setCreationDate(LocalDateTime.now());
        promotion.setFinalDate(promotion.getCreationDate().plusDays(promotionType.getPeriod()));
        //promotion.setIdUser();
        //promotion.setPromotionState(true);

        daoPromotion.add(promotion, recipe);

    }


    //in cazul unei promovari active, acesta se modifica doar la un tip promovare superior
/*    public Promotion modifyIdPromotionType(int idPromotion, int idNewPromotionType) {
        Promotion promotion = daoPromotion.findById(idPromotion);
        if (validateModify(promotion, idNewPromotionType)) {
            promotion.setIdPromotionType(idNewPromotionType);
            promotion.setCreationDate(LocalDateTime.now());
            promotion.setFinalDate(promotion.getCreationDate().plusDays(daoPromotionType.findById(idNewPromotionType).getPeriod()));
            //promotion.setIdUser();
            promotion.setPromotionState(true);
        }
        daoPromotion.update(promotion);
        return promotion;
    }*/

    public Promotion findById(int id) {
        return daoPromotion.findById(id);
    }

    public Collection<Promotion> listAll() {
        return daoPromotion.getAll();
    }

    public boolean delete(Promotion promotion) {
        daoPromotion.delete(promotion);
        return false;
    }

    public PromotionDAO getDao() {
        return daoPromotion;
    }

    public void setDao(PromotionDAO dao) {
        this.daoPromotion = dao;
    }

}
