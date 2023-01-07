package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;

public class SpielerServiceImpl implements SpielerService {

	private List<Spieler> spielers = new ArrayList<>();

	private List<Karte> spielerStapel = new ArrayList<>();

	private static Logger log = Logger.getRootLogger();

	@Override
	public Spieler anlegenSpieler(String name) {
		Spieler spieler = new Spieler(name);
		spieler.setSpielerStapel(spielerStapel);
		spielers.add(spieler);
		log.debug("Der Spieler " + name + "wird erzeugt.");
		return spieler;
	}

	@Override
	public Spieler handKarteHinzufuegen(Spieler spieler, Karte hinzufuegendekarte) {
		List<Karte> spielerStapel = new ArrayList<>();
		spielerStapel = spieler.getSpielerStapel();
		spielerStapel.add(hinzufuegendekarte);
		spieler.setSpielerStapel(spielerStapel);
		log.debug("Der Spieler " + spieler.getName() + " fügt die Karte " + hinzufuegendekarte.getFarbe() + " " + hinzufuegendekarte.getWert() + " hinzu.");
		return spieler;
	}
	@Override
	public Spieler kartenAusZiehstapelZiehen(Spieler spieler, Karte ziehendekarte) {
		List<Karte> spielerStapel = new ArrayList<>();
		spielerStapel = spieler.getSpielerStapel();
		spielerStapel.add(ziehendekarte);
		spieler.setSpielerStapel(spielerStapel);
		log.debug("Der Spieler " + spieler.getName() + " zieht die Karte " + ziehendekarte.getFarbe() + " " + ziehendekarte.getWert() + " aus dem Ziehstapel.");
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
		log.debug("Der Spieler " + spieler.getName() + " legt die Karte " + ablegendekarte.getFarbe() + " " + ablegendekarte.getWert() + " ab.");
		return spieler;
	}

	@Override
	public Spieler handKartenAblegen(Spieler spieler, Karte ablegendekarte) throws KarteNichtGefundenException {
		spielerStapel = spieler.getSpielerStapel();
		if (spielerStapel.contains(ablegendekarte)) {
			log.debug("Der Spieler " + spieler.getName() + " legt die Karte " + ablegendekarte.getFarbe() + " " + ablegendekarte.getWert() + " ab.");
			return spieler;
		} else {
			throw new KarteNichtGefundenException();
		}
	}

	@Override
	public Spieler getNaechsterSpieler(List<Spieler> spielerList, Spieler aktiverSpieler) {
		int spielerIndex = spielerList.indexOf(aktiverSpieler)+1;
		if(spielerIndex > spielerList.size()-1){
			spielerIndex = 0;
		}
		log.debug("Der " + spielerIndex + ".Spieler " + "ist nächste");
		return spielerList.get(spielerIndex);
	}

}

