package ro.sci.digitalCookBook.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.sci.digitalCookBook.dao.UserDAO;
import ro.sci.digitalCookBook.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class JDBCTemplateUserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JDBCTemplateUserDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<User> getAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return jdbcTemplate.query("select * from app_user where email='" + currentUserName + "'",
                new JDBCTemplateUserDAO.UserMapper());
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query("select * from app_user",
                new ro.sci.digitalCookBook.dao.db.JDBCTemplateUserDAO.UserMapper());
    }

    @Override
    public User findById(int id) {
        return jdbcTemplate.queryForObject("select * from app_user where id = ?",
                new JDBCTemplateUserDAO.UserMapper(), id);
    }

    @Override
    public User update(User model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        String sql = "";
        Integer newId = null;
        if (model.getId() > 0) {
            sql = "update app_user set username=?, email=?, nume=?, prenume=?, isbucatar=?, password = ?, active = ?, role = ? "
                    + "where id = ? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getUserName(),
                    model.getEmail(),
                    model.getLastName(),
                    model.getFirstName(),
                    model.getIsBucatar(),
                    model.getPassword(),
                    model.getActive(),
                    model.getRole()
            }, new RowMapper<Integer>() {
                public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getInt(1);
                }
            });
        } else {
            sql = "insert into app_user (id, username, email, nume, prenume, isbucatar, password, active, role ) "
                    + "values (nextval('useri_id_seq'),?, ?, ?, ?, ?, ?, ?, 'USER') returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getUserName(),
                    model.getEmail(),
                    model.getLastName(),
                    model.getFirstName(),
                    model.getIsBucatar(),
                    model.getPassword(),
                    model.getActive(),
                    model.getRole(),
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
    public boolean delete(User model) {
        return jdbcTemplate.update("delete from app_user where id = ?", model.getId()) > 0;
    }

    @Override
    public Collection<User> searchByName(String query) {
        return jdbcTemplate.query("select * from app_user "
                        + "where lower(email) like ?",
                new String[]{"%" + query.toLowerCase() + "%"},
                new JDBCTemplateUserDAO.UserMapper());
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
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
}
