package ro.sci.digitalCookBook.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Used to define the information needed for an Employee.
 *
 * @author Andrei Bu

 */

public class Recipe extends AbstractModel{

    @NotEmpty(message = "{Denumirea retetei este obligatorie!}")
    private String denumire;

    @NotNull
    private long portii;

    @DateTimeFormat(pattern ="dd/MM/yyyy")
    private Date dataAdaugarii;

    private String descriere;
    private boolean istutorial;
    private String  link;
    private int cautari;
    private long rating;
    private int idTipPromotie;
    private int idUser;
    private int idCategoria;
    private int idPoza;
    private int idRetetar;
    private char inactiv;

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public long getPortii() {
        return portii;
    }

    public void setPortii(long portii) {
        this.portii = portii;
    }

    public Date getData_adaugarii() {
        return dataAdaugarii;
    }

    public void setData_adaugarii(Date data_adaugarii) {
        this.dataAdaugarii = data_adaugarii;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
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

    public int getCautari() {
        return cautari;
    }

    public void setCautari(int cautari) {
        this.cautari = cautari;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public int getIdTipPromotie() {
        return idTipPromotie;
    }

    public void setIdTipPromotie(int idTipPromotie) {
        this.idTipPromotie = idTipPromotie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdPoza() {
        return idPoza;
    }

    public void setIdPoza(int idPoza) {
        this.idPoza = idPoza;
    }

    public int getIdRetetar() {
        return idRetetar;
    }

    public void setIdRetetar(int idRetetar) {
        this.idRetetar = idRetetar;
    }

    public char getInactiv() {
        return inactiv;
    }

    public void setInactiv(char inactiv) {
        this.inactiv = inactiv;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "denumire='" + denumire + '\'' +
                ", portii='" + portii + '\'' +
                ", data_adaugarii=" + dataAdaugarii +
                ", descriere='" + descriere + '\'' +
                ", istutorial=" + istutorial +
                ", link='" + link + '\'' +
                ", cautari='" + cautari + '\'' +
                ", rating=" + rating +
                ", idTipPromotie=" + idTipPromotie +
                ", idUser=" + idUser +
                ", idCategoria=" + idCategoria +
                ", idPoza=" + idPoza +
                ", idRetetar=" + idRetetar +
                ", inactiv=" + inactiv +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe other = (Recipe) o;
        if (dataAdaugarii == null) {
            if (other.dataAdaugarii != null)
                return false;
        } else if (!dataAdaugarii.equals(other.dataAdaugarii))
            return false;
        if (descriere == null) {
            if (other.descriere != null)
                return false;
        } else if (!descriere.equals(other.descriere))
            return false;
        if (istutorial != other.istutorial)
            return false;
        if (inactiv != other.inactiv)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, portii, dataAdaugarii, descriere, istutorial, link, cautari, rating, idTipPromotie, idUser, idCategoria, idPoza, idRetetar, inactiv);
    }
}
