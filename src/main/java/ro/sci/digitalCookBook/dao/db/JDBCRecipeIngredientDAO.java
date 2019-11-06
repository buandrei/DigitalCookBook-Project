package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipeIngredientDAO;
import ro.sci.digitalCookBook.domain.RecipeIngredient;
import ro.sci.digitalCookBook.domain.RecipePhoto;

import java.sql.*;
import java.util.Collection;

public class JDBCRecipeIngredientDAO implements RecipeIngredientDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRecipeDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCRecipeIngredientDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
    }


    private RecipePhoto extractRecipe(ResultSet rs) throws SQLException {
        return null;
    }


    @Override
    public Collection<RecipeIngredient> getAll() {
        return null;
    }

    @Override
    public RecipeIngredient findById(int id) {
        return null;
    }

    @Override
    public RecipeIngredient update(RecipeIngredient recipeIngredient) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;

            if (recipeIngredient.getId() > 0) {
                //TODO the update part
                ps = connection.prepareStatement(" ");

            } else {
                ps = connection.prepareStatement(
                        "INSERT INTO retetar (idingrediente, instructiuni) VALUES (?, ?) RETURNING id;"
                );

            }
            Array ingredientArray = connection.createArrayOf("integer", recipeIngredient.getIdIngrediente().toArray());
            ps.setArray(1, ingredientArray);
            ps.setString(2, recipeIngredient.getInstructiuni());

            if (recipeIngredient.getId() > 0) {
                ps.setInt(2, recipeIngredient.getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipeIngredient.setId(rs.getInt(1));
            }
            rs.close();

            connection.commit();

        } catch (SQLException e) {

            throw new RuntimeException("Nu am reusit sa gasesc retetar.", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return recipeIngredient;
    }

    @Override
    public boolean delete(RecipeIngredient model) {
        return false;
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
