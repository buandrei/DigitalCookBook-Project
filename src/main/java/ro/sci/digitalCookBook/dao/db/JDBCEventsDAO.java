package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ro.sci.digitalCookBook.dao.EventsDAO;
import ro.sci.digitalCookBook.domain.Events;
import ro.sci.digitalCookBook.domain.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JDBCEventsDAO implements EventsDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCEventsDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

        public JDBCEventsDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }

    @Override
    public Collection<Events> getAll() {

        Collection<Events> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("select * from events")) {

            while (rs.next()) {
                result.add(extractEvents(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting events.", ex);
        }
        return result;
    }

    @Override
    public Events findById(int id) {
        Connection connection = newConnection();
        List<Events> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("select * from events where id = " + id)) {
            while (rs.next()) {
                result.add(extractEvents(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting events.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Multiple Events for id: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Events update(Events model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;
            if (model.getId() > 0) {
                ps = connection.prepareStatement(
                        "update events set denumire=?, descriere=?, data_start=?, data_final=?, organizator=?, idpromo = ?, iduser = ?, inactiv = ? "
                                + "where id = ? returning id");

            } else {
                ps = connection.prepareStatement(
                        "insert into events (id, denumire, descriere, data_start, data_final, organizator, idpromo, iduser, inactiv ) "
                                + "values (nextval('events_id_seq'), ?, ?,?, ?, ?,1,(select id from app_user where email='"+currentUserName+"'),'d') returning id");

            }
            ps.setString(1, model.getDenumire());
            ps.setString(2, model.getDescriere());
            ps.setTimestamp(3, new Timestamp(model.getData_start().getTime()));
            ps.setTimestamp(4, new Timestamp(model.getData_final().getTime()));
            ps.setString(5, model.getOrganizator());
            ps.setInt(6, model.getIdpromo());
            ps.setInt(7, model.getIduser());
            ps.setString(8, model.getInactiv());

            if (model.getId() > 0) {
                ps.setInt(9, model.getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt(1));
            }
            rs.close();

            connection.commit();

        } catch (SQLException ex) {

            throw new RuntimeException("Error getting events.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return model;
    }

    @Override
    public boolean delete(Events model) {
        boolean result = false;
        Connection connection = newConnection();
        try {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from events where id = " + model.getId());
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error updating events.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
            }
        }
        return result;
    }

    @Override
    public Collection<Events> searchByName(String query) {
        if (query == null) {
            query = "";
        } else {
            query = query.trim();
        }

        Connection connection = newConnection();
        Collection<Events> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("select * from events "
                        + "where lower(descriere) like '%"
                        + query.toLowerCase() + "%'")) {
            while (rs.next()) {
                result.add(extractEvents(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting events.", ex);
        }
        return result;
    }

    protected Connection newConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();

            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")
                    .append("://")
                    .append(host)
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(dbName)
                    .append("?user=")
                    .append(userName)
                    .append("&password=")
                    .append(pass).toString();
            Connection result = DriverManager.getConnection(url);
            result.setAutoCommit(false);

            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting DB connection", ex);
        }
    }

    private Events extractEvents(ResultSet rs) throws SQLException {
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