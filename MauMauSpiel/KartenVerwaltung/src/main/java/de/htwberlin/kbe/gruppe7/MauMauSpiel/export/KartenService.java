package de.htwberlin.kbe.gruppe7.MauMauSpiel.export;

import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Wert;

public interface KartenService {
	
	Karte anlegenKarte(Wert wert, Farbe farbe);


	List<Karte> anlegenStapel();
	

	List<Karte> hinzufuegfenKarte(List<Karte> kartenStapel, int anzahlVonKarten);
	


	List<Karte> entfernenKarten(List<Karte> kartenStapel, Karte zuEntfernenKarten) throws KarteNichtGefundenException;


	
	List<Karte> mischenKarten(List<Karte> kartenStapel);

}
