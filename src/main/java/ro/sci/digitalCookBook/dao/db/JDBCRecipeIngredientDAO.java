package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipeIngredientDAO;
import ro.sci.digitalCookBook.domain.RecipeIngredient;

import java.sql.*;
import java.util.*;

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


    private RecipeIngredient extractRecipeIngredients(ResultSet rs) throws SQLException {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        recipeIngredient.setId(rs.getInt("id"));
        recipeIngredient.setInstructions(rs.getString("instructiuni"));
        Array ingredientArray = rs.getArray("idingrediente");
        if (ingredientArray == null) {
            return null;
        }
        Integer[] ingredientIntArray = (Integer[]) ingredientArray.getArray();
        ArrayList<Integer> ingredientArrayList = new ArrayList<>();
        Collections.addAll(ingredientArrayList, ingredientIntArray);
        recipeIngredient.setIngredientsId(ingredientArrayList);

        return recipeIngredient;
    }

    @Override
    public Collection<RecipeIngredient> getAll() {
        return null;
    }

    @Override
    public RecipeIngredient findById(int id) {
        Connection connection = newConnection();

        List<RecipeIngredient> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT retetar.*," +
                        "         1 AS unu " +
                        "   FROM retetar " +

                        "   WHERE retetar.id = " + id)) {

            while (rs.next()) {
                result.add(extractRecipeIngredients(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting photo!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Multiple photos for the ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    public RecipeIngredient update(RecipeIngredient recipeIngredient) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;


            if (recipeIngredient.getId() > 0) {

                ps = connection.prepareStatement( " UPDATE retetar SET idingrediente=?,instructiuni=? WHERE id = ? RETURNING id;");

            } else {
                ps = connection.prepareStatement(
                        "INSERT INTO retetar (idingrediente, instructiuni) VALUES (?, ?) RETURNING id;"
                );

            }
            Array ingredientArray = connection.createArrayOf("integer", recipeIngredient.getIngredientsId().toArray());


            ps.setArray(1, ingredientArray);
            ps.setString(2, recipeIngredient.getInstructions());

            if (recipeIngredient.getId() > 0) {
                ps.setInt(3, recipeIngredient.getId());
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
