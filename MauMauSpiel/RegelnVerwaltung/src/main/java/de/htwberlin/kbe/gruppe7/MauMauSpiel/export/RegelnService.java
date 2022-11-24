package de.htwberlin.kbe.gruppe7.MauMauSpiel.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Farbe;

public interface RegelnService {
	
    int zweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);
    
    boolean spielerAussetzen(Karte gelegteKarte);
    
    boolean richtungWechsel(Karte gelegteKarte);
    
    Farbe sichFarbeWuenschen(Karte gelegteKarte, int farbe);

}
