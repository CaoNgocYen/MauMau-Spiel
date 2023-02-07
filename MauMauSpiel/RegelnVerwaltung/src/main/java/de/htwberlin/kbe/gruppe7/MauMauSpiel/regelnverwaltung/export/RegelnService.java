package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;

public interface RegelnService {
     /**
     * Prueft, ob eine Karte gespielt werden kann, auch ob die Karte JACK, SEVEN war und ob es einen Farbwunsch gibt
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @param obersteneKarte die obersten Karte des Ablagestapel
     * @param FarbeWuenschen die Farbe, die der Spieler wuenschen moechte nachdem die Karte Bude abgelegt ist
     * @param anzahlZuziehendKarten die Anzahl von Karten, die der Spieler ziehen muss
      *@return true, wenn die gelegte Karte gueltig ist
     * @throws AbgelegteKarteIstUngueltig werft exception wenn eine Karte nicht gespielt werden kann
     */
    void ueberpruefenKarte(Karte abgelegteKarte , Karte obersteneKarte, Farbe FarbeWuenschen, int anzahlZuziehendKarten) throws AbgelegteKarteIstUngueltig;

    /**
     * Prueft, ob der Spieler oder naeschten Spieler Karten ziehen muss wenn die Karte 7 abgelegt ist
     * @param spieler der Spieler ist dran
     * @param obersteKarte die obersten Karte des Ablagestapel
     * @param anzahlZuziehenKarten Anzahl der gezogenen Karten
     * @return true, wenn der Spieler Karten ziehen muss
     */
    boolean mussKarteZiehen(Spieler spieler, Karte obersteKarte, int anzahlZuziehenKarten);

    /**
     * Prueft, ob der Spieler eine SIEBEN spielen muss
     * @param anzahlZuziehenKarten Anzahl der gezogenen Karten
     * @param abgelegteWert Wert der gespielten Karte
     * @param obersteneWert Wert obersten Karte auf dem Ablagestapel
     * @return true, wenn der Spieler SEVEN spielen muss, aber nicht gespielt hat, false wenn nicht
     */
    boolean mussSiebenSpielen (int anzahlZuziehenKarten, Wert abgelegteWert, Wert obersteneWert);

    /**
     * Prueft, ob die Richtung des Spiels gewechselt wird nachdem die Karte 9 abgelegt ist
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @return true, wenn die Karte 9 abgelegt ist
     */
    boolean richtungWechsel(Karte abgelegteKarte);

    /**
     * Prueft, ob der naechsten Spieler ausgesetzt wird
     * @param obersteKarte die Karte, die der Spieler legt ab
     * @return true, wenn die Karte Ass abgelegt ist
     */
    boolean spielerAussetzen(Karte obersteKarte);

    Farbe sichFarbeWuenschen(Karte abgelegteKarte, int farbe);

    /**
     * Prueft, ob die letzte gespielte Karte ein Bube war und der Spieler einen Wunsch äußern kann
     * @param obersteKarte die oberste Karte auf dem Ablagestapel
     * @return true wenn Karte ein Bube war, false wenn nicht
     */
    boolean istBudeKarte(Karte obersteKarte);

    /**
     * Prueft ob die Wert der abgelegten Karte und obersten Karte Bude sind
     * @param abgelegteWert der Wert der Karte, die der Spieler legt ab
     * @param obersteneWert der Wert der Karte, die obersten Karte des Ablagestapel
     * @return true, wenn die Farbe der abgelegten Karte und obersten Karte Bude sind
     */
    boolean istBubeAufBube(Wert abgelegteWert, Wert obersteneWert);

    /**
     * Prueft, ob der Farbwunsch mit der Farbe der gespielten Karte übereinstimmt (wenn der Farbwunsch angegeben ist)
     * @param wunschFarbe die Wunschfarbe wird gespielt werden
     * @param abgelegteFarbe die Farbe von gespielten Karte
     * @return true, wenn die Farbe der gespielten Karte mit dem Farbwunsch übereinstimmt oder der Farbwunsch nicht gegeben ist, false, wenn nicht
     */
    boolean istWunschfarbePasst(Farbe wunschFarbe, Farbe abgelegteFarbe);

    /**
     * Prueft, ob Farbe oder Wert der abgelegten Karte mit der Farbe oder Wert der obersten Karte übereinstimmt
     * @param abgelegteKarte die gespielte Karte
     * @param obersteneKarte die oberste Karte auf dem Ablagestapel
     * @return true, wenn Farbe oder Wert übereinstimmen, false, wenn nicht
     */
    boolean wertOderFarbePasst(Karte abgelegteKarte, Karte obersteneKarte);

    //boolean prüfeZweiKartenZiehen (Karte gelegteKarte);

    //boolean prüfeGelegteKarteGueltig (Karte gelegteKarte, Karte letzteStapelKarte, Farbe spielFarbe);
    /**
     * Prueft, ob der Spieler Mau gesagt hat und ob es gültig ist
     * @param spieler der Spieler ist dran
     * @return false, wenn Spieler Mau gueltig ist, true, wenn es ungueltig ist
     */
    boolean istMauUnGueltig(Spieler spieler);

    /**
     * Prueft, ob ein Spieler Karten ziehen muss
     * @param obersteKarte die oberste Karte auf dem Ablagestapel
     * @return true wenn Karte der Wert SEVEN hat, false wenn nicht
     */
    boolean mussKarteZiehen(Karte obersteKarte);

}
