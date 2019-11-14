package ro.sci.digitalCookBook.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.sci.digitalCookBook.dao.RecipeCategoryDAO;
import ro.sci.digitalCookBook.domain.RecipeCategory;
import java.util.Collection;

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

    public RecipeCategory get(int id) {
        LOGGER.debug("Aduc categoria pentru ID : " + id);
        return recipeCategoryDAO.findById(id);
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

    public RecipeCategoryDAO getDao() {
        return recipeCategoryDAO;
    }

    public void setDao(RecipeCategoryDAO dao) {
        this.recipeCategoryDAO = dao;
    }
}
