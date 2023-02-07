package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

public class Karte {

	private Long id;

	private Wert wert;

	private Farbe farbe;

	public Karte() {

	}

	/** Erstellt eine Karte mit Farbe und Wert.
	 * @param farbe Farbe der Karte
	 * @param wert Wert der Karte
	 */
	public Karte(Wert wert, Farbe farbe) {
		this.wert = wert;
		this.farbe = farbe;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setWert(Wert wert) {
		this.wert = wert;
	}

	public void setFarbe(Farbe farbe) {
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
