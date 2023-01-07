package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.SondernRegelnService;

import java.util.List;

public interface SpielService {

    void setRegelnwerk(boolean standardRegeln);

    SondernRegelnService getRegelnwerk();

    /**
     * Diese Methode legt ein Spiel an.
     *
     * @param spielerListe die liste alle spielern
     * @param regeln welche regeln fuer das spiel verwendet wird
     * @return Neues Spiel
     */
    Spiel spielAnlegen (List<String> spielerListe, boolean regeln);

    /**
     * Entnimmt eine oder mehrere Karten vom Ziehstapel.
     *
     * @param spiel das aktuelle Spiel
     * @return Verändertes Spiel
     */
    Spiel kartenAusZiehstapelZiehen(int anzahl, Spiel spiel);

    /**
     * Legt eine Karte auf den Ablagestapel. Vorab wird geprüft, ob diese gelegt werden darf.
     *
     * @param spiel          das aktuelle Spiel
     * @param abgelegteKarte die Karte, die abgelegt werden soll
     * @param spieler        der Spieler, der die Karte ablegen will
     * @return Verändertes Spiel
     */
    Spiel legeKarte(Spiel spiel, Karte abgelegteKarte, Spieler spieler);

    /**
     * Setzt die aktuelle Spielfarbe.
     *
     * @param farbe die aktuelle Spielfarbe
     * @param spiel das aktuelle Spiel
     * @return Verändertes Spiel
     */
    Spiel aktuelleFarbe(Farbe farbe, Spiel spiel);

    /**
     * Prüft, ob das Spiel abgeschlossen ist (1 Spieler hat keine Karten mehr).
     *
     * @param spieler der aktuelle Spieler
     * @return bool. Wert, der das Spielende markiert
     */
    boolean pruefeAufSpielende(Spieler spieler);

    /**
     * Prüft, ob der Spieler nur noch eine Karte auf der Hand hat.
     *
     * @param spieler der aktuelle Spieler
     * @return boolischer Wert, der angibt, ob der Spieler nur noch eine Karte auf der Hand hat
     */
    boolean pruefeAufMau(Spieler spieler);

    /**
     * Verteilt 5 Karten auf jeden Spieler.
     *
     * @param ziehstapel der Stapel von dem die Karten genommen werden sollen
     * @param teilnehmer Liste der Spieler die die Karten bekommen sollen
     * @return der reduzierte ziehstapel
     */
    List<Karte> fuenfKartenAusteilen(List<Karte> ziehstapel, List<Spieler> teilnehmer);

    /**
     * Setzt den nächsten aktiven Spieler.
     *
     * @param spiel Das alte Spielobjekt
     * @return Das neue Spielobjekt
     */
    Spiel setzeNaechstenSpieler(Spiel spiel, boolean spielerUeberspringen);

    /**
     * Aendert die Reihenfolge der Spieler.
     *
     * @param spiel Das alte Spielobjekt
     * @return Das neue Spielobjekt
     */
    Spiel richtungswechselAusfuehren(Spiel spiel);
}
