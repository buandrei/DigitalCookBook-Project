package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.sci.digitalCookBook.dao.IngredientDAO;
import ro.sci.digitalCookBook.domain.Ingredient;


import java.util.Collection;

public class IngredientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private IngredientDAO ingredientDAO;

    public Collection<Ingredient> listAll() {
        return ingredientDAO.getAll();
    }

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

    public Ingredient get(int id) {
        LOGGER.debug("Aduc ingredientele pentru ID : " + id);
        return ingredientDAO.findById(id);
    }
//
//    public int save(RecipePhoto recipePhoto ) throws ValidationException {
//        LOGGER.debug("Saving: " + recipePhoto);
//        int photoId;
//        validate(recipePhoto);
//        recipePhotoDAO.update(recipePhoto);
//        photoId = recipePhoto.getId();
//        return photoId;
//    }
//
//    private void validate(RecipePhoto recipePhoto) throws ValidationException {
//        List<String> errors = new LinkedList<String>();
//        if (StringUtils.isEmpty(recipePhoto.getCale_fisier())) {
//            errors.add("Nu a fost atasat nici un fisier");
//        }
//
//
//    }

    public IngredientDAO getDao() {
        return ingredientDAO;
    }

    public void setDao(IngredientDAO dao) {
        this.ingredientDAO = dao;
    }
}
