package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import java.util.List;

public class Spieler {

	private String name;

	private int punkte;

	private List<Karte> spielerStapel;

	private boolean gesagtMau = false;

	private boolean gesagtMauMau = false;

	private boolean virtuellerSpieler = false;

	public Spieler(String name) {
		super();
		this.name = name;
	}

	public  Spieler () {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public List<Karte> getSpielerStapel() {
		return spielerStapel;
	}

	public void setSpielerStapel(List<Karte> spielerStapel) {
		this.spielerStapel = spielerStapel;
	}

	public boolean isGesagtMau() {
		return gesagtMau;
	}

	public void setGesagtMau(boolean gesagtMau) {
		this.gesagtMau = gesagtMau;
	}

	public boolean isGesagtMauMau() {
		return gesagtMauMau;
	}

	public void setGesagtMauMau(boolean gesagtMauMau) {
		this.gesagtMauMau = gesagtMauMau;
	}

	public boolean isVirtuellerSpieler() {
		return virtuellerSpieler;
	}
	public void setVirtuellerSpieler(boolean virtuellerSpieler) {
		this.virtuellerSpieler = virtuellerSpieler;
	}
}

