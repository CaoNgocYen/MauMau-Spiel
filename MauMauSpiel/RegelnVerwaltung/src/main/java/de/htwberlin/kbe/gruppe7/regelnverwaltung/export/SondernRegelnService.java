package de.htwberlin.kbe.gruppe7.regelnverwaltung.export;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;

public interface SondernRegelnService {


    /**
     * validates if a card can be played, also if card was JACK, SEVEN and if there is a suit wish
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @param obersteneKarte die obersten Karte des Ablagestapel
     * @param FarbeWuenschen die Farbe, die der Spieler wuenschen moechte nachdem die Karte Bude abgelegt ist
     * @param anzahlZuziehendKarten die Anzahl von Karten, die der Spieler ziehen muss
     * @throws KarteNichtGefundenException werft exception wenn eine Karte nicht gespielt werden kann
     */
    void ueberpruefenKarte(Karte abgelegteKarte , Karte obersteneKarte, Farbe FarbeWuenschen, int anzahlZuziehendKarten) throws KarteNichtGefundenException;

    /**
     * Prueft, ob der Spieler oder naeschten Spieler Karten ziehen muss wenn die Karte 7 abgelegt ist
     * @param abgelegteWert der Spieler ist dran
     * @param obersteneWert die obersten Karte des Ablagestapel
     * @return true, wenn der Spieler Karten ziehen muss
     */
    boolean mussKarteZiehen(Wert abgelegteWert, Wert obersteneWert);

    /**
     * Prueft, ob die Richtung des Spiels gewechselt wird nachdem die Karte 9 abgelegt ist
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @return true, wenn die Karte 9 abgelegt ist
     */
    boolean richtungWechsel(Karte abgelegteKarte);

    /**
     * Prueft, ob der naechsten Spieler ausgesetzt wird
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @return true, wenn die Karte Ass abgelegt ist
     */
    boolean spielerAussetzen(Karte abgelegteKarte);

    /**
     * Prueft, welche Farbe, die Spieler wechseln moechte
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @return int, die WuenschenFarbe
     */
    Farbe sichFarbeWuenschen(Karte abgelegteKarte, int farbe);

    /**
     * Prueft ob die Wert der abgelegten Karte und obersten Karte Bude sind
     * @param abgelegteWert der Wert der Karte, die der Spieler legt ab
     * @param obersteneWert der Wert der Karte, die obersten Karte des Ablagestapel
     * @return true, wenn die Farbe der abgelegten Karte und obersten Karte Bude sind
     */
    boolean istBudeUeberBude(Wert abgelegteWert, Wert obersteneWert);

    /**
     * Prueft ob der Spieler eine Karte hat, die zu der Wunschfarbe des vorherigen Spieler passt
     * @param abgelegteFarbe die WunschFarbe abgelegt werden soll
     * @param wunschFarbe die WunschFarbe abgelegt wurden
     * @return true wenn die ablegende Farbe zu der Wunschfarbe passt
     */
    boolean istWunschfarbePasst(Farbe abgelegteFarbe, Farbe wunschFarbe);

    /**
     * Prueft ob der Wert oder die Farbe der abgelegten Karte zu der obersten Karte passt
     * @param abgelegteKarte die Karte, die der Spieler legt ab
     * @param obersteneKarte die obersten Karte des Ablagestapel
     * @return true wenn der Wert oder die Farbe passt
     */
    boolean wertOderFarbePasst(Karte abgelegteKarte, Karte obersteneKarte);

    int zweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    int zweiKartenZiehen(Karte gelegteKarte, Spieler spieler);

}
