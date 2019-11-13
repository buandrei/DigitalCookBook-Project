package ro.sci.digitalCookBook.domain;

import java.util.Objects;

public class PromotionType extends AbstractModel {
    private int id;
    private String name;
    private int period;
    private String description;
    private long sumPromotion;


    public PromotionType() {
    }

    public PromotionType(String name, int period, String description, long sumPromotion) {
        this.name = name;
        this.period = period;
        this.description = description;
        this.sumPromotion = sumPromotion;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSumPromotion() {
        return sumPromotion;
    }

    public void setSumPromotion(long sumPromotion) {
        this.sumPromotion = sumPromotion;
    }

    @Override
    public String toString() {
        return "PromotionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", period=" + period +
                ", description='" + description + '\'' +
                ", sumPromotion=" + sumPromotion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromotionType)) return false;
        PromotionType that = (PromotionType) o;
        return id == that.id &&
                period == that.period &&
                sumPromotion == that.sumPromotion &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, period, description, sumPromotion);
    }
}
