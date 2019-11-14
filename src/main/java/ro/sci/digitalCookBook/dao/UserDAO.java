package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.User;

import java.util.Collection;

public interface UserDAO extends BaseDAO<User> {

    Collection<User> searchByName(String query);

    Collection<User> getAllByUser();
}
