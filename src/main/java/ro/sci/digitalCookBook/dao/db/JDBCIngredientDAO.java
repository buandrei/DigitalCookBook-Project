package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.IngredientDAO;
import ro.sci.digitalCookBook.domain.Ingredient;

import java.sql.*;
import java.util.*;

/**
 * @author Andrei Bu
 * <p>
 * Ingredient JDBC DAO class for retrieving or altering ingredients
 */


public class JDBCIngredientDAO implements IngredientDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRecipeDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCIngredientDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
    }

    @Override
    public Collection<Ingredient> searchByName(String name) {
        Collection<Ingredient> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT  ingrediente.denumire, " +
                             "  ingrediente.id," +
                             "  ingrediente.um," +
                             "  1 AS unu " +

                             " FROM ingrediente " +
                             " WHERE trim(ingrediente.denumire) = trim($$" + name + "$$)" +
                             " ORDER BY ingrediente.denumire")) {

            while (rs.next()) {
                result.add(extractIngredient(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Nu am reusit sa aduc ingredientele cu denumirea " + name, ex);
        }

        return result;
    }

    private Ingredient extractIngredient(ResultSet rs) throws SQLException {
        Ingredient ingredient = new Ingredient();

        ingredient.setId(rs.getInt("id"));
        ingredient.setName(rs.getString("denumire"));
        ingredient.setUm(rs.getString("um"));

        return ingredient;

    }


    @Override
    public Collection<Ingredient> getAll() {
        Collection<Ingredient> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT ingrediente.id," +
                             "  ingrediente.denumire, " +
                             "  ingrediente.um, " +
                             "  1 AS unu " +

                             " FROM ingrediente " +
                             " ORDER BY ingrediente.denumire")) {

            while (rs.next()) {
                result.add(extractIngredient(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Nu am reusit sa aduc ingredientele.", ex);
        }

        return result;
    }

    @Override
    public Ingredient findById(int id) {
        Connection connection = newConnection();

        List<Ingredient> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT * FROM ingrediente WHERE id = " + id)) {

            while (rs.next()) {
                result.add(extractIngredient(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Nu am gasit ingredientul!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Am gasit mai multe ingrediente cu ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Collection<Ingredient> findByMultipleId(ArrayList<Integer> ids) {
        Connection connection = newConnection();

        List<Ingredient> result = new LinkedList<>();
        String databaseArray = ids.toString().replace("[", "(").replace("]", ")");

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT * FROM ingrediente WHERE id IN " + databaseArray)) {
            while (rs.next()) {
                result.add(extractIngredient(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Nu am gasit ingredientul!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return result;
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        return null;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO ingrediente (denumire,um) VALUES (?,?) RETURNING id;"
            );

            ps.setString(1, ingredient.getName());
            ps.setString(2, ingredient.getUm());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingredient.setId(rs.getInt(1));
            }

            rs.close();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting recipe category.", e);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        return ingredient;
    }

    @Override
    public boolean delete(Ingredient ingredient) {
        boolean deletion = false;
        Connection connection = newConnection();
        try {
            Statement statement = connection.createStatement();
            deletion = statement.execute("DELETE FROM ingrediente WHERE id = " + ingredient.getId());
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error deleting ingredient.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return deletion;
    }

    /**
     * This method will create a connection to the DB
     *
     * @return a Connection or throws a new RuntimeException if there is no DB connection
     */
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
        } catch (Exception e) {
            throw new RuntimeException("No DB Connection!", e);
        }

    }


}
