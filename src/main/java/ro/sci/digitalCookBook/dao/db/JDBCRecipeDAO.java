package ro.sci.digitalCookBook.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.domain.*;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JDBC implementation for {@link ro.sci.digitalCookBook.dao.RecipeDAO}.
 *
 * @author Andrei Bu
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
     *
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
                             "  retete.timp_preparare," +
                             "  retete.timp_gatire," +
                             "  promovari.idtip_promovare AS idtip_promovare," +
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
        Recipe recipe = new Recipe();

        recipe.setId(rs.getInt("id"));
        recipe.setName(rs.getString("denumire"));
        recipe.setPortions(rs.getLong("portii"));
        recipe.setAddDate(new Date(rs.getTimestamp("data_adaugarii").getTime()));
        recipe.setDescription(rs.getString("descriere"));
        if (rs.getString("istutorial") == "D") {
            recipe.setIstutorial(true);
        } else {
            recipe.setIstutorial(false);
        }
        recipe.setLink(rs.getString("link"));
        recipe.setAccessCount(rs.getInt("cautari"));
        recipe.setRating(rs.getDouble("rating"));
        recipe.setIdTipPromotie(rs.getInt("idtip_promovare"));
        recipe.setIdCategoria(rs.getInt("idcategoria"));

        RecipePhoto recipePhoto =  new JDBCRecipePhotoDAO(host,port,dbName,userName,pass).findById(rs.getInt("idpoza"));
        recipe.setPhoto(recipePhoto);
        recipe.setPhotoId(rs.getInt("idpoza"));

        RecipeIngredient recipeIngredient =  new JDBCRecipeIngredientDAO(host,port,dbName,userName,pass).findById(rs.getInt("idretetar"));
        recipe.setRecipeIngredient(recipeIngredient);
        recipe.setRecipeId(rs.getInt("idretetar"));

        RecipeCategory recipeCategory =  new JDBCRecipeCategoryDAO(host,port,dbName,userName,pass).findById(rs.getInt("idcategoria"));
        recipe.setRecipeCategory(recipeCategory);

        recipe.setCookingTime(rs.getInt("timp_gatire"));
        recipe.setPreparationTime(rs.getInt("timp_preparare"));

        return recipe;

    }
    @Override
    public Collection<Recipe> getAll() {

        Collection<Recipe> result = new ArrayList();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT retete.id," +
                             "  retete.denumire," +
                             "  retete.portii," +
                             "  retete.data_adaugarii," +
                             "  retete.descriere," +
                             "  retete.istutorial," +
                             "  retete.rating," +
                             "  categorii_retete.denumire AS categoria," +
                             "  promovari.idtip_promovare AS idtip_promovare," +
                             "  poze.content AS thumbnail," +
                             "  COALESCE(InitCap(app_user.nume), $$$$) ||  $$ $$ || COALESCE(InitCap(app_user.prenume), $$$$) AS user," +
                             "  1 AS unu " +

                             "FROM retete " +
                             "  LEFT JOIN poze ON poze.id = retete.idpoza" +
                             "  LEFT JOIN categorii_retete ON categorii_retete.id = retete.idcategoria" +
                             "  LEFT JOIN promovari ON promovari.id = retete.idpromotie " +
                             "  LEFT JOIN app_user ON app_user.id = retete.iduser " +
                             "WHERE" +
                             "  retete.inactiv = $$N$$")) {

            while (rs.next()) {
                result.add(extractRecipeForList(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipes.", ex);
        }

           return result;
       }




    private Recipe extractRecipeForList(ResultSet rs) throws SQLException {
        Recipe recipe = new Recipe();
        RecipePhoto recipePhoto = new RecipePhoto();
        RecipeCategory recipeCategory = new RecipeCategory();
        User user = new User();

        recipe.setId(rs.getInt("id"));
        recipe.setName(rs.getString("denumire"));
        recipe.setPortions(rs.getLong("portii"));
        recipe.setAddDate(new Date(rs.getTimestamp("data_adaugarii").getTime()));
        recipe.setDescription(rs.getString("descriere"));
        if (rs.getString("istutorial") == "D") {
            recipe.setIstutorial(true);
        } else {
            recipe.setIstutorial(false);
        }
        recipe.setRating(rs.getDouble("rating"));
        recipeCategory.setName(rs.getString("categoria"));
        recipe.setRecipeCategory(recipeCategory);
        recipe.setIdTipPromotie(rs.getInt("idtip_promovare"));

        recipePhoto.setContent(rs.getBytes("thumbnail"));
        recipe.setPhoto(recipePhoto);

        user.setNume(rs.getString("user"));
        recipe.setUser(user);


        return recipe;

    }

    @Override
    public Recipe findById(int id) {
        Connection connection = newConnection();

        List<Recipe> result = new LinkedList<>();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE retete SET cautari = cautari + 1 WHERE id = " + id);
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

        try (ResultSet rs = connection.createStatement()
                .executeQuery("SELECT retete.*," +
                        "            promovari.idtip_promovare AS idtip_promovare," +
                        "            1 AS unu " +
                        "           FROM retete " +
                        "               LEFT JOIN promovari ON promovari.id = retete.idpromotie " +
                        "           WHERE retete.id = " + id)) {

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

            if (recipe.getId() > 0) {
                StringBuilder querry = new StringBuilder();
                querry.append(" UPDATE retete SET " +
                                "   denumire=?, " +
                                "   portii=?, " +
                                "   descriere=?, " +
                                "   istutorial=?, " +
                                "   link=?, " +
                                "   iduser=1, " +
                                "   idcategoria=?, " +
                                "   idpoza=?, " +
                                "   idretetar=?, " +
                                "   timp_gatire=?, " +
                                "   timp_preparare=?  " +
                               " WHERE id = ? RETURNING id;");

                ps = connection.prepareStatement(querry.toString());

            } else {
                ps = connection.prepareStatement(
                        "INSERT INTO retete (denumire, portii, data_adaugarii, descriere, istutorial, link, iduser, idcategoria, idpoza, idretetar, timp_gatire, timp_preparare) "
                                + "VALUES(?, ?, now(), ?, ?, ?, 1 , ?, ?, ?, ?, ?) RETURNING id;"
                );

            }

            ps.setString(1, recipe.getName());
            ps.setLong(2, recipe.getPortions());
            ps.setString(3, recipe.getDescription());
            ps.setString(4, (recipe.isIstutorial() ? "D" : "N"));
            ps.setString(5, setEmbedOnlyLink(recipe.getLink()));
            ps.setInt(6, recipe.getRecipeCategory().getId());
            ps.setInt(7, recipe.getPhoto().getId());
            ps.setInt(8, recipe.getRecipeIngredient().getId());
            ps.setLong(9, recipe.getCookingTime());
            ps.setLong(10, recipe.getPreparationTime());

            if (recipe.getId() > 0) {
                ps.setInt(11, recipe.getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipe.setId(rs.getInt(1));
            }
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

    private String setEmbedOnlyLink(String link) {
        Pattern p = Pattern.compile("src=\"(.*?)\"");
        Matcher m = p.matcher(link);
        String emdedLink = "";
        if (m.find()) {
            emdedLink = m.group(1);
        } else {
            emdedLink = link;
        }

        return emdedLink;
    }

    public boolean giveRating(Recipe recipe, long rating){
        boolean result = false;
        Connection connection = newConnection();
        try {
            Statement statement = connection.createStatement();
            result = statement.execute("" +
                            "   UPDATE retete SET nr_rating = nr_rating + 1 WHERE id = "+recipe.getId() +";"+
                            "   UPDATE retete SET " +
                            "   rating = " +
                            "   CASE WHEN retete.rating = 0 " +
                            "       THEN "+rating+" " +
                            "       ELSE (rating + " + rating + ") / CASE WHEN nr_rating = 0 THEN 1 ELSE nr_rating END " +
                            "   END     " +
                            "   WHERE id = "  + recipe.getId());
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error updating the rating!", ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return result;

    }

    @Override
    public Collection<Recipe> getAllWherePromotionNotNull() {

        Collection<Recipe> result = new ArrayList();

        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT retete.id," +
                             "  retete.denumire," +
                             "  retete.portii," +
                             "  retete.data_adaugarii," +
                             "  retete.descriere," +
                             "  retete.istutorial," +
                             "  retete.rating," +
                             "  categorii_retete.denumire AS categoria," +
                             "  promovari.idtip_promovare AS id" +
                             "  promovari.tip_promovare," +
                             "  poze.content AS thumbnail," +
                             "  COALESCE(InitCap(app_user.nume), $$$$) ||  $$ $$ || COALESCE(InitCap(app_user.prenume), $$$$) AS user," +
                             "  1 AS unu " +

                             "FROM retete " +
                             "  LEFT JOIN poze ON poze.id = retete.idpoza" +
                             "  LEFT JOIN categorii_retete ON categorii_retete.id = retete.idcategoria" +
                             "  LEFT JOIN promovari ON promovari.id = retete.idpromotie " +
                             "  LEFT JOIN app_user ON app_user.id = retete.iduser " +
                             "WHERE" +
                             "  retete.idTipPromovare IS NOT NULL")) {

            while (rs.next()) {
                result.add(extractRecipeForList(rs));
            }
            connection.commit();
        } catch (SQLException ex) {

            throw new RuntimeException("Error getting recipes.", ex);
        }

        return result;
    }

    @Override
    public boolean delete(Recipe model) {
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

