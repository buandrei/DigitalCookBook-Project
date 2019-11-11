package ro.sci.digitalCookBook.dao.db;

import ro.sci.digitalCookBook.dao.PromovariDAO;
import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.Recipe;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.time.LocalDate.now;

public class JDBCPromovariDAO implements PromovariDAO {
    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;


    public JDBCPromovariDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }

    @Override
    public void add(Promovari promotion, Recipe recipe) {
        Connection conn = newConnection();
        try {
            PreparedStatement ps1 = conn.prepareStatement(
                    "INSERT INTO promovari (data_adaugare, data_final, idtip_promovare, iduser) VALUES (now(),?,?,1) RETURNING id;" , Statement.RETURN_GENERATED_KEYS
            );
            Timestamp timestamp = Timestamp.valueOf(promotion.getDataFinal());
            ps1.setTimestamp(1, timestamp);
            ps1.setInt(2, promotion.getIdTipPromovare());
//            ps1.setLong(1, promotion.getIdUser());
            ps1.executeUpdate();
            int result = ps1.executeUpdate();
            if (result == 0) {
                throw new SQLException("Inserarea datelor a esuat!");
            }
            try (ResultSet generatedKeys = ps1.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    promotion.setId(generatedKeys.getInt(1));
                    PreparedStatement ps2 = conn.prepareStatement("UPDATE retete SET idpromotie = ? WHERE id = ?");
                    ps2.setInt(1, promotion.getId());
                    ps2.setInt(2, recipe.getId());
                    ps2.executeUpdate();
                }
                else {
                    throw new SQLException("ID lipsa. Nu s-a modificate reteta!");
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare la salvarea in DB!", ex);
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public Collection<Promovari> getAll() {
        List<Promovari> result = new LinkedList<>();
        try (Connection conn = newConnection();
             ResultSet rs = conn.createStatement()
                     .executeQuery("SELECT * FROM promovari")) {
            while (rs.next()) {
                result.add(extragerePromovari(rs));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare extragere promovare!", ex);
        }
        return result;
    }

    private Promovari extragerePromovari(ResultSet rs) throws SQLException {
        Promovari promovare = new Promovari();
        promovare.setId(rs.getInt("id"));
        promovare.setDataAdaugare((rs.getTimestamp("data_adaugare")).toLocalDateTime());
        promovare.setDataFinal((rs.getTimestamp("data_final")).toLocalDateTime());
        return promovare;
    }

    @Override
    public Promovari listById(int id) {
        return findById(id);
    }

    @Override
    public Promovari update(Promovari promovare) {
        Connection conn = newConnection();
        try {
            PreparedStatement ps1 = conn.prepareStatement("UPDATE promovari SET idtip_promovare=? WHERE id=?;");
            ps1.setInt(1, promovare.getIdTipPromovare());
            ps1.setInt(2, promovare.getId());
            ps1.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare update promovare.", ex);
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
        return promovare;
    }

    // doar pt administrator e accesibil actiunea delete
    @Override
    public boolean delete(Promovari promotion) {
        Connection conn=newConnection();
        try {
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM promovari WHERE id=?;");
            ps1.setInt(1, promotion.getId());
            ps1.executeUpdate();
            try {
                    PreparedStatement ps2 = conn.prepareStatement("UPDATE retete SET idpromotie = DEFAULT WHERE idpromotie = ?");
                    ps2.setInt(1, promotion.getId());
                    ps2.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException("Eroare stergere promovare", ex);
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare stergere promovare", ex);
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public Promovari findById(int id) {
        Connection conn = newConnection();
        List<Promovari> result = new LinkedList<>();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM promovari WHERE id = " + id)) {
            while (rs.next()) {
                result.add(extragerePromovari(rs));
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
            throw new IllegalStateException("Mai multe promovari cu acelasi ID: " + id);
        }
        return result.isEmpty() ? null : result.get(0);
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
