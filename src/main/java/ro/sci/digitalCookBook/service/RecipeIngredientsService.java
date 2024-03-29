package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.RecipeIngredientDAO;
import ro.sci.digitalCookBook.domain.RecipeIngredient;
import ro.sci.digitalCookBook.exception.ValidationException;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that executes DAO methods for RecipeIngredient Service
 *
 * @author Andrei Bu
 */

public class RecipeIngredientsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeIngredientsService.class);

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

    public RecipeIngredient get(int id) {
        LOGGER.debug("Getting ingredients for id's: " + id);
        return recipeIngredientDAO.findById(id);
    }


    public void save(RecipeIngredient recipeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + recipeIngredient);
        validate(recipeIngredient);
        recipeIngredientDAO.update(recipeIngredient);
    }

    private void validate(RecipeIngredient recipeIngredient) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (recipeIngredient.getIngredientsId().size() == 0) {
            errors.add("Nu au fost alese ingrediente!");
        }

        if (StringUtils.isEmpty(recipeIngredient.getInstructions())) {
            errors.add("Nu a fost completata descrierea!");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public RecipeIngredientDAO getDao() {
        return recipeIngredientDAO;
    }

    public void setDao(RecipeIngredientDAO dao) {
        this.recipeIngredientDAO = dao;
    }
}
