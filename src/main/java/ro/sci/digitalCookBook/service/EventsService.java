package ro.sci.digitalCookBook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.EventsDAO;
import ro.sci.digitalCookBook.domain.Events;
import ro.sci.digitalCookBook.exception.ValidationException;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsService.class);

    @Autowired
    private EventsDAO dao;

    public Collection<Events> listAll() {
        return dao.getAll();
    }

    public Collection<Events> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(Integer id) {
        LOGGER.debug("Deleting events for id: " + id);
        Events events = null;
        try {
            events = dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("trying to fing an inexisting events");
            return false;
        }
        if (events != null) {
            dao.delete(events);
            return true;
        }
        return false;
    }

    public Events get(Integer id) {
        LOGGER.debug("Getting events for id: " + id);
        return dao.findById(id);
    }

    public void save(Events events) throws ValidationException {
        LOGGER.debug("Saving: " + events);
        validate(events);
        dao.update(events);
    }

    private void validate(Events events) throws ValidationException {
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(events.getDenumire())) {
            errors.add("Denumire is Empty");
        }

        if (StringUtils.isEmpty(events.getDescriere())) {
            errors.add("Descriere is Empty");
        }

        if (events.getData_start() == null) {
            errors.add("Data start is Empty");
        }

        if (events.getData_final() == null) {
            errors.add("Data finala is Empty");
        }

        if (StringUtils.isEmpty(events.getOrganizator())) {
            errors.add("Organizator is Empty");
        }

//        if (events.getIdpromo() <= 0) {
//            errors.add("Idpromo is Empty");
//        }

//        if (events.getIduser() <= 0) {
//            errors.add("Iduser is Empty");
//        }

//        if (events.getInactiv() == null) {
//            errors.add("Inactiv is Empty");
//        }

//        if (StringUtils.isEmpty(events.getIdpromo())) {
//            errors.add("Idpromo is Empty");
//        }

//        if (StringUtils.isEmpty(events.getIduser())) {
//            errors.add("IdUser is Empty");
//        }

//        if (StringUtils.isEmpty(events.getInactiv())) {
//            errors.add("Inactiv is Empty");
//        }
        else {
            if (currentDate.after(events.getData_start())) {
                errors.add("Data start in past");
            }
        }

        if (events.getData_start() == null) {
            errors.add("Data start is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public EventsDAO getDao() {
        return dao;
    }

    public void setDao(EventsDAO dao) {
        this.dao = dao;
    }
}