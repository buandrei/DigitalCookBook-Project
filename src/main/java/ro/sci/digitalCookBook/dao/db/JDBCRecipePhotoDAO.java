package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipePhotoDAO;
import ro.sci.digitalCookBook.domain.RecipePhoto;

import java.sql.*;
import java.util.Collection;

public class JDBCRecipePhotoDAO implements RecipePhotoDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRecipeDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCRecipePhotoDAO(String host, String port, String dbName, String userName, String pass) {
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
    public Collection<RecipePhoto> getAll() {
        return null;
    }

    @Override
    public RecipePhoto findById(int id) {
     return null;
    }

    @Override
    public RecipePhoto update(RecipePhoto recipePhoto) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;

            if (recipePhoto.getId() > 0) {
                //TODO
                ps = connection.prepareStatement( " UPDATE poze SET cale_fisier=? WHERE id = ? RETURNING id;");

            } else {
                ps = connection.prepareStatement(
                        "INSERT INTO poze (cale_fisier) VALUES (?) RETURNING id;"
                );

            }

            ps.setString(1, recipePhoto.getCale_fisier());

            if (recipePhoto.getId() > 0) {
                ps.setInt(2, recipePhoto.getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipePhoto.setId(rs.getInt(1));
            }
            rs.close();

            connection.commit();

        } catch (SQLException e) {

            throw new RuntimeException("Error getting recipePhoto.", e);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        return recipePhoto;
    }

    @Override
    public boolean delete(RecipePhoto model) {
        return false;
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
}
