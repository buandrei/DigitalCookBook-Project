package ro.sci.digitalCookBook.domain;

import java.util.Objects;

public class TipPromovare extends AbstractModel {

    private String denumire;
    private int perioada;
    private String descriere;
    private long sumaPromovare;


    public TipPromovare() {
    }

    public TipPromovare(String denumire, int perioada, String descriere, long sumaPromovare){
        this.denumire=denumire;
        this.perioada=perioada;
        this.descriere=descriere;
        this.sumaPromovare=sumaPromovare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getPerioada() {
        return perioada;
    }

    public void setPerioada(int perioada) {
        this.perioada = perioada;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public long getSumaPromovare() {
        return sumaPromovare;
    }

    public void setSumaPromovare(long sumaPromovare) {
        this.sumaPromovare = sumaPromovare;
    }

    @Override
    public String toString() {
        return "TipPromovare{" +
                ", denumire='" + denumire + '\'' +
                ", perioada=" + perioada +
                ", descriere='" + descriere + '\'' +
                ", sumaPromovare=" + sumaPromovare +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipPromovare)) return false;
        TipPromovare that = (TipPromovare) o;
        return
                perioada == that.perioada &&
                sumaPromovare == that.sumaPromovare &&
                Objects.equals(denumire, that.denumire) &&
                Objects.equals(descriere, that.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash( denumire, perioada, descriere, sumaPromovare);
    }
}
