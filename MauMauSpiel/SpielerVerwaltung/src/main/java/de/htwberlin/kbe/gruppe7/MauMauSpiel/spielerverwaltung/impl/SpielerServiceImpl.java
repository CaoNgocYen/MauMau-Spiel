package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpielerServiceImpl implements SpielerService {

	private List<Spieler> spielers = new ArrayList<>();

	private List<Karte> spielerStapel = new ArrayList<>();

	private static Logger logger = LogManager.getLogger(SpielerServiceImpl.class);


	@Override
	public Spieler anlegenSpieler(String name) {
		Spieler spieler = new Spieler(name);
		spieler.setSpielerStapel(spielerStapel);
		spielers.add(spieler);
		logger.debug("Der Spieler " + name + "wird erzeugt.");
		return spieler;
	}

	@Override
	public void handKarteHinzufuegen(Spieler spieler, List<Karte> hinzufuegendeKarten) {
		spieler.getSpielerStapel().addAll(hinzufuegendeKarten);
		//Collections.sort(spieler.getSpielerStapel());
		//List<Karte> spielerStapel = new ArrayList<>();
		logger.info("{} wird dem Deck des Spielers hinzugefügt {}", hinzufuegendeKarten, spieler.getName());
	}

//	@Override
//	public Spieler kartenAusZiehstapelZiehen(Spieler spieler, Karte ziehendekarte) {
//		List<Karte> spielerStapel = new ArrayList<>();
//		spielerStapel = spieler.getSpielerStapel();
//		spielerStapel.add(ziehendekarte);
//		spieler.setSpielerStapel(spielerStapel);
//		log.debug("Der Spieler " + spieler.getName() + " zieht die Karte " + ziehendekarte.getFarbe() + " " + ziehendekarte.getWert() + " aus dem Ziehstapel.");
//		return spieler;
//	}

	@Override
	public void handKarteEntfernen(Spieler spieler, Karte entfernendeKarte){
		spieler.getSpielerStapel().remove(entfernendeKarte);
		logger.info("{} wird aus dem Deck des Spielers hinzugefügt {}", entfernendeKarte, spieler.getName());
//		spielerStapel = spieler.getSpielerStapel();
//		if (spielerStapel.contains(entfernendeKarte)) {
//			spielerStapel.remove(entfernendeKarte);
//			spieler.setSpielerStapel(spielerStapel);
//		}
	}


//	@Override
//	public Spieler handKartenAblegen(Spieler spieler, Karte ablegendekarte) throws KarteNichtGefundenException {
//		spielerStapel = spieler.getSpielerStapel();
//		if (spielerStapel.contains(ablegendekarte)) {
//			log.debug("Der Spieler " + spieler.getName() + " legt die Karte " + ablegendekarte.getFarbe() + " " + ablegendekarte.getWert() + " ab.");
//			return spieler;
//		} else {
//			throw new KarteNichtGefundenException();
//		}
//	}

	@Override
	public Spieler getNaechsterSpieler(List<Spieler> spielerList, Spieler aktiverSpieler) {
		int spielerIndex = spielerList.indexOf(aktiverSpieler)+1;
		if(spielerIndex > spielerList.size()-1){
			spielerIndex = 0;
		}
		logger.debug("Der " + spielerIndex + ".Spieler " + "ist nächste");
		return spielerList.get(spielerIndex);
	}

}

