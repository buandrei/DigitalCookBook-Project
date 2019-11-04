package ro.sci.digitalCookBook.domain;

public class RecipeCategory extends AbstractModel{
    private String denumire;

    public String getDenumire() {
        return denumire;
    }

    public void setName(String name) {
        this.denumire = name;
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "name='" + denumire + '\'' +
                '}';
    }
}
