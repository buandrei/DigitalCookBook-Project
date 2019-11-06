package ro.sci.digitalCookBook.domain;

import java.util.ArrayList;

public class RecipeIngredient extends AbstractModel{

    private String instructiuni;
    private ArrayList<Integer> idIngrediente;

    public String getInstructiuni() {
        return instructiuni;
    }

    public void setInstructiuni(String instructiuni) {
        this.instructiuni = instructiuni;
    }

    public ArrayList getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(ArrayList idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public String toString() {
        return "RecipeIngredients{" +
                "instructiuni='" + instructiuni + '\'' +
                ", idIngrediente=" + idIngrediente +
                '}';
    }
}
