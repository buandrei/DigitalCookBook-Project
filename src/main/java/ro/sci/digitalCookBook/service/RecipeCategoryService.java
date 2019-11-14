package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.RecipeCategoryDAO;
import ro.sci.digitalCookBook.domain.RecipeCategory;
import ro.sci.digitalCookBook.exception.ValidationException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that executes DAO methods for Recipe Categories
 *
 * @author Andrei Bu
 */

public class RecipeCategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeCategoryService.class);

    @Autowired
    private RecipeCategoryDAO recipeCategoryDAO;

    public Collection<RecipeCategory> listAll() {
        return recipeCategoryDAO.getAll();
    }

    public boolean delete(int id) {
        LOGGER.debug("Deleting recipe category with id =  " + id);
        RecipeCategory recipeCategory = null;
        try {
            recipeCategory = recipeCategoryDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("There is no recipe category with id = ");
            return false;
        }
        if (recipeCategory != null) {
            recipeCategoryDAO.delete(recipeCategory);
            return true;
        }

        return false;
    }

    public RecipeCategory get(int id) {
        LOGGER.debug("Aduc categoria pentru ID : " + id);
        return recipeCategoryDAO.findById(id);
    }

    public void save(RecipeCategory recipeCategory) throws ValidationException {
        LOGGER.debug("Saving: " + recipeCategory);
        validate(recipeCategory);
        recipeCategoryDAO.save(recipeCategory);
    }

    private void validate(RecipeCategory recipeCategory) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(recipeCategory.getName())) {
            errors.add("Nu a fost completata denumirea!");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public RecipeCategoryDAO getDao() {
        return recipeCategoryDAO;
    }

    public void setDao(RecipeCategoryDAO dao) {
        this.recipeCategoryDAO = dao;
    }
}
