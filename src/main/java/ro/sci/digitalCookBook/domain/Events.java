package ro.sci.digitalCookBook.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Events extends AbstractModel {

    @NotEmpty(message = "{denumire.notempty}")
    private String denumire;

    @NotEmpty(message = "{descriere.notempty}")
    private String descriere;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_start;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_final;

    @NotEmpty(message = "{organizator.notempty}")
    private String organizator;

//    @NotNull(message = "{idpromo.notnull}")
    private int idpromo;

//    @NotNull(message = "{iduser.notnull}")
    private int iduser;

//    @NotNull(message = "{inactiv.notnull}")
    private String inactiv;

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getData_start() {
        return data_start;
    }

    public void setData_start(Date data_start) {
        this.data_start = data_start;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public String getOrganizator() {
        return organizator;
    }

    public void setOrganizator(String organizator) {
        this.organizator = organizator;
    }

    public int getIdpromo() {
        return idpromo;
    }

    public void setIdpromo(int idpromo) {
        this.idpromo = idpromo;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getInactiv() {
        return inactiv;
    }

    public void setInactiv(String inactiv) {
        this.inactiv = inactiv;
    }

    @Override
    public String toString() {
        return "Events{" +
                "denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                ", data_start=" + data_start +
                ", data_final=" + data_final +
                ", organizator='" + organizator + '\'' +
                ", idpromo=" + idpromo +
//                ", iduser=" + iduser +
                ", inactiv='" + inactiv + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Events)) return false;
        Events events = (Events) o;
        return idpromo == events.idpromo &&
//                iduser == events.iduser &&
                Objects.equals(denumire, events.denumire) &&
                Objects.equals(descriere, events.descriere) &&
                Objects.equals(data_start, events.data_start) &&
                Objects.equals(data_final, events.data_final) &&
                Objects.equals(organizator, events.organizator) &&
                Objects.equals(inactiv, events.inactiv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, descriere, data_start, data_final, organizator, idpromo, /*iduser, */inactiv);
    }
}

