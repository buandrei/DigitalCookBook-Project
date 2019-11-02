package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.AbstractModel;
import java.util.Collection;

public interface BaseDAO<T extends AbstractModel> {

    Collection<T> getAll();

    T findById(int id);

    T update(T model);

    boolean delete(T model);
}
