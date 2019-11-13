package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.PromotionType;

public interface PromotionTypeDAO extends BaseDAO<PromotionType> {
    PromotionType findById(int id);
}
