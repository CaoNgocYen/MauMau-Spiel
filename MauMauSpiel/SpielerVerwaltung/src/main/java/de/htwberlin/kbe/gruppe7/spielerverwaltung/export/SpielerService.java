package de.htwberlin.kbe.gruppe7.spielerverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

public interface SpielerService {
	
	Spieler anlegenSpieler(String s);
	
	Spieler ziehenKarte(Spieler p, Karte karte);
	
	Spieler entfernenKarte(Spieler spieler, Karte karte) throws KarteNichtGefundenException;
	
	Spieler ablegenKarte(Spieler p, Karte karte) throws KarteNichtGefundenException;
	
}
