package ro.sci.digitalCookBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sci.digitalCookBook.dao.PromovareAleasaDAO;
import ro.sci.digitalCookBook.dao.PromovariDAO;
import ro.sci.digitalCookBook.dao.TipPromovariDAO;
import ro.sci.digitalCookBook.domain.PromovareAleasa;
import ro.sci.digitalCookBook.domain.Promovari;
import ro.sci.digitalCookBook.domain.TipPromovare;

import java.time.LocalDateTime;
import java.util.Collection;

import static java.time.LocalDate.now;

public class PromovariService {
    @Autowired
    private PromovariDAO daoPromovari;
    private PromovareAleasaDAO daoPromovareAleasa;
    private TipPromovariDAO daoTipPromovare;

    public Promovari addPromovare(PromovareAleasa model) {
        Promovari promovare=new Promovari();
        promovare.setDataAdaugare(extragereDatePromovare(model).getDataAdaugare());
        promovare.setDataFinal(extragereDatePromovare(model).getDataFinal());
        //promovare.setIdUser();
        promovare.setStarePromovare(true);
        promovare.setIdTipPromovare(extragereDatePromovare(model).getIdTipPromovare());
        if (validarePromovare(promovare)){daoPromovari.add(promovare);}
        return promovare;
    }

    //in cazul unei promovari active, acesta se modifica doar la un tip promovare superior
    public Promovari modificareIdTipPromovare(int idPromovare, int idNouTipPromovare){
        Promovari promovare=daoPromovari.findById(idPromovare);
        if(validareModificare(promovare)){
            promovare.setIdTipPromovare(idNouTipPromovare);
            promovare.setDataAdaugare(LocalDateTime.now());
            promovare.setDataFinal(promovare.getDataAdaugare().plusDays(daoTipPromovare.findTipById(idNouTipPromovare).getPerioada()));
            //promovare.setIdUser();
            promovare.setStarePromovare(true);
        }
        daoPromovari.update(promovare);
        return promovare;
    }

    private boolean validareModificare(Promovari promovare) {
        boolean updateValid=true;
        return updateValid;
    }


    private boolean validarePromovare(Promovari promovare) {
        boolean validPromovare=true;
        return validPromovare;
    }

    public Promovari list(int id) {
        return null;
    }

    public Collection<Promovari> listAll() {
        return null;
    }

    public boolean modificaDataFinal(int id) {
        return false;
    }

    public boolean modificaTipPromovare(int id) {
        return false;
    }

    public boolean delete(int id) {
/*        Promovari promovare = dao.findById(id);
        if (promovare!=null) {
            dao.delete(promovare);
            return true;
        }*/
        return false;
    }

    private Promovari extragereDatePromovare(PromovareAleasa promovareAleasa){
        Promovari promovari = new Promovari();
        promovari.setDataAdaugare(LocalDateTime.now());
        promovari.setDataFinal(promovari.getDataAdaugare().plusDays(daoPromovareAleasa.findTipByName(promovareAleasa).getPerioada()));
        promovari.setIdTipPromovare(daoPromovareAleasa.findTipByName(promovareAleasa).getId());
        return promovari;
    }


    public PromovariDAO getDao() {
        return daoPromovari;
    }

    public void setDao(PromovariDAO dao) {
        this.daoPromovari = dao;
    }
}
