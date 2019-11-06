package ro.sci.digitalCookBook.domain;

import java.util.Objects;

public class Ingredient extends AbstractModel{
    private String denumire;
    private String um;

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "denumire='" + denumire + '\'' +
                ", um='" + um + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return denumire.equals(that.denumire) &&
                Objects.equals(um, that.um);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, um);
    }
}

