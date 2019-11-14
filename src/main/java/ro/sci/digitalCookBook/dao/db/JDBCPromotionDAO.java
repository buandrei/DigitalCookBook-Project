package ro.sci.digitalCookBook.dao.db;

import ro.sci.digitalCookBook.dao.PromotionDAO;
import ro.sci.digitalCookBook.domain.PromotionType;
import ro.sci.digitalCookBook.domain.Promotion;
import ro.sci.digitalCookBook.domain.Recipe;
import ro.sci.digitalCookBook.service.PromotionTypeService;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.time.LocalDate.now;

public class JDBCPromotionDAO implements PromotionDAO {
    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;


    public JDBCPromotionDAO(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.userName = userName;
        this.pass = pass;
        this.port = port;
        this.dbName = dbName;
    }


    @Override
    public void add(Promotion promotion, Recipe recipe) {
        Connection conn = newConnection();
        try {
            PreparedStatement ps1 = conn.prepareStatement(
                    "INSERT INTO promovari (data_adaugare, data_final, idtip_promovare, iduser) VALUES (now(),?,?,1) RETURNING id;", Statement.RETURN_GENERATED_KEYS
            );
            Timestamp timestamp = Timestamp.valueOf(promotion.getFinalDate());
            ps1.setTimestamp(1, timestamp);
            ps1.setInt(2, promotion.getIdPromotionType());
//            ps1.setLong(1, promotion.getIdUser());

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
                } else {
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
    public Collection<Promotion> getAll() {
        List<Promotion> result = new LinkedList<>();
        try (Connection conn = newConnection();
             ResultSet rs = conn.createStatement()
                     .executeQuery("SELECT promovari.*, " +
                             " tip_promovari.denumire AS tip_promo_denumire," +
                             "    tip_promovari.descriere AS tip_promo_descriere," +
                             "    tip_promovari.perioada AS tip_promo_perioada," +
                             "    tip_promovari.suma AS tip_promo_suma" +
                             "" +
                             " FROM promovari" +
                             "  LEFT JOIN tip_promovari ON tip_promovari.id = promovari.idtip_promovare")) {
            while (rs.next()) {
                result.add(extractPromotion(rs));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare extragere promovare!", ex);
        }
        return result;
    }

    @Override
    public Collection<Promotion> getByUserId(int id) {
        List<Promotion> result = new LinkedList<>();
        try (Connection conn = newConnection();
             ResultSet rs = conn.createStatement()
                     .executeQuery("SELECT promovari.*, " +
                             " tip_promovari.denumire AS tip_promo_denumire," +
                             "    tip_promovari.descriere AS tip_promo_descriere," +
                             "    tip_promovari.perioada AS tip_promo_perioada," +
                             "    tip_promovari.suma AS tip_promo_suma" +
                             "" +
                             " FROM promovari" +
                             "  LEFT JOIN tip_promovari ON tip_promovari.id = promovari.idtip_promovare WHERE iduser=" + id + ";")) {
            while (rs.next()) {
                result.add(extractPromotion(rs));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Eroare extragere promovare!", ex);
        }
        return result;
    }


    private Promotion extractPromotion(ResultSet rs) throws SQLException {
        PromotionType promotionType = new PromotionType();
        promotionType.setId(rs.getInt("idtip_promovare"));
        promotionType.setName(rs.getString("tip_promo_denumire"));
        promotionType.setDescription(rs.getString("tip_promo_descriere"));
        promotionType.setPeriod(rs.getInt("tip_promo_perioada"));
        promotionType.setSumPromotion(rs.getLong("tip_promo_suma"));
        Promotion promotion = new Promotion();
        promotion.setId(rs.getInt("id"));
        promotion.setCreationDate((rs.getTimestamp("data_adaugare")).toLocalDateTime());
        promotion.setFinalDate((rs.getTimestamp("data_final")).toLocalDateTime());
        promotion.setIdUser(rs.getInt("iduser"));
        promotion.setPromotionType(promotionType);
        return promotion;
    }

    @Override
    public Promotion update(Promotion promotion) {
        Connection conn = newConnection();
        try {
            PreparedStatement ps1 = conn.prepareStatement("UPDATE promovari SET idtip_promovare=? WHERE id=?;");
            ps1.setInt(1, promotion.getIdPromotionType());
            ps1.setInt(2, promotion.getId());
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
        return promotion;
    }

    // doar pt administrator e accesibil actiunea delete
    @Override
    public boolean delete(Promotion promotion) {
        Connection conn = newConnection();
        try {
            StringBuilder query = new StringBuilder();
            query.append(" UPDATE retete SET idpromotie = DEFAULT WHERE idpromotie = " + promotion.getId() + ";");
            query.append(" DELETE FROM promovari WHERE id = " + promotion.getId() + ";");
            PreparedStatement ps1 = conn.prepareStatement(query.toString());
            System.out.println(query.toString());
            ps1.executeUpdate();
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

    @Override
    public Promotion findById(int id) {
        Connection conn = newConnection();
        List<Promotion> result = new LinkedList<>();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT promovari.*, " +
                        " tip_promovari.denumire AS tip_promo_denumire," +
                        "    tip_promovari.descriere AS tip_promo_descriere," +
                        "    tip_promovari.perioada AS tip_promo_perioada," +
                        "    tip_promovari.suma AS tip_promo_suma" +
                        "" +
                        " FROM promovari" +
                        "  LEFT JOIN tip_promovari ON tip_promovari.id = promovari.idtip_promovare " +

                          " WHERE promovari.id = " + id)) {
            while (rs.next()) {
                result.add(extractPromotion(rs));
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
