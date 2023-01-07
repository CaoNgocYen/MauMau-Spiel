package de.htwberlin.kbe.gruppe7.spielerverwaltung.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwberlin.kbe.gruppe7.kartenverwaltung.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.kartenverwaltung.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.export.SpielerService;

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
	public Spieler handKarteHinzufuegen(Spieler spieler, Karte hinzufuegendekarte) {
		List<Karte> spielerStapel = new ArrayList<>();
		spielerStapel = spieler.getSpielerStapel();
		spielerStapel.add(hinzufuegendekarte);
		spieler.setSpielerStapel(spielerStapel);
		return spieler;
	}

	@Override
	public Spieler kartenAusZiehstapelZiehen(Spieler spieler, Karte ziehendekarte) {
		List<Karte> spielerStapel = new ArrayList<>();
		spielerStapel = spieler.getSpielerStapel();
		spielerStapel.add(ziehendekarte);
		spieler.setSpielerStapel(spielerStapel);
		return spieler;
	}


	@Override
	public Spieler handKarteEntfernen(Spieler spieler, Karte entfernendekarte) throws KarteNichtGefundenException {
		spielerStapel = spieler.getSpielerStapel();
		if (spielerStapel.contains(entfernendekarte)) {
			spielerStapel.remove(entfernendekarte);
			spieler.setSpielerStapel(spielerStapel);
		} else
			throw new KarteNichtGefundenException();
		return spieler;
	}

	@Override
	public Spieler handKartenAblegen(Spieler spieler, Karte ablegendekarte) throws KarteNichtGefundenException {
		spielerStapel = spieler.getSpielerStapel();
		if (spielerStapel.contains(ablegendekarte)) {
			return spieler;
		} else {
			throw new KarteNichtGefundenException();
		}
	}

}

