package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

	private String name;

	private List<Karte> spielerStapel;

	//private boolean mauGesagt = false;
	private boolean mauGesagt;

	private boolean mussAussetzen;

	private boolean virtuellerSpieler = false;
	public  Spieler () {
	}

	public Spieler(String name) {
		this.name = name;
		this.spielerStapel = new ArrayList<>();
	}

	public Spieler(String name, boolean virtuellerSpieler) {
		this.name = name;
		this.virtuellerSpieler = virtuellerSpieler;
		this.spielerStapel = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Karte> getSpielerStapel() {
		return spielerStapel;
	}

	public void setSpielerStapel(List<Karte> spielerStapel) {
		this.spielerStapel = spielerStapel;
	}

	public boolean isMauGesagt() {
		return mauGesagt;
	}

	public void setMauGesagt(boolean mauGesagt) {
		this.mauGesagt = mauGesagt;
	}

	public boolean isMussAussetzen() {
		return mussAussetzen;
	}

	public void setMussAussetzen(boolean mussAussetzen) {
		this.mussAussetzen = mussAussetzen;
	}

	public boolean isVirtuellerSpieler() {
		return virtuellerSpieler;
	}
	public void setVirtuellerSpieler(boolean virtuellerSpieler) {
		this.virtuellerSpieler = virtuellerSpieler;
	}
}


