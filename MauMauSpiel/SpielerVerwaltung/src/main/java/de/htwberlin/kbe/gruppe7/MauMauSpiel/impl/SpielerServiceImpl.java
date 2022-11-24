package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.export.SpielerService;

public class SpielerServiceImpl implements SpielerService {
	
	private List<Spieler> spielers = new ArrayList<>();
	
	private List<Karte> spielerStapel = new ArrayList<>();

	@Override
	public Spieler anlegenSpieler(String name) {
		Spieler spieler = new Spieler(name);
		spieler.setSpielerStapel(spielerStapel);
		spielers.add(spieler);
		return spieler;
	}

	@Override
	public Spieler ziehenKarte(Spieler spieler, Karte karte) {
		List<Karte> spielerStapel = new ArrayList<>();
		spielerStapel = spieler.getSpielerStapel();
		spielerStapel.add(karte);
		spieler.setSpielerStapel(spielerStapel);
		return spieler;
	}

	@Override
	public Spieler entfernenKarte(Spieler spieler, Karte karte) throws KarteNichtGefundenException {
		spielerStapel = spieler.getSpielerStapel();
		if (spielerStapel.contains(karte)) {
			spielerStapel.remove(karte);
			spieler.setSpielerStapel(spielerStapel);
		} else
			throw new KarteNichtGefundenException();
		return spieler;
	}

	@Override
	public Spieler ablegenKarte(Spieler spieler, Karte karte) throws KarteNichtGefundenException {
		spielerStapel = spieler.getSpielerStapel();
		if (spielerStapel.contains(karte)) {
			return spieler;
		} else {
			throw new KarteNichtGefundenException();
		}
	}

}
