package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.SpielService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.SondernRegelnService;

import java.util.List;

public class SpielServiceImpl implements SpielService {

    @Override
    public void setRegelnwerk(boolean standardRegeln) {

    }

    @Override
    public SondernRegelnService getRegelnwerk() {
        return null;
    }

    /**
     * Diese Methode legt ein Spiel an.
     *
     * @param spielerListe die liste alle spielern
     * @param regeln welche regeln fuer das spiel verwendet wird
     * @return Neues Spiel
     */
    @Override
    public Spiel spielAnlegen(List<String> spielerListe, boolean regeln) {return null;
    }


    /**
     * Entnimmt eine oder mehrere Karten vom Ziehstapel.
     *
     * @param anzahl
     * @param spiel  das aktuelle Spiel
     * @return Verändertes Spiel
     */
    @Override
    public Spiel kartenAusZiehstapelZiehen(int anzahl, Spiel spiel) {
        return null;
    }

    /**
     * Legt eine Karte auf den Ablagestapel. Vorab wird geprüft, ob diese gelegt werden darf.
     *
     * @param spiel          das aktuelle Spiel
     * @param abgelegteKarte die Karte, die abgelegt werden soll
     * @param spieler        der Spieler, der die Karte ablegen will
     * @return Verändertes Spiel
     */
    @Override
    public Spiel legeKarte(Spiel spiel, Karte abgelegteKarte, Spieler spieler) {
        return null;
    }

    /**
     * Setzt die aktuelle Spielfarbe.
     *
     * @param farbe die aktuelle Spielfarbe
     * @param spiel das aktuelle Spiel
     * @return Verändertes Spiel
     */
    @Override
    public Spiel aktuelleFarbe(Farbe farbe, Spiel spiel) {
        return null;
    }

    /**
     * Prüft, ob das Spiel abgeschlossen ist (1 Spieler hat keine Karten mehr).
     *
     * @param spieler der aktuelle Spieler
     * @return bool. Wert, der das Spielende markiert
     */
    @Override
    public boolean pruefeAufSpielende(Spieler spieler) {
        return false;
    }

    /**
     * Prüft, ob der Spieler nur noch eine Karte auf der Hand hat.
     *
     * @param spieler der aktuelle Spieler
     * @return boolischer Wert, der angibt, ob der Spieler nur noch eine Karte auf der Hand hat
     */
    @Override
    public boolean pruefeAufMau(Spieler spieler) {
        return false;
    }

    /**
     * Verteilt 5 Karten auf jeden Spieler.
     *
     * @param ziehstapel der Stapel von dem die Karten genommen werden sollen
     * @param teilnehmer Liste der Spieler die die Karten bekommen sollen
     * @return der reduzierte ziehstapel
     */
    @Override
    public List<Karte> fuenfKartenAusteilen(List<Karte> ziehstapel, List<Spieler> teilnehmer) {
        return null;
    }

    /**
     * Setzt den nächsten aktiven Spieler.
     *
     * @param spiel                Das alte Spielobjekt
     * @param spielerUeberspringen
     * @return Das neue Spielobjekt
     */
    @Override
    public Spiel setzeNaechstenSpieler(Spiel spiel, boolean spielerUeberspringen) {
        return null;
    }

    /**
     * Aendert die Reihenfolge der Spieler.
     *
     * @param spiel Das alte Spielobjekt
     * @return Das neue Spielobjekt
     */
    @Override
    public Spiel richtungswechselAusfuehren(Spiel spiel) {
        return null;
    }
}
