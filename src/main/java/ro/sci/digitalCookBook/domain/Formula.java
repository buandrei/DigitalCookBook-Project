package ro.sci.digitalCookBook.domain;

import java.util.Arrays;
import java.util.Objects;

public class Formula extends AbstractModel {

    private String instructioni;
    private int[] ingrediente;

    public String getInstructioni() {
        return instructioni;
    }

    public void setInstructioni(String instructioni) {
        this.instructioni = instructioni;
    }

    public int[] getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(int[] ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "instructioni='" + instructioni + '\'' +
                ", ingrediente=" + Arrays.toString(ingrediente) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formula formula = (Formula) o;
        return instructioni.equals(formula.instructioni) &&
                Arrays.equals(ingrediente, formula.ingrediente);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(instructioni);
        result = 31 * result + Arrays.hashCode(ingrediente);
        return result;
    }
}
