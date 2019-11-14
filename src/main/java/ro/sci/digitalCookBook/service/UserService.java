package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;
import ro.sci.digitalCookBook.exception.ValidationException;

import java.util.*;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO dao;

    public Collection<User> listAll() {
        return dao.getAll();
    }

    public Collection<User> listAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return dao.getAllByUser();
    }

    public Collection<User> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(Integer id) {
        LOGGER.debug("Deleting user for id: " + id);
        User User = null;
        try {
            User = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("trying to fing an inexisting user");
            return false;
        }
        if (User != null) {
            dao.delete(User);
            return true;
        }
        return false;
    }

    public User get(Integer id) {
        LOGGER.debug("Getting user for id: " + id);
        return dao.findById(id);
    }

    public void save(User User) throws ro.sci.digitalCookBook.exception.ValidationException {
        LOGGER.debug("Saving: " + User);
        validate(User);
        dao.update(User);
    }

    private void validate(User User) throws ro.sci.digitalCookBook.exception.ValidationException {
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();
//        if (StringUtils.isEmpty(User.getEmail())) {
//            errors.add("Email Is Empty");
//        }

//        if (StringUtils.isEmpty(User.getUserName())) {
//            errors.add("UserName is Empty");
//        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public UserDAO getDao() {
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
}
