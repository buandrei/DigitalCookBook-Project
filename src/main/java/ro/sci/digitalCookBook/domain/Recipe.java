package ro.sci.digitalCookBook.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Used to define the information needed for a Recipe.
 *
 * @author Andrei Bu

 */

public class Recipe extends AbstractModel{


    private String name;

    @NotNull
    private long portions;

    @DateTimeFormat(pattern ="dd/MM/yyyy")
    private Date addDate;

    private String description;
    private boolean istutorial;
    private String  link;
    private int accessCount;
    private double rating;
    private int idTipPromotie;
    private User user;
    private RecipeCategory recipeCategory;
    private int idCategoria;

    private RecipePhoto photo;
    private int photoId;

    private RecipeIngredient recipeIngredient;
    private int recipeId;

    private char inactiv;
    private long cookingTime;
    private long preparationTime;


    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPortions() {
        return portions;
    }

    public void setPortions(long portions) {
        this.portions = portions;
    }

    public Date getData_adaugarii() {
        return addDate;
    }

    public void setData_adaugarii(Date data_adaugarii) {
        this.addDate = data_adaugarii;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIstutorial() {
        return istutorial;
    }

    public void setIstutorial(boolean istutorial) {
        this.istutorial = istutorial;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getIdTipPromotie() {
        return idTipPromotie;
    }

    public void setIdTipPromotie(int idTipPromotie) {
        this.idTipPromotie = idTipPromotie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public RecipePhoto getPhoto() {
        return photo;
    }

    public void setPhoto(RecipePhoto photo) {
        this.photo = photo;
    }

    public RecipeIngredient getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(RecipeIngredient recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public char getInactiv() {
        return inactiv;
    }

    public void setInactiv(char inactiv) {
        this.inactiv = inactiv;
    }


    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public long getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(long preparationTime) {
        this.preparationTime = preparationTime;
    }



    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", portions=" + portions +
                ", addDate=" + addDate +
                ", description='" + description + '\'' +
                ", istutorial=" + istutorial +
                ", link='" + link + '\'' +
                ", accessCount=" + accessCount +
                ", rating=" + rating +
                ", idTipPromotie=" + idTipPromotie +
                ", user=" + user +
                ", recipeCategory=" + recipeCategory +
                ", photo=" + photo +
                ", photoId=" + photoId +
                ", recipeIngredient=" + recipeIngredient +
                ", inactiv=" + inactiv +
                ", cookingTime=" + cookingTime +
                ", preparationTime=" + preparationTime +
                ", idCategoria=" + idCategoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe other = (Recipe) o;
        if (addDate == null) {
            if (other.addDate != null)
                return false;
        } else if (!addDate.equals(other.addDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (istutorial != other.istutorial)
            return false;
        if (inactiv != other.inactiv)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, portions, addDate, description, istutorial, link, accessCount, rating, idTipPromotie, user, recipeCategory, photo, recipeIngredient, inactiv);
    }
}
