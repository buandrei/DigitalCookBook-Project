package ro.sci.digitalCookBook.dao.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<User> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<User> all = new LinkedList<User>(getAll());
        for (Iterator<User> it = all.iterator(); it.hasNext(); ) {
            User apu = it.next();
            String ss = apu.getEmail();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }

    @Override
    public Collection<User> getAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        return jdbcTemplate.query("select * from app_user where iduser=(select id from app_user where email='"+currentUserName+"')",
                new ro.sci.digitalCookBook.dao.db.JDBCTemplateUserDAO.UserMapper());
    }
}
