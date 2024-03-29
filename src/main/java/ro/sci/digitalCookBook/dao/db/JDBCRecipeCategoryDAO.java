package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipeCategoryDAO;
import ro.sci.digitalCookBook.domain.RecipeCategory;

import java.sql.*;
import java.util.*;

/**
 * @author Andrei Bu
 * <p>
 * RecipeCategory JDBC DAO class for retrieving or altering categories
 */

public class JDBCRecipeCategoryDAO implements RecipeCategoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRecipeDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCRecipeCategoryDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
    }

    private RecipeCategory extractCategory(ResultSet rs) throws SQLException {
        RecipeCategory recipeCategory = new RecipeCategory();

        recipeCategory.setId(rs.getInt("id"));
        recipeCategory.setName(rs.getString("denumire"));

        return recipeCategory;

    }


    @Override
    public Collection<RecipeCategory> getAll() {
        Collection<RecipeCategory> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT categorii_retete.id," +
                             "  categorii_retete.denumire, " +
                             "  1 AS unu " +

                             "FROM categorii_retete ")) {

            while (rs.next()) {
                result.add(extractCategory(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Nu am reusit sa aduc categoriile de retete.", ex);
        }

        return result;
    }

    @Override
    public RecipeCategory findById(int id) {
        Connection connection = newConnection();

        List<RecipeCategory> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT * FROM categorii_retete WHERE id = " + id)) {

            while (rs.next()) {
                result.add(extractCategory(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Nu am gasit categoria!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Am gasit mai multe categorii de retete cu id ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public RecipeCategory update(RecipeCategory recipeCategory) {
        return null;
    }

    @Override
    public RecipeCategory save(RecipeCategory recipeCategory) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO categorii_retete (denumire) VALUES (?) RETURNING id;"
            );

            ps.setString(1, recipeCategory.getName());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipeCategory.setId(rs.getInt(1));
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

        return recipeCategory;
    }

    @Override
    public boolean delete(RecipeCategory recipeCategory) {
        boolean deletion = false;
        Connection connection = newConnection();
        try {
            Statement statement = connection.createStatement();
            deletion = statement.execute("DELETE FROM categorii_retete WHERE id = " + recipeCategory.getId());
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error deleting recipe category.", ex);
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

