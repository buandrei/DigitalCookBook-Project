package ro.sci.digitalCookBook.domain;

import java.util.ArrayList;

public class RecipeIngredient extends AbstractModel{

    private String instructions;
    private ArrayList<Integer> ingredientsId;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ArrayList getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(ArrayList ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Override
    public String toString() {
        return "RecipeIngredients{" +
                "instructions='" + instructions + '\'' +
                ", ingredientsId=" + ingredientsId +
                '}';
    }
}
