package de.htwberlin.kbe.gruppe7.regelnverwaltung.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

public interface RegelnService {
	
    int zweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    int zweiKartenZiehen(Karte gelegteKarte, Spieler spieler);

    boolean spielerAussetzen(Karte gelegteKarte);
    
    boolean richtungWechsel(Karte gelegteKarte);
    
    Farbe sichFarbeWuenschen(Karte gelegteKarte, int farbe);

}
