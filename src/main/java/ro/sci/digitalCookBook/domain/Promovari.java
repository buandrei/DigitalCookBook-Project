package ro.sci.digitalCookBook.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Promovari extends AbstractModel{
    private int id;
    private LocalDateTime dataAdaugare;
    private LocalDateTime dataFinal;
    private int idUser;
    private boolean starePromovare;
    private int idTipPromovare;

    public Promovari() { }

    public Promovari(int id, LocalDateTime dataAdaugare, LocalDateTime dataFinal, int idUser, boolean starePromovare, int idTipPromovare) {
        this.id=id;
        this.dataAdaugare=dataAdaugare;
        this.dataFinal=dataFinal;
        this.idUser=idUser;
        this.starePromovare=starePromovare;
        this.idTipPromovare=idTipPromovare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataAdaugare() {
        return dataAdaugare;
    }

    public void setDataAdaugare(LocalDateTime dataAdaugare) {
        this.dataAdaugare = dataAdaugare;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isStarePromovare() {
        return starePromovare;
    }

    public void setStarePromovare(boolean starePromovare) {
        this.starePromovare = starePromovare;
    }

    public int getIdTipPromovare() {
        return idTipPromovare;
    }

    public void setIdTipPromovare(int idTipPromovare) {
        this.idTipPromovare = idTipPromovare;
    }


    @Override
    public String toString() {
        return "Promovari{" +
                "id=" + id +
                ", dataAdaugare=" + dataAdaugare +
                ", dataFinal=" + dataFinal +
                ", idUser=" + idUser +
                ", starePromovare=" + starePromovare +
                ", idTipPromovare=" + idTipPromovare +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promovari)) return false;
        Promovari promovari = (Promovari) o;
        return id == promovari.id &&
                idUser == promovari.idUser &&
                starePromovare == promovari.starePromovare &&
                dataAdaugare== promovari.dataAdaugare &&
                dataFinal== promovari.dataFinal &&
                idTipPromovare == promovari.idTipPromovare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAdaugare, dataFinal, idUser, starePromovare, idTipPromovare);
    }

}
