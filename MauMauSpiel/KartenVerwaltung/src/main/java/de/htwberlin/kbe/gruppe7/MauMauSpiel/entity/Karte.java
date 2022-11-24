package de.htwberlin.kbe.gruppe7.MauMauSpiel.entity;

public class Karte {
	
	private Wert wert;
	
	private Farbe farbe;
	
	public Karte(Wert value, Farbe farbe) {
		super();
		this.wert = value;
		this.farbe = farbe;
	}

	public Wert getWert() {
		return wert;
	}

	public Farbe getFarbe() {
		return farbe;
	}

}
