package ro.sci.digitalCookBook.domain;

import java.util.Comparator;
import java.util.Objects;

public class Ingredient extends AbstractModel  {
    private String name;
    private String um;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", um='" + um + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name) &&
                Objects.equals(um, that.um);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, um);
    }



}

