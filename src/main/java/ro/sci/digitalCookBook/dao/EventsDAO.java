package ro.sci.digitalCookBook.dao;

import ro.sci.digitalCookBook.domain.Events;

import java.util.Collection;

public interface EventsDAO extends BaseDAO<Events> {

    Collection<Events> searchByName(String query);
}
