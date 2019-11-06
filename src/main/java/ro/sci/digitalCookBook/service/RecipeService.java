package ro.sci.digitalCookBook.service;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;

import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.exception.ValidationException;


public class RecipeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeDAO dao;

    public Collection<Recipe> listAll() {
        return dao.getAll();
    }

    public Collection<Recipe> search( String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(int id) {
        LOGGER.debug("Deleting recipe with id =  " + id);
        Recipe recipe = null;
        try {
            recipe = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("There is no recipe with id = ");
            return false;
        }
        if (recipe != null) {
            dao.delete(recipe);
            return true;
        }

        return false;
    }

    public Recipe get(int id) {
        LOGGER.debug("Getting recipe for id: " + id);
        return dao.findById(id);
    }

    public void save(Recipe recipe) throws ValidationException {
        LOGGER.debug("Saving: " + recipe);
        validate(recipe);

        dao.update(recipe);
    }

    private void validate(Recipe recipe) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(recipe.getDenumire())) {
            errors.add("Nu s-a completat denumirea! ");
        }

        if (StringUtils.isEmpty(recipe.getDescriere())) {
            errors.add("Nu s-a completat descrierea!");
        }

        if (recipe.getIdCategoria() == 0) {
            errors.add("Fara categorie!");
        }

        if (StringUtils.isEmpty(recipe.getLink())) {
            errors.add("Reteta nu are link!");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public RecipeDAO getDao() {
        return dao;
    }

    public void setDao(RecipeDAO dao) {
        this.dao = dao;
    }

}
