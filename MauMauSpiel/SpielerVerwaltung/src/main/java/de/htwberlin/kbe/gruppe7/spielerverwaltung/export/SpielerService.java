package de.htwberlin.kbe.gruppe7.spielerverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

public interface SpielerService {

	Spieler anlegenSpieler(String name);

	Spieler handKarteHinzufuegen(Spieler spieler, Karte hinzufuegendekarte);

	Spieler kartenAusZiehstapelZiehen(Spieler spieler, Karte ziehendekarte);

	Spieler handKarteEntfernen(Spieler spieler, Karte entfernendekarte) throws KarteNichtGefundenException;

	Spieler handKartenAblegen(Spieler spieler, Karte ablegendekarte) throws KarteNichtGefundenException;

}

