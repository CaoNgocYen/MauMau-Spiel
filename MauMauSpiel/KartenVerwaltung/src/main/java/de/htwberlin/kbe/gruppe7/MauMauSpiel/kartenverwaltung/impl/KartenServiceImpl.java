package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;


public class KartenServiceImpl implements KartenService {

	@Override
	public Karte anlegenKarte(Wert wert, Farbe farbe) {
		Karte karte = new Karte(wert, farbe);
		//log.debug("Karte: " + farbe + " " + wert + "wird erzeugt.");
		return karte;
	}

	@Override
	public List<Karte> anlegenStapel(int anzahlKarten) {
		List<Karte> kartenStapel = new ArrayList();
		for (Wert wert : Wert.values()) {
			for (Farbe farbe : Farbe.values()) {
				kartenStapel.add(new Karte(wert, farbe));
			}
		}
		//log.debug("Ein Stapel mit " + anzahlKarten + " Karten wird erzeugt.");
		return kartenStapel;
	}



	@Override
	public List<Karte> hinzufuegfenKarte(List<Karte> kartenStapel, int anzahlKarten) {
		List<Karte> spielerStapel = new ArrayList();
		mischenKarten(kartenStapel);
		for (int i = 0; i < anzahlKarten; i++ ) {
			spielerStapel.add(kartenStapel.get(i));
		}
		//log.debug("Der Spieler erhalt " + anzahlKarten + " Karten.");
		return spielerStapel;
	}

	@Override
	public List<Karte> entfernenKarten(List<Karte> kartenStapel, Karte zuEntfernenKarten){
		if (kartenStapel.contains(zuEntfernenKarten)) {
			kartenStapel.remove(zuEntfernenKarten);
		}
		//log.debug("Karte " + zuEntfernenKarten.getFarbe() + " " + zuEntfernenKarten.getWert() + " wird vom Stapel entfernt.");
		return kartenStapel;
	}

	@Override
	public List<Karte> mischenKarten(List<Karte> kartenStapel) {
		Collections.shuffle(kartenStapel);
		//log.debug("Die Karten werden gemischt.");
		return kartenStapel;
	}


	@Override
	public List<Farbe> getFarben() {
		return Arrays.asList(Farbe.values());
	}

	@Override
	public List<Wert> getWerten() {
		return Arrays.asList(Wert.values());
	}
}
