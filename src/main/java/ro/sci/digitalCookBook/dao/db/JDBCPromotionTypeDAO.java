package ro.sci.digitalCookBook.dao.db;

import ro.sci.digitalCookBook.dao.PromotionTypeDAO;
import ro.sci.digitalCookBook.domain.PromotionType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class JDBCPromotionTypeDAO implements PromotionTypeDAO {
    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;


    public JDBCPromotionTypeDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }


    @Override
    public PromotionType findById(int id) {

        Connection conn = newConnection();
        List<PromotionType> result = new LinkedList<>();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tip_promovari WHERE id = " + id)) {
            while (rs.next()) {
                result.add(extractPromotionType(rs));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare extragere promovare", ex);
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
        if (result.size() > 1) {
            throw new IllegalStateException("Mai multe tipuri de promovari cu acelasi ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    private PromotionType extractPromotionType(ResultSet rs) throws SQLException {
        PromotionType promotionType = new PromotionType();
        promotionType.setId(rs.getInt("id"));
        promotionType.setName(rs.getString("denumire"));
        promotionType.setPeriod(rs.getInt("perioada"));
        promotionType.setDescription(rs.getString("descriere"));
        promotionType.setSumPromotion(rs.getLong("suma"));
        return promotionType;
    }

    @Override
    public Collection<PromotionType> getAll() {
        Collection<PromotionType> result = new LinkedList<>();
        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT tip_promovari.* FROM tip_promovari ")) {

            while (rs.next()) {
                result.add(extractPromotionType(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Couldn't get promotion types!.", ex);
        }
        return result;
    }

    @Override
    public PromotionType update(PromotionType model) {
        return null;
    }

    @Override
    public boolean delete(PromotionType model) {
        return false;
    }

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
        } catch (Exception ex) {
            throw new RuntimeException("Error getting DB connection", ex);
        }

    }

}
