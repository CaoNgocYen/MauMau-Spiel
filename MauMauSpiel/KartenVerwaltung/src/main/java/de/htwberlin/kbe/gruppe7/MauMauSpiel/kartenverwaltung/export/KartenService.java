package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export;

import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

public interface KartenService {


	Karte anlegenKarte(Wert wert, Farbe farbe);


	/**
	 * Kartenstapel wird erzeugt.
	 * @return kartenStapel angelegter Kartenstapel
	 *
	 */
	List<Karte> anlegenStapel();


	/**
	 * Fügt eine neue Karte dem Kartenstapel hinzu und gibt dem neuen Stapel zurück.
	 * @param kartenStapel List an Kartenobjekten
	 * @param anzahlVonKarten von hinzugefuegten Karten
	 * @return neuer Kartenstapel
	 */
	List<Karte> hinzufuegfenKarte(List<Karte> kartenStapel, int anzahlVonKarten);


	/**
	 * Entfernt eine Karte aus dem Stapel und gibt den neuen Stapel zurück.
	 * @param kartenStapel Kartenstapel, dem die Karte zuentfernen werden soll
	 * @param zuEntfernenKarten Karte, die entfernt werden soll
	 * KarteNichtGefundenException throw exception wenn die Karte kann nicht abgelegt wird
	 * @return neuer Kartenstapel
	 */
	List<Karte> entfernenKarten(List<Karte> kartenStapel, Karte zuEntfernenKarten) throws KarteNichtGefundenException;

	/**
	 * Die Reihenfolge der Objekte in der Liste kartenStapel wird zufaellig festgelegt und zurueckgegeben.
	 * Es koennen somit jeweils Stapel zu Beginn des Spiels gemischt werden sowie waehrend des Spiels.
	 * Die oberste Karte verbleibt oben.
	 * @param kartenStapel List an Kartenobjekten
	 * @return kartenStapel Gemischter Kartenstapel
	 */
	List<Karte> mischenKarten(List<Karte> kartenStapel);

}
