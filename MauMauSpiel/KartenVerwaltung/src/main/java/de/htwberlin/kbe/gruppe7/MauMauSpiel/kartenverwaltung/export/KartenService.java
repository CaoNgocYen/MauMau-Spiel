package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export;

import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;


public interface KartenService {
	



	Karte anlegenKarte(Wert wert, Farbe farbe);

	List<Karte> anlegenStapel(int anzahlKarten);
	

	List<Karte> hinzufuegfenKarte(List<Karte> kartenStapel, int anzahlVonKarten);
	


	List<Karte> entfernenKarten(List<Karte> kartenStapel, Karte zuEntfernenKarten) throws KarteNichtGefundenException;


	
	List<Karte> mischenKarten(List<Karte> kartenStapel);

}
