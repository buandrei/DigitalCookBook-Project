package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.RecipePhotoDAO;
import ro.sci.digitalCookBook.domain.RecipePhoto;
import ro.sci.digitalCookBook.exception.ValidationException;
import java.util.LinkedList;
import java.util.List;

public class RecipePhotoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipePhotoService.class);

    @Autowired
    private RecipePhotoDAO recipePhotoDAO;

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

    public RecipePhoto get(int id) {
        LOGGER.debug("Getting photo for id: " + id);
        return recipePhotoDAO.findById(id);
    }

    public void save(RecipePhoto recipePhoto ) throws ValidationException {
        LOGGER.debug("Saving: " + recipePhoto);
        validate(recipePhoto);
        recipePhotoDAO.update(recipePhoto);
    }

    private void validate(RecipePhoto recipePhoto) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(recipePhoto.getContent())) {
            errors.add("Nu a fost atasat nici un fisier");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public RecipePhotoDAO getDao() {
        return recipePhotoDAO;
    }

    public void setDao(RecipePhotoDAO dao) {
        this.recipePhotoDAO = dao;
    }
}
