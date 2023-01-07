package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

public class Karte {

	private Wert wert;

	private Farbe farbe;

	/** Erstellt eine Karte mit Farbe und Wert.
	 * @param farbe Farbe der Karte
	 * @param wert Wert der Karte
	 */
	public Karte(Wert value, Farbe farbe) {
		super();
		this.wert = value;
		this.farbe = farbe;
	}


	/** Gibt Wert der Karte zurueck.
	 * @return Wert der Karte, kann einer von acht folgenden Werten sein: {sieben, acht, neun, zehn, dame, bube, koenig, ass}
	 */
	public Wert getWert() {
		return wert;
	}


	/** Gibt Farbe der Karte zurueck.
	 * @return Farbe der Karte, kann einer von vier folgenden Werten sein: {Kreuz, Pik, Herz, Karo}
	 */
	public Farbe getFarbe() {
		return farbe;
	}

}
