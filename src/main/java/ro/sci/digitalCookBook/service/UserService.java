package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;

import javax.validation.ValidationException;
import java.util.*;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO dao;

    public Collection<User> listAll() {
        return dao.getAll();
    }

    public Collection<User> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(int id) {
        LOGGER.debug("Deleting user for id: " + id);
        User user = null;
        try {
            user = dao.findById(Math.toIntExact(id));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("trying to fing an inexisting user");
            return false;
        }
        if (user != null) {
            dao.delete(user);
            return true;
        }

        return false;
    }

    public User get(int id) {
        LOGGER.debug("Getting user for id: " + id);
        return dao.findById(id);
    }

    public void save(User user) throws ValidationException {
        LOGGER.debug("Saving: " + user);
        validate(user);

        dao.update(user);
    }

    private void validate(User user) throws ValidationException {
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(user.getPrenume())) {
            errors.add("First Name is Empty");
        }

        if (StringUtils.isEmpty(user.getNume())) {
            errors.add("Last Name is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(Arrays.toString(errors.toArray(new String[]{})));
        }
    }

    public UserDAO getDao() {
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
}
