package ro.sci.digitalCookBook.domain;

import java.util.Date;
import java.util.Objects;

public class PromovareAleasa extends AbstractModel{
    private String tipPromovare;

    public PromovareAleasa() { }

    public PromovareAleasa(String tipPromovaree) {
        this.tipPromovare=tipPromovare;
    }

    public String getTipPromovare() {
        return tipPromovare;
    }

    public void setTipPromovare(String tipPromovare) {
        this.tipPromovare = tipPromovare;
    }


    @Override
    public String toString() {
        return "PromovareAleasa{" +
                "tipPromovare=" + tipPromovare +
                ", perioada=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromovareAleasa)) return false;
        PromovareAleasa that = (PromovareAleasa) o;
        return tipPromovare == that.tipPromovare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipPromovare);
    }

}
