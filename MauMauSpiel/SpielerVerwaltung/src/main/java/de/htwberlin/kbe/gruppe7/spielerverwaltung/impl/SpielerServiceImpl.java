package de.htwberlin.kbe.gruppe7.spielerverwaltung.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
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

	@Override
	public Spieler getNextSpieler(List<Spieler> spielerList, Spieler aktiverSpieler) {
		int spielerIndex = spielerList.indexOf(aktiverSpieler)+1;
		if(spielerIndex > spielerList.size()-1){
			spielerIndex = 0;
		}
		return spielerList.get(spielerIndex);
	}

}
