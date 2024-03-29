package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.exception.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import java.util.List;

public interface SpielerService {
	/**
	 * Ein Spieler wird mit Name erzeugt
	 * @param name der Name der Spieler
	 * @return angelegter Spieler
	 */
	Spieler anlegenSpieler(String name);
	/**
	 * Prueft, ob der aktiver Spieler soll die Karten hinzufügen
	 * @param spieler der Spieler ist dran
	 * @param hinzufuegendeKarten die Karten werden hizugefügt
	 */
	void handKarteHinzufuegen(Spieler spieler, List<Karte> hinzufuegendeKarten);
//	/**
//	 * Prueft, ob der aktiver Spieler soll eine Karte hinzufügen
//	 * @param spieler der Spieler ist dran
//	 * @param ziehendekarte die Karte wird aus dem Ziehstapel gezogen
//	 * @return der aktiver Spieler
//	 */
//	Spieler kartenAusZiehstapelZiehen(Spieler spieler, Karte ziehendekarte);
	/**
	 * Prueft, ob der aktiver Spieler soll eine Karte entfernen
	 * @param spieler der Spieler ist dran
	 * @param entfernendekarte die Karte wird entfernt
	 */
	void handKarteEntfernen(Spieler spieler, Karte entfernendekarte);
//	/**
//	 * Prueft, ob der Spieler Mau gesagt hat
//	 * @param spieler der Spieler ist dran
//	 * @param ablegendekarte die Karte wird aus dem Handstapel abgelegt
//	 * @return der aktiver Spieler
//	 */
//	Spieler handKartenAblegen(Spieler spieler, Karte ablegendekarte) throws KarteNichtGefundenException;
	/**
	 * Prueft, ob welcher Spieler ist nächst
	 * @param spielerList die Liste von Spielern
	 * @param aktiverSpieler der Spieler ist dran
	 * @return index von nächsten Spieler
	 */
	Spieler getNaechsterSpieler(List<Spieler> spielerList, Spieler aktiverSpieler);

}

