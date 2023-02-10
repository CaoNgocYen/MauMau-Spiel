package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.exceptions.DaoException;
import de.htwberlin.kbe.gruppe7.exceptions.IllegelStapelGroesseException;

import java.util.List;

public interface SpielService {

    /**
     * initialisiert ein Spiel mit der gewünschten Anzahl von Spielern und dem benötigten Kartensatz
     * @param players - Liste der Spieler, die am Spiel teilnehmen
     * @return neues Spiel
     */
    Spiel spielAnlegen(List<Spieler> players) throws IllegelStapelGroesseException;

    /**
     * legt den Spieler für die nächste Runde fest und merkt sich, ob die Runde im oder gegen den Uhrzeigersinn gespielt wird.
     * @param spiel
     */
    void naechsterSpielerWechseln(Spiel spiel);

    /**
     * Zu Beginn des Spiels erhält jeder Spieler seine Handkarten.
     * @param spiel
     */
    void handkartenHandeln(Spiel spiel);

    /**
     * Es werden so viele Karten vom Ziehstapel gezogen, wie angegeben sind, und dann werden die Karten an den Spieler ausgeteilt
     * @param anzahlZuziehendeKarten - Anzahl der Karten, die der Spieler ziehen muss
     * @param spiel
     */
    void mussKartenZiehen(int anzahlZuziehendeKarten, Spiel spiel);

    /**
     * Prüft, ob der Spieler in dieser Runde eine Karte spielen darf. Er darf nicht, wenn er Karten ziehen muss
     * (z.B. weil eine Sieben oben liegt und der Spieler keine Sieben auf der Hand hat)
     * @param spiel
     * @return true, wenn der Spieler eine Karte ausspielen kann, false, wenn er stattdessen Karten ziehen muss
     */
    boolean darfKartenZiehen(Spiel spiel);

    /**
     * prüft, ob die gespielte Karte einer Kartenregel entspricht
     * zu der Kartenregel gehören, werden weitere Methoden ausgeführt
     * @param spiel
     */
    void regelnAnwenden(Spiel spiel);

    /**
     * prüft die zu spielende Karte
     * wenn die Karte gültig ist, wird sie dem Ablagestapel hinzugefügt
     * wenn ein Farbwunsch gespielt wurde und gültig ist, wird der Farbwunsch aus dem Spiel entfernt
     * @param karte - Karte, die gespielt werden soll
     * @param spiel
     * @throws AbgelegteKarteIstUngueltig, wenn die gespielte Karte für diese Runde nicht gültig ist
     */
    void ueberpruefenKarte (Karte karte, Spiel spiel) throws AbgelegteKarteIstUngueltig;

    /**
     * Setzt einen Anzugswunsch eines Spielers ins Spiel und setzt den Status askForSuitWish auf false
     * @param wunschFarbe - Anzugswunsch des Spielers
     * @param spiel
     */
    void wunschFarbeSetzen(Farbe wunschFarbe, Spiel spiel);

    /**
     * ruft das Spiel als gewonnen auf, wenn der aktive Spieler keine Handkarten mehr hat
     * @param game
     * @return true, wenn der aktive Spieler keine Handkarten mehr hat, false wenn nicht
     */
    boolean istSpielAngeschlossen(Spiel game);

    /**
     * Setzt den 'mau'-Status des aktiven Spielers auf false
     * @param spiel
     */
    void mauGesagt(Spiel spiel);

    /**
     * speichert ein Spiel in einer Datenbank
     * @param spiel Spiel, das gespeichert werden soll
     * @throws DaoException
     */

    void saveSpiel(Spiel spiel) throws DaoException;

    /**
     * Löscht ein Spiel aus der Datenbank
     * @param spiel Spiel, das gelöscht werden soll
     * @throws DaoException
     */
    void deleteSpiel(Spiel spiel) throws DaoException;

    /**
     * gibt an, ob mindestens ein Spiel in der Datenbank gespeichert ist
     * @return true wenn mindestens ein Spiel in der Datenbank gespeichert ist, false wenn nicht
     * @throws DaoException
     */
    boolean spielHaben() throws DaoException;

    /**
     * gibt das Spiel zurück, das zu der id gehört
     * @param id id, die zu dem Spiel gehört
     * @return gespeichertes Spiel
     * @throws DaoException
     */
    Spiel getSavedGame(long id) throws DaoException;
}
