package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCUserDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }

    @Override
    public Collection<User> getAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Collection<User> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("select * from app_user where email='" + currentUserName + "'")) {

            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting user. (49)", ex);
        }
        return result;
    }

    @Override
    public Collection<User> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Collection<User> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("select * from app_user where email='" + currentUserName + "'")) {

            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting user. (68)", ex);
        }
        return result;
    }

    @Override
    public User findById(int id) {
        Connection connection = newConnection();
        List<User> result = new LinkedList<User>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("select * from app_user where id = " + id)) {
            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting user. (85)", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Multiple Users for id: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User update(User model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;
            if (model.getId() > 0) {
                ps = connection.prepareStatement(
                        "update app_user set username=?, email=?, nume=?, prenume=?, isbucatar=?, password = ?, active = ?, role = ? "
                                + "where id = ? returning id");

            } else {
                ps = connection.prepareStatement(
                        "insert into app_user (id, username, email, nume, prenume, isbucatar, password, active, role) "
                                + "values (nextval('useri_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?) returning id");

            }
            ps.setString(1, model.getUserName());
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getLastName());
            ps.setString(4, model.getFirstName());
            ps.setString(5, model.getIsBucatar());
            ps.setString(6, model.getPassword());
            ps.setString(7, model.getActive());
            ps.setString(8, model.getRole());

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

            throw new RuntimeException("Error getting user (139).", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return model;
    }

    @Override
    public boolean delete(User model) {
        boolean result = false;
        Connection connection = newConnection();
        try {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from app_user where id = " + model.getId());
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error updating user.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
            }
        }
        return result;
    }

    @Override
    public Collection<User> searchByName(String query) {
        if (query == null) {
            query = "";
        } else {
            query = query.trim();
        }

        Connection connection = newConnection();
        Collection<User> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("select * from app_user "
                        + "where lower(email) like '%"
                        + query.toLowerCase() + "%'")) {
            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error getting user. (189)", ex);
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

    private User extractUser(ResultSet rs) throws SQLException {
        User app_user = new User();
        app_user.setId(rs.getInt("id"));
        app_user.setUserName(rs.getString("username"));
        app_user.setEmail(rs.getString("email"));
        app_user.setLastName(rs.getString("nume"));
        app_user.setFirstName(rs.getString("prenume"));
        app_user.setIsBucatar(rs.getString("isbucatar"));
        app_user.setPassword(rs.getString("password"));
        app_user.setActive(rs.getString("active"));
        app_user.setRole(rs.getString("role"));
        return app_user;
    }
}
