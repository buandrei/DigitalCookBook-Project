package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.RecipeIngredientDAO;
import ro.sci.digitalCookBook.domain.RecipeIngredient;
import ro.sci.digitalCookBook.exception.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RecipeIngredientsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeIngredientsService.class);

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

//    public Collection<RecipePhoto> listAll() {
//        return dao.getAll();
//    }

//    public Collection<Recipe> search( String query) {
//        LOGGER.debug("Searching for " + query);
//        return dao.searchByName(query);
//    }

//    public boolean delete(int id) {
//        LOGGER.debug("Deleting recipe with id =  " + id);
//        Recipe recipe = null;
//        try {
//            recipe = dao.findById(id);
//        } catch (EmptyResultDataAccessException e) {
//           LOGGER.warn("There is no recipe with id = ");
//            return false;
//        }
//        if (recipe != null) {
//            dao.delete(recipe);
//            return true;
//        }
//
//        return false;
//    }

//    public Recipe get(int id) {
//        LOGGER.debug("Getting recipe for id: " + id);
//        return dao.findById(id);
//    }

    public void save(RecipeIngredient recipeIngredient) throws ValidationException {
        LOGGER.debug("Saving: " + recipeIngredient);

        validate(recipeIngredient);
        recipeIngredientDAO.update(recipeIngredient);
    }

    private void validate(RecipeIngredient recipeIngredient) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (recipeIngredient.getIdIngrediente().size() == 0) {
            errors.add("Nu au fost alese ingrediente!");
        }

        if(StringUtils.isEmpty(recipeIngredient.getInstructiuni())){
            errors.add("Nu a fost completata descrierea!");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public RecipeIngredientDAO getDao() {
        return recipeIngredientDAO;
    }

    public void setDao(RecipeIngredientDAO dao) {
        this.recipeIngredientDAO = dao;
    }
}
