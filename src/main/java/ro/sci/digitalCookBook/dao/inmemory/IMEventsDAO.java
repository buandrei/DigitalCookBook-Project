package ro.sci.digitalCookBook.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.EventsDAO;
import ro.sci.digitalCookBook.domain.Events;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMEventsDAO extends IMBaseDAO<Events> implements EventsDAO {

    @Override
    public Collection<Events> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Events> all = new LinkedList<Events>(getAll());
        for (Iterator<Events> it = all.iterator(); it.hasNext(); ) {
            Events evt = it.next();
            String ss = evt.getDenumire();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}