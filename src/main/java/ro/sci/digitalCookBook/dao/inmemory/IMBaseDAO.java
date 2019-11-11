package ro.sci.digitalCookBook.dao.inmemory;

import ro.sci.digitalCookBook.dao.BaseDAO;
import ro.sci.digitalCookBook.domain.AbstractModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class IMBaseDAO<T extends AbstractModel> implements BaseDAO<T> {

    private Map<Integer, T> models = new HashMap<>();

    private static AtomicInteger ID = new AtomicInteger();

    @Override
    public Collection<T> getAll() {

        return models.values();
    }

    @Override
    public T findById(int id) {

        return models.get(id);
    }

    @Override
    public T update(T model) {
        if (model.getId() <= 0) {
            model.setId(ID.getAndIncrement());
        }

        models.put(model.getId(), model);
        return model;
    }

    @Override
    public boolean delete(T model) {
        boolean result = models.containsKey(model.getId());

        if (result)
            models.remove(model.getId());
        return result;
    }


}
