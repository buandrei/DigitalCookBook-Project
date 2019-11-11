package ro.sci.digitalCookBook.dao.db;

import ro.sci.digitalCookBook.dao.TipPromovariDAO;
import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.RecipeCategory;
import ro.sci.digitalCookBook.domain.TipPromovare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class JDBCTipPromovareDAO implements TipPromovariDAO {
    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;


    public JDBCTipPromovareDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }

    @Override
    public TipPromovare findTipById(int id) {
        Connection conn = newConnection();
        List<TipPromovare> result = new LinkedList<>();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from tip_promovari where id = " +id)) {
            while (rs.next()) {
                result.add(extragereTipPromovari(rs));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare extragere promovare", ex);
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {}
        }
        if (result.size() > 1) {
            throw new IllegalStateException("Mai multe tipuri de promovari cu acelasi ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    private TipPromovare extragereTipPromovari(ResultSet rs) throws SQLException{
        TipPromovare tipPromovare = new TipPromovare();
        tipPromovare.setId(rs.getInt("id"));
        tipPromovare.setDenumire(rs.getString("denumire"));
        tipPromovare.setPerioada(rs.getInt("perioada"));
        tipPromovare.setDescriere(rs.getString("descriere"));
        tipPromovare.setSumaPromovare(rs.getLong(   "suma"));
        return tipPromovare;
    }

    @Override
    public Collection<TipPromovare> getAll() {
        Collection<TipPromovare> result = new LinkedList<>();
        try (Connection connection = newConnection();
             ResultSet rs = connection.createStatement()
                     .executeQuery("" +
                             "SELECT tip_promovari.* FROM tip_promovari ")) {

            while (rs.next()) {
                result.add(extragereTipPromovari(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Couldn't get promotion types!.", ex);
        }
        return result;
    }

    @Override
    public TipPromovare findById(int id) {
        return null;
    }

    @Override
    public TipPromovare update(TipPromovare model) {
        return null;
    }

    @Override
    public boolean delete(TipPromovare model) {
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
