package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Spieler {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Karte> spielerStapel;

	//private boolean mauGesagt = false;
	@Column(nullable = false)
	private boolean mauGesagt;
	@Column(nullable = false)
	private boolean mussAussetzen;
	@Column(nullable = false)
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


