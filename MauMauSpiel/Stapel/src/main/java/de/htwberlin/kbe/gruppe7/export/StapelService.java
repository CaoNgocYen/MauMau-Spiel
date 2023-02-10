package de.htwberlin.kbe.gruppe7.export;

import de.htwberlin.kbe.gruppe7.exceptions.IllegelStapelGroesseException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import java.util.List;

public interface StapelService {
    /**
     * Kartenstapel initialisieren
     * @param karten - Kartenstapel, der dem Deck hinzugefügt werden muss
     * @return Stapel, das den erforderlichen Kartenstapel enthält
     * @throws IllegelStapelGroesseException wenn der Kartenstapel leer ist oder ein ungültiges Suit Label Verhältnis hat
     */
    Stapel stapelAnlegen(List<Karte> karten) throws IllegelStapelGroesseException;

    /**
     * zu Beginn eines Spiels: Abhängig von der Anzahl der gezogenen Karten werden Karten vom Nachziehstapel ausgegeben
     * @param stapel - Kartenstapel
     * @return Handkartenstapel für Spieler
     */
    List<Karte> spielerHandkartenHandeln(Stapel stapel);

    /**
     * abhängig von der Anzahl der zu ziehenden Karten werden die Karten vom Nachziehstapel ausgeteilt
     * @param stapel - Kartenstapel
     * @param anzahlZuziehendeKarten - Anzahl der zu ziehenden Karten
     * @return Kartenliste der gezogenen Karten
     */
    List<Karte> kartenAusZiehstapelZiehen(Stapel stapel, int anzahlZuziehendeKarten);

    /**
     * Abgelegte Karte wird auf die neue oberste Karte gesetzt und die vorherige oberste Karte wird auf den Ablagestapel gesetzt
     * @param stapel - Kartenstapel
     * @param gespielteKarte - Karte, die auf die neue oberste Karte gesetzt werden muss
     * @return neue oberste Karte
     */
    Karte karteZuObersteKarteSetzen(Stapel stapel, Karte gespielteKarte);
}
