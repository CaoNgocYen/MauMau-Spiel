package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;


public class KartenServiceImpl implements KartenService {

	@Override
	public Karte anlegenKarte(Wert wert, Farbe farbe) {
		Karte karte = new Karte(wert, farbe);
		return karte;
	}

	@Override
	public List<Karte> anlegenStapel() {
		List<Karte> kartenStapel = new ArrayList();
		for (Wert wert : Wert.values()) {
			for (Farbe farbe : Farbe.values()) {
				kartenStapel.add(new Karte(wert, farbe));
			}
		}
		return kartenStapel;
	}

	@Override
	public List<Karte> hinzufuegfenKarte(List<Karte> kartenStapel, int anzahlVonKarten) {
		List<Karte> spielerStapel = new ArrayList();
		mischenKarten(kartenStapel);
		for (int i = 0; i < anzahlVonKarten; i++ ) {
			spielerStapel.add(kartenStapel.get(i));
		}
		return spielerStapel;
	}

	@Override
	public List<Karte> entfernenKarten(List<Karte> kartenStapel, Karte zuEntfernenKarten) throws KarteNichtGefundenException {
		if (kartenStapel.contains(zuEntfernenKarten)) {
			kartenStapel.remove(zuEntfernenKarten);
		} else {
			throw new KarteNichtGefundenException();
		}
		return kartenStapel;
	}

	@Override
	public List<Karte> mischenKarten(List<Karte> kartenStapel) {
		Collections.shuffle(kartenStapel);
		return kartenStapel;
	}

}
