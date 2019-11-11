package ro.sci.digitalCookBook.domain;

import java.util.Objects;

public class RecipeCategories extends AbstractModel {

    String denumire;

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "RecipeCategories{" +
                "denumire='" + denumire + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategories that = (RecipeCategories) o;
        return denumire.equals(that.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire);
    }
}
