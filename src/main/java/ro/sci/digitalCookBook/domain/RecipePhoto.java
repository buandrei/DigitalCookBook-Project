package ro.sci.digitalCookBook.domain;

public class RecipePhoto extends AbstractModel{
    private String cale_fisier;

    public String getCale_fisier() {
        return cale_fisier;
    }

    public void setCale_fisier(String cale_fisier) {
        this.cale_fisier = cale_fisier;
    }

    @Override
    public String toString() {
        return "RecipePhoto{" +
                "cale_fisier='" + cale_fisier + '\'' +
                '}';
    }
}
