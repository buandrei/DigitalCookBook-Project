package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class JDBCuserDAO implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCuserDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCuserDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
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
                        + "where lower(prenume || ' ' || nume) like '%"
                        + query.toLowerCase() + "%'")) {

            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting users.", ex);
        }

        return result;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("select * from app_user")) {

            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting users.", ex);
        }

        return result;
    }

    @Override
    public User findById(int id) {
        Connection connection = newConnection();

        List<User> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("select * from app_user where id = " + id)) {

            while (rs.next()) {
                result.add(extractUser(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting users.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Multiple Employees for id: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User update(User user) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;
            if (user.getId() > 0) {
                ps = connection.prepareStatement(
                        "update app_user set username=?, email=?, nume=?, prenume=?, isbucatar=?, password = ? "
                                + "where id = ? returning id");

            } else {
                ps = connection.prepareStatement(
                        "insert into app_user (username, email, nume, prenume, isbucatar, password) "
                                + "values (?, ?, ?, ?, ?, ?) returning id" , Statement.RETURN_GENERATED_KEYS);

            }
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getNume());
            ps.setString(4, user.getPrenume());
            ps.setString(5, user.getIsBucatar());
            ps.setString(6, encriptPasswordWithBCrypt(user.getParola()));


            int insertedRows = ps.executeUpdate();

            if(insertedRows == 0) {
                throw new SQLException("Nu am reusit sa adaug user!");
            }

            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    user.setId(generatedKeys.getInt(1));
                    PreparedStatement ps2 = connection.prepareStatement("INSERT INTO user_role(user_id,role_id) VALUES(?,2)");
                    ps2.setInt(1,user.getId());
                    ps2.executeUpdate();
                }else{
                    throw new SQLException("Nu am reusit sa gases user ID!");
                }
            }

            if (user.getId() > 0) {
                ps.setInt(7, user.getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            rs.close();

            connection.commit();

        } catch (SQLException ex) {

            throw new RuntimeException("Error getting user.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        return user;
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

            throw new RuntimeException("Error updating users.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
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
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setNume(rs.getString("nume"));
        user.setPrenume(rs.getString("prenume"));
        user.setIsBucatar(rs.getString("isbucatar"));
        user.setParola(rs.getString("parola"));
        /*user.setPicturePath(rs.getString("poza"));
        user.setAddDate(rs.getTimestamp("data_adaugare"));
        user.setLastLoginDate(rs.getTimestamp("data_last_login"));
        user.setActive(rs.getBoolean("active"));*/

        return user;

    }

    private String encriptPasswordWithBCrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
