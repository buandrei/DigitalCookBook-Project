package ro.sci.digitalCookBook.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Promotion extends AbstractModel {
    private int id;
    private LocalDateTime creationDate;
    private LocalDateTime finalDate;
    private int idUser;
    private boolean promotionState;
    private int idPromotionType;
    private PromotionType promotionType;

    public Promotion(int id, LocalDateTime creationDate, LocalDateTime finalDate, int idUser, boolean promotionState, int idPromotionType) {
        this.id = id;
        this.creationDate = creationDate;
        this.finalDate = finalDate;
        this.idUser = idUser;
        this.promotionState = promotionState;
        this.idPromotionType = idPromotionType;
    }

    public Promotion() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isPromotionState() {
        return promotionState;
    }

    public void setPromotionState(boolean promotionState) {
        this.promotionState = promotionState;
    }

    public int getIdPromotionType() {
        return idPromotionType;
    }

    public void setIdPromotionType(int idPromotionType) {
        this.idPromotionType = idPromotionType;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", finalDate=" + finalDate +
                ", idUser=" + idUser +
                ", promotionState=" + promotionState +
                ", idPromotionType=" + idPromotionType +
                ", promotionType=" + promotionType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promotion)) return false;
        Promotion promotion = (Promotion) o;
        return id == promotion.id &&
                idUser == promotion.idUser &&
                promotionState == promotion.promotionState &&
                idPromotionType == promotion.idPromotionType &&
                Objects.equals(creationDate, promotion.creationDate) &&
                Objects.equals(finalDate, promotion.finalDate) &&
                Objects.equals(promotionType, promotion.promotionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, finalDate, idUser, promotionState, idPromotionType, promotionType);
    }
}
