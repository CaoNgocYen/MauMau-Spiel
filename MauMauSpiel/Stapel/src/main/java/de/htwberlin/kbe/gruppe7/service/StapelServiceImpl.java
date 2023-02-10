package de.htwberlin.kbe.gruppe7.service;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.exceptions.IllegelStapelGroesseException;
import de.htwberlin.kbe.gruppe7.export.Stapel;
import de.htwberlin.kbe.gruppe7.export.StapelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StapelServiceImpl implements StapelService {

    private KartenService kartenService;

    private static Logger logger = LogManager.getLogger(StapelServiceImpl.class);

    @Override
    public Stapel stapelAnlegen(List<Karte> karten) throws IllegelStapelGroesseException {
        Stapel stapel = new Stapel();
        Collections.shuffle(karten);
        obersteKarteSetzen(karten, stapel);
        stapel.setZiehStapel(karten);
        logger.info("Stapel ist initialisiert: {}", stapel);
        return stapel ;
    }

    @Override
    public List<Karte> spielerHandkartenHandeln(Stapel stapel) {
        List <Karte> ziehStapel = stapel.getZiehStapel();
        ArrayList<Karte> spielerHandKarten = new ArrayList<>(ziehStapel.subList(0, stapel.getAnzahlAnfangSpielerHandKarten()));
        ziehStapel.removeAll(spielerHandKarten);
        return spielerHandKarten;
    }

    @Override
    public List<Karte> kartenAusZiehstapelZiehen(Stapel stapel, int anzahlZuziehendeKarten) {
        List<Karte> ziehStapel = stapel.getZiehStapel();
        List<Karte> zuziehendeKarten = new ArrayList<>();
        if(ziehStapel.size() <= anzahlZuziehendeKarten) {
            ziehStapel.addAll(stapel.getAblageStapel());
            stapel.getAblageStapel().removeAll(stapel.getAblageStapel());
            Collections.shuffle(ziehStapel);
        }
        zuziehendeKarten.addAll(ziehStapel.subList(0, anzahlZuziehendeKarten));
        ziehStapel.removeAll(zuziehendeKarten);
        return zuziehendeKarten;
    }

    private void obersteKarteSetzen(List<Karte> karten, Stapel stapel) {
        Karte letzteKarte = karten.get((karten.size()-1));
        stapel.setObersteKarte(letzteKarte);
        logger.info("Oberste Karte ist {}", letzteKarte);
        karten.remove(letzteKarte);
    }

    @Override
    public Karte karteZuObersteKarteSetzen(Stapel stapel, Karte karte) {
        stapel.getAblageStapel().add(stapel.getObersteKarte());
        stapel.setObersteKarte(karte);
        return stapel.getObersteKarte();
    }
}
