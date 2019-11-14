package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.IngredientDAO;
import ro.sci.digitalCookBook.domain.Ingredient;
import ro.sci.digitalCookBook.exception.ValidationException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that executes DAO methods for IngredientService
 *
 * @author Andrei Bu
 */


public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private IngredientDAO ingredientDAO;

    public Collection<Ingredient> listAll() {
        return ingredientDAO.getAll();
    }

    public boolean delete(int id) {
        LOGGER.debug("Deleting recipe with id =  " + id);
        Ingredient ingredient = null;
        try {
            ingredient = ingredientDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("There is no recipe with id = ");
            return false;
        }
        if (ingredient != null) {
            ingredientDAO.delete(ingredient);
            return true;
        }
        return false;
    }

    public Ingredient get(int id) {
        LOGGER.debug("Aduc ingredientele pentru ID : " + id);
        return ingredientDAO.findById(id);
    }

    public Collection<Ingredient> get(ArrayList<Integer> ids) {
        LOGGER.debug("Getting ingredients for id's: " + ids);
        return ingredientDAO.findByMultipleId(ids);
    }

    public void save(Ingredient ingredient) throws ValidationException {
        LOGGER.debug("Saving: " + ingredient);
        validate(ingredient);
        ingredientDAO.save(ingredient);
    }

    private void validate(Ingredient ingredient) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(ingredient.getName())) {
            errors.add("Nu a fost completata denumirea");
        }

        if (StringUtils.isEmpty(ingredient.getUm())) {
            errors.add("Nu a fost completata unitatea de masura");
        }

        if (ingredientDAO.searchByName(ingredient.getName()).size() > 0) {
            errors.add("Exista deja ingredientul cu denumirea" + ingredient.getName());
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public IngredientDAO getDao() {
        return ingredientDAO;
    }

    public void setDao(IngredientDAO dao) {
        this.ingredientDAO = dao;
    }
}
