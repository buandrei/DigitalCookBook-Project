package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.Recipe;


import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *  JDBC implementation for {@link ro.sci.digitalCookBook.dao.RecipeDAO}.
 *
 * @author Andrei Bu
 *
 */

public class JDBCRecipeDAO implements RecipeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRecipeDAO.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCRecipeDAO(String host, String port, String dbName, String userName, String pass) {
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
    public Collection<Recipe> searchByName(String querry) {
        Collection<Recipe> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT retete.denumire," +
                             "  retete.portii," +
                             "  retete.data_adaugarii," +
                             "  retete.descriere," +
                             "  retete.istutorial," +
                             "  retete.link," +
                             "  retete.cautari," +
                             "  retete.rating," +
                             "  promovari.idtip_promovare," +
                             "  retete.idcategoria AS categoria," +
                             "  poze.cale_fisier AS thumbnail," +
                             "  1 AS unu" +

                             "FROM retete " +
                             "  LEFT JOIN poze ON poze.id = retete.idpoza" +
                             "  LEFT JOIN categorii_retete ON categorii_retete.id = retete.idcategoria" +
                             "  LEFT JOIN promovari ON promovari.id = retete.idpromotie" +
                             "WHERE" +
                             "  retete.inactiv = $$N$$")) {
            while (rs.next()) {
                result.add(extractRecipe(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipes.", ex);
        }

        return result;
    }

    private Recipe extractRecipe(ResultSet rs) throws SQLException {
        Recipe  recipe = new Recipe();

        recipe.setId(rs.getInt("id"));
        recipe.setDenumire(rs.getString("denumire"));
        recipe.setPortii(rs.getLong("portii"));
        recipe.setData_adaugarii(new Date(rs.getTimestamp("data_adaugarii").getTime()));
        recipe.setDescriere(rs.getString("descriere"));
        if(rs.getString("istutorial") == "D"){
            recipe.setIstutorial(true);
        }else{
            recipe.setIstutorial(false);
        }
        recipe.setLink(rs.getString("link"));
        recipe.setCautari(rs.getInt("cautari"));
        recipe.setRating(rs.getLong("rating"));
        recipe.setIdTipPromotie(rs.getInt("idtip_promovare"));
        recipe.setIdCategoria(rs.getInt("categoria"));

        return recipe;

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
    public Collection<Recipe> getAll() {
        Collection<Recipe> result = new LinkedList<>();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT retete.id," +
                             "  retete.denumire," +
                             "  retete.portii," +
                             "  retete.data_adaugarii," +
                             "  retete.descriere," +
                             "  retete.istutorial," +
                             "  retete.link," +
                             "  retete.cautari," +
                             "  retete.rating," +
                             "  promovari.idtip_promovare," +
                             "  retete.idcategoria AS categoria," +
                             "  poze.cale_fisier AS thumbnail," +
                             "  1 AS unu " +

                             "FROM retete " +
                             "  LEFT JOIN poze ON poze.id = retete.idpoza" +
                             "  LEFT JOIN categorii_retete ON categorii_retete.id = retete.idcategoria" +
                             "  LEFT JOIN promovari ON promovari.id = retete.idpromotie " +
                             "WHERE" +
                             "  retete.inactiv = $$N$$")) {

            while (rs.next()) {
                result.add(extractRecipe(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipes.", ex);
        }

        return result;
    }

    @Override
    public Recipe findById(int id) {
        Connection connection = newConnection();

        List<Recipe> result = new LinkedList<>();

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT * FROM retete WHERE id = " + id)) {

            while (rs.next()) {
                result.add(extractRecipe(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipe!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        if (result.size() > 1) {
            throw new IllegalStateException("Multiple recipes for the ID: " + id + " .Please contact support and send the error message!");
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Recipe update(Recipe recipe) {
        Connection connection = newConnection();
        try {
            PreparedStatement ps = null;

                ps = connection.prepareStatement(
                        "   UPDATE retete SET denumire=?, portii=?, descriere=?, istutorial=?, link=?, idcategoria=? "
                                + "WHERE id = ? RETURNING id;");


            ps.setString(1, recipe.getDenumire());
            ps.setLong(2, recipe.getPortii());
            ps.setString(3, recipe.getDescriere());
            ps.setString(4, (recipe.isIstutorial() ? "N" : "D"));
            ps.setString(5, recipe.getLink());
            ps.setInt(6, recipe.getIdCategoria());
            ps.setInt(7, recipe.getId());


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipe.setId(rs.getInt(1));
            }
            rs.close();

            connection.commit();

        } catch (SQLException e) {

            throw new RuntimeException("Error getting recipes.", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }

        return recipe;
    }


    public Recipe save(Recipe recipe) {
        Connection connection = newConnection();

        try {
            PreparedStatement ps = null;

                ps = connection.prepareStatement(
                        "INSERT INTO retete (denumire, portii, data_adaugarii, descriere, istutorial, link, iduser, idcategoria) "
                                + "VALUES(?, ?, now(), ?, ?, ?, 1 , ?) RETURNING id");


            ps.setString(1, recipe.getDenumire());
            ps.setLong(2, recipe.getPortii());
            ps.setString(3, recipe.getDescriere().toString());
            ps.setString(4, (recipe.isIstutorial() ? "N" : "D"));
            ps.setString(5, recipe.getLink());
            ps.setInt(6,recipe.getIdCategoria());

            ResultSet rs = ps.executeQuery();
            rs.close();
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipe.", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }

        return recipe;
    }

    /**
     * This method will upload a photo, asign it to the DB and then return the ID of the row
     * @param connection
     * @param isAdd
     * @return
     */
//    public int addPhotoTargetToDB(Connection connection, String isAdd){
//        int id = 1;
//        try{
//            PreparedStatement ps = null;
//            if(isAdd == "D"){
//                ps = connection.prepareStatement("UPDATE poze SET cale_fisier = ? WHERE id = ?");
//            }
//
//        }
//
//
//
//
//        return id;
//    }


    @Override
    public boolean delete(Recipe model) {
        return false;
    }
}

