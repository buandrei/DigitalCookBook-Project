package ro.sci.digitalCookBook.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ro.sci.digitalCookBook.dao.EventsDAO;
import ro.sci.digitalCookBook.domain.Events;
import ro.sci.digitalCookBook.domain.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Repository("dao")
public class JDBCTemplateEventsDAO implements EventsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JDBCTemplateEventsDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Events> getAll() {
        return jdbcTemplate.query("select * from events",
                new ro.sci.digitalCookBook.dao.db.JDBCTemplateEventsDAO.EventsMapper());
    }

    @Override
    public Events findById(int id) {
        return jdbcTemplate.queryForObject("select * from events where id = ?",
                new ro.sci.digitalCookBook.dao.db.JDBCTemplateEventsDAO.EventsMapper(), id);
    }

    @Override
    public Events update(Events model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        String sql = "";
        Integer newId = null;
        if (model.getId() > 0) {
            sql = "update events set denumire=?, descriere=?, data_start=?, data_final=?, organizator=?, idpromo = ?, iduser = ?, inactiv = ? "
                    + "where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getDenumire(),
                    model.getDescriere(),
                    new Timestamp(model.getData_start().getTime()),
                    new Timestamp(model.getData_final().getTime()),
                    model.getOrganizator(),
                    model.getIdpromo(),
                    model.getIduser(),
                    model.getInactiv(),
                    model.getId()
            }, new RowMapper<Integer>() {
                public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getInt(1);
                }
            });
        } else {
            sql = "insert into events (id, denumire, descriere, data_start, data_final, organizator, idpromo, iduser, inactiv ) "
                    + "values (nextval('events_id_seq'),?, ?, ?, ?, ?, 1, (select id from app_user where email='" + currentUserName + "'), 'd') returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getDenumire(),
                    model.getDescriere(),
                    new Timestamp(model.getData_start().getTime()),
                    new Timestamp(model.getData_final().getTime()),
                    model.getOrganizator(),
//                    model.getIdpromo(),
//                    model.getIduser(),
//                    model.getInactiv(),
            }, new RowMapper<Integer>() {
                public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getInt(1);
                }
            });
        }
        model.setId(newId);
        return model;
    }

    @Override
    public boolean delete(Events model) {
        return jdbcTemplate.update("delete from events where id = ?", model.getId()) > 0;
    }

    @Override
    public Collection<Events> searchByName(String query) {
        return jdbcTemplate.query("select * from events "
                        + "where lower(denumire) like ?",
                new String[]{"%" + query.toLowerCase() + "%"},
                new ro.sci.digitalCookBook.dao.db.JDBCTemplateEventsDAO.EventsMapper());
    }

    private static class EventsMapper implements RowMapper<Events> {

        @Override
        public Events mapRow(ResultSet rs, int arg1) throws SQLException {
            Events events = new Events();
            events.setId(rs.getInt("id"));
            events.setDenumire(rs.getString("denumire"));
            events.setDescriere(rs.getString("descriere"));
            events.setData_start(new Date(rs.getTimestamp("data_start").getTime()));
            events.setData_final(new Date(rs.getTimestamp("data_final").getTime()));
            events.setOrganizator(rs.getString("organizator"));
            events.setIdpromo((int) rs.getInt("idpromo"));
            events.setIduser((int) rs.getInt("iduser"));
            events.setInactiv(rs.getString("inactiv"));
            return events;
        }
    }
}