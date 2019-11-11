package ro.sci.digitalCookBook.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.Recipe;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMRecipeDAO extends IMBaseDAO<Recipe> implements RecipeDAO {


    @Override
    public Collection<Recipe> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Recipe> all = new LinkedList<Recipe>(getAll());
        for (Iterator<Recipe> it = all.iterator(); it.hasNext(); ) {
            Recipe emp = it.next();
            String ss = emp.getDenumire();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }

}
