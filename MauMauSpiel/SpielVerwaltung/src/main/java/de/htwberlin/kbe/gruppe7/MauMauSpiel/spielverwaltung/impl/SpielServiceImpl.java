package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.exceptions.DaoException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.SpielService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.DAO.SpielDao;
import de.htwberlin.kbe.gruppe7.exceptions.IllegelStapelGroesseException;
import de.htwberlin.kbe.gruppe7.export.Stapel;
import de.htwberlin.kbe.gruppe7.export.StapelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class SpielServiceImpl implements SpielService {
    private StapelService stapelService;

    private KartenService kartenService;

    private RegelnService regelnService;

    private SpielerService spielerService;

    private KIService kiService;

    private SpielDao spielDao;

    private static Logger logger = LogManager.getLogger(SpielServiceImpl.class);

    @Override
    public Spiel spielAnlegen(List<Spieler> spielerList) throws IllegelStapelGroesseException {
        Stapel stapel = stapelService.stapelAnlegen(kartenService.anlegenStapel(32));
        Spiel spiel = new Spiel(spielerList, stapel);
        logger.info("Das Spiel wird angelegt: {}", spiel);
        spielDao.saveSpiel(spiel);
        return spiel;
    }

    @Override
    public void handkartenHandeln(Spiel spiel) {
        Stapel stapel = spiel.getZiehstapel();
        for (Spieler spieler : spiel.getSpielerListe()) {
            if (spieler.isVirtuellerSpieler()) {
                kiService.kartenHinzufuegen(spieler, stapelService.spielerHandkartenHandeln(stapel));
                logger.info("Spieler {} hat 5 anfängliche Handkarten: {}", spieler.getName(), spieler.getSpielerStapel());
            } else {
                spielerService.handKarteHinzufuegen(spieler, stapelService.spielerHandkartenHandeln(stapel));
                logger.info("Spieler {} hat 5 anfängliche Handkarten: {}", spieler.getName(), spieler.getSpielerStapel());
            }
        }
    }

    @Override
    public void naechsterSpielerWechseln(Spiel spiel) {
        spiel.setAktiverSpieler(getNextActivePlayer(spiel));
        if (spiel.getAktiverSpieler().isMussAussetzen()) {
            spiel.getAktiverSpieler().setMussAussetzen(false);
            naechsterSpielerWechseln(spiel);
        }
        logger.info("Der aktiver Spieler ist: {}", spiel.getAktiverSpieler());
    }

    private Spieler getNextActivePlayer(Spiel spiel) {
        Spieler aktiverSpieler = spiel.getAktiverSpieler();
        List<Spieler> spielerListe = spiel.getSpielerListe();
        int idxActivePlayer = spielerListe.indexOf(aktiverSpieler);

        if (spiel.isIstImUhrzeigersinn()) {
            if (idxActivePlayer == spielerListe.size() - 1) {
                aktiverSpieler = spielerListe.get(0);
            } else {
                aktiverSpieler = spielerListe.get(++idxActivePlayer);
            }
        } else {
            if (idxActivePlayer == 0) {
                aktiverSpieler = spielerListe.get(spielerListe.size() - 1);
            } else {
                aktiverSpieler = spielerListe.get(--idxActivePlayer);
            }
        }
        return aktiverSpieler;
    }

    @Override
    public void mussKartenZiehen(int numberOfDrawnCards, Spiel spiel) {
        Spieler aktiverSpieler = spiel.getAktiverSpieler();
        logger.info("Spieler {} muss {} Karten ziehen", aktiverSpieler.getName(), numberOfDrawnCards);
        List<Karte> drawCards = stapelService.kartenAusZiehstapelZiehen(spiel.getZiehstapel(), numberOfDrawnCards);

        if (aktiverSpieler.isVirtuellerSpieler()) {
            kiService.kartenHinzufuegen(aktiverSpieler, drawCards);
        } else {
            spielerService.handKarteHinzufuegen(aktiverSpieler, drawCards);
        }

        resetDrawCounter(spiel);
    }

    private void resetDrawCounter(Spiel spiel) {
        if (spiel.getAnzahlKartenZiehen() > 0) {
            spiel.setAnzahlKartenZiehen(0);
            logger.info("die Kartenanzahl wird auf 0 gesetzt");
        }
    }

    @Override
    public boolean darfKartenZiehen(Spiel spiel) {
        Spieler activePlayer = spiel.getAktiverSpieler();
        return regelnService.mussKarteZiehen(activePlayer, spiel.getZiehstapel().getObersteKarte(), spiel.getAnzahlKartenZiehen());
    }

    @Override
    public void wunschFarbeSetzen(Farbe Wunschfarbe, Spiel spiel) {
        spiel.setWunschFarbe(Wunschfarbe);
        logger.info("Der Wunsch des Spielers {} ist gesetzt", Wunschfarbe);
        spiel.setNaechsterSpielerMussFarbeWuenschen(false);
    }

    @Override
    public void ueberpruefenKarte(Karte karte, Spiel spiel) throws AbgelegteKarteIstUngueltig {
        Stapel stapel = spiel.getZiehstapel();
        Karte topCard = stapel.getObersteKarte();
        regelnService.ueberpruefenKarte(karte, topCard, spiel.getWunschFarbe(), spiel.getAnzahlKartenZiehen());
        stapelService.karteZuObersteKarteSetzen(stapel, karte);

        if (spiel.getAktiverSpieler().isVirtuellerSpieler()) {
            kiService.karteEntfernen(spiel.getAktiverSpieler(), karte);
        } else {
            spielerService.handKarteEntfernen(spiel.getAktiverSpieler(), karte);
        }

        logger.info("Karte {} hat die Überprüfung bestanden", karte);
        if (Objects.nonNull(spiel.getWunschFarbe())) {
            spiel.setWunschFarbe(null);
        }
    }

    @Override
    public void regelnAnwenden(Spiel spiel) {
        Karte obersteKarte = spiel.getZiehstapel().getObersteKarte();
        if (regelnService.mussKarteZiehen(obersteKarte)) {
            spiel.straffkartenErhoehen();
            logger.info("Die Kartenanzahl wird um {} erhöht", spiel.getAnzahlKartenZiehen());
        }
        if (regelnService.istMauUnGueltig(spiel.getAktiverSpieler())) {
            mussKartenZiehen(2, spiel);
            logger.info("'Mau von Spieler {} ist ungültig", spiel.getAktiverSpieler().getName());
        }
        if (regelnService.spielerAussetzen(obersteKarte)) {
            aussetzungsstatusAufSpielerSetzen(spiel);
        }
        if (regelnService.istBudeKarte(obersteKarte)) {
            spiel.setNaechsterSpielerMussFarbeWuenschen(true);
            logger.info("Spieler {} darf sich eine Farbe wünschen", spiel.getAktiverSpieler().getName());
        }
        if (regelnService.richtungWechsel(obersteKarte)) {
            spiel.richtungWechseln();
            logger.info("Die Richtung wird auf {} umgestellt.", spiel.isIstImUhrzeigersinn() ? "im Uhrzeigersinn" : "gegen den Uhrzeigersinn");
        }
    }

    private void aussetzungsstatusAufSpielerSetzen(Spiel spiel) {
        if (spiel.getSpielrunde() == 1) {
            spiel.getAktiverSpieler().setMussAussetzen(true);
            logger.info("Spieler {} muss in der aktuellen Runde aussetzen", spiel.getAktiverSpieler().getName());
        } else {
            Spieler nextPlayer = getNextActivePlayer(spiel);
            nextPlayer.setMussAussetzen(true);
            logger.info("Spieler {} muss in der nächsten Runde aussetzend", nextPlayer.getName());
        }
    }

    @Override
    public boolean istSpielAngeschlossen(Spiel spiel) {
        return spiel.getAktiverSpieler().getSpielerStapel().size() == 0;
    }

    @Override
    public void mauGesagt(Spiel game) {
        game.getAktiverSpieler().setMauGesagt(false);
        logger.info("Der Mau-Status des aktiven Players {} wird gesetzt", game.getAktiverSpieler().getName());
    }

    @Override
    public void saveSpiel(Spiel spiel) throws DaoException {
        try {
            spielDao.saveSpiel(spiel);
        } catch (DaoException e) {
            logger.warn("Irgendetwas ist schief gelaufen beim Speichern des Spiels");
        }
    }

    @Override
    public void deleteSpiel(Spiel spiel) throws DaoException {
        try {
            spielDao.deleteSpiel(spiel);
        } catch (DaoException e) {
            logger.warn("Irgendetwas ist schief gelaufen beim Loeschen des Spiels");
        }
    }

    @Override
    public boolean spielHaben() throws DaoException {
        return spielDao.findSpiel();
    }

    @Override
    public Spiel getSavedGame(long id) throws DaoException {
        return spielDao.findById(id);
    }

}
