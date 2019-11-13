package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.IngredientDAO;
import ro.sci.digitalCookBook.domain.Ingredient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    /**
     * Method will return all the results from the retete db table
     * @param querry
     * @return
     */
    @Override
    public Collection<Ingredient> searchByName(String querry) {
        return null;
    }

    private Ingredient extractIngredient(ResultSet rs) throws SQLException {
        Ingredient  ingredient = new Ingredient();

        ingredient.setId(rs.getInt("id"));
        ingredient.setName(rs.getString("denumire"));

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
                             "  1 AS unu " +

                             "FROM ingrediente ")) {

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
            throw new IllegalStateException("Am gasit mai multe ingrediente cu ID: " + id );
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Collection<Ingredient> findByMultipleId(ArrayList<Integer> ids) {
        Connection connection = newConnection();

        List<Ingredient> result = new LinkedList<>();



        String databaseArray = ids.toString().replace("[", "(").replace("]",")");

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


    /**
     * This method will create a connection to the DB
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

    @Override
    public boolean delete(Ingredient model) {
        return false;
    }
}
