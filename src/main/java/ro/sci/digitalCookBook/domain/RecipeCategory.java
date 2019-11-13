package ro.sci.digitalCookBook.domain;

public class RecipeCategory extends AbstractModel{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
