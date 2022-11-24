package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.entity.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.export.RegelnService;

public class RegelnServiceImpl implements RegelnService {
	
	private Wert kartenwert;

	@Override
	public int zweiKartenZiehen(Karte gelegteKarte, Spieler spieler) {
		return null;
	}
	
	@Override
	public boolean spielerAussetzen(Karte gelegteKarte) {
		if (gelegteKarte.getWert().equals(Wert.ACHT)) {
			return true;
		} else {
			return false;
		}
    }

	@Override
	public boolean richtungWechsel(Karte gelegteKarte) {
		if (gelegteKarte.getWert().equals(Wert.NEUN)) {
			return true;
		} else {
			return false;
		}
    }
	
	@Override
	public Farbe sichFarbeWuenschen(Karte gelegteKarte, int farbe) {
		if (gelegteKarte.getWert().equals(Wert.BUBE)) {
			switch (farbe) {
			case 1:
				return Farbe.HERZ;
			case 2:
				return Farbe.KARO;
			case 3:
				return Farbe.PIK;
			case 4:
				return Farbe.KREUZ;
			}
		} else {
			return null;
		}
		return null;
	}

}
