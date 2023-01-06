package de.htwberlin.kbe.gruppe7.regelnverwaltung.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.regelnverwaltung.export.RegelnService;

public class RegelnServiceImpl implements RegelnService {
	
	private Wert kartenwert;

	@Override
	public int zweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {
		return 0;
	}

	@Override
	public int zweiKartenZiehen(Karte gelegteKarte, Spieler spieler) {
		return 0;
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

	@Override
	public boolean prüfeZweiKartenZiehen(Karte gelegteKarte) {
		return gelegteKarte.getWert().equals(Wert.SIEBEN);
	}

	@Override
	public boolean prüfeGelegteKarteGueltig(Karte gelegteKarte, Karte letzteStapelKarte, Farbe spielFarbe) {
		if((gelegteKarte.getFarbe() == letzteStapelKarte.getFarbe()) || (gelegteKarte.getWert() == letzteStapelKarte.getWert()) || gelegteKarte.getFarbe().equals(spielFarbe) ){
			return true;
		}
		else return false;
	}

}
