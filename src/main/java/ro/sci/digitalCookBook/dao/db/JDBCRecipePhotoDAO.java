package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipePhotoDAO;
import ro.sci.digitalCookBook.domain.RecipePhoto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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


    private RecipePhoto extractPhoto(ResultSet rs) throws SQLException {
        RecipePhoto recipePhoto = new RecipePhoto();

        recipePhoto.setId(rs.getInt("id"));
        recipePhoto.setContent(rs.getBytes("content"));
        recipePhoto.setFileName(rs.getString("file_name"));

        return recipePhoto;
    }



    @Override
    public Collection<RecipePhoto> getAll() {
        Collection<RecipePhoto> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT poze.* " +
                             "  1 AS unu " +
                             "FROM poze ")) {

            while (rs.next()) {
                result.add(extractPhoto(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting photos.", ex);
        }

        return result;
    }

    @Override
    public RecipePhoto findById(int id) {
        Connection connection = newConnection();

        List<RecipePhoto> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT poze.*," +
                        "         1 AS unu " +
                        "   FROM poze " +


                        "   WHERE poze.id = " + id)) {

            while (rs.next()) {
                result.add(extractPhoto(rs));
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
    public RecipePhoto update(RecipePhoto recipePhoto) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;

            if (recipePhoto.getId() > 0) {

                ps = connection.prepareStatement( " UPDATE poze SET content=?,file_name=? WHERE id = ? RETURNING id;");

            } else {
                ps = connection.prepareStatement(
                        "INSERT INTO poze (content,file_name) VALUES (?, ?) RETURNING id;"
                );

            }

            ps.setBytes(1, recipePhoto.getContent());
            ps.setString(2, recipePhoto.getFileName());

            if (recipePhoto.getId() > 0) {
                ps.setInt(3, recipePhoto.getId());
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
