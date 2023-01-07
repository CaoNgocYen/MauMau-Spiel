package de.htwberlin.kbe.gruppe7.regelnverwaltung.impl;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import java.util.Objects;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.regelnverwaltung.export.SondernRegelnService;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

public class SondernRegelnServiceImpl implements SondernRegelnService {

	@Override
	public void ueberpruefenKarte(Karte abgelegteKarte , Karte obersteneKarte, Farbe Wunschfarbe, int anzahlZuziehendKarten) throws KarteNichtGefundenException {
		Farbe abgelegteFarbe = abgelegteKarte.getFarbe();
		Wert abgelegtKarteWert = abgelegteKarte.getWert();
		Wert oberstenKarteWert = obersteneKarte.getWert();
		if (mussKarteZiehen(abgelegtKarteWert, oberstenKarteWert)) {
			throw new KarteNichtGefundenException();
		}

		if (!wertOderFarbePasst(abgelegteKarte, obersteneKarte) && !abgelegtKarteWert.equals(Wert.BUBE) && Objects.isNull(Wunschfarbe)) {
			throw new KarteNichtGefundenException();
		}

		if (istBudeUeberBude(abgelegtKarteWert, oberstenKarteWert)) {
			throw new KarteNichtGefundenException();
		}

		if (Wunschfarbe != null && !istWunschfarbePasst(Wunschfarbe, abgelegteFarbe)) {
			throw new KarteNichtGefundenException();
		}

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
	public Farbe sichFarbeWuenschen(Karte abgelegteKarte, int farbe) {
		if (abgelegteKarte.getWert().equals(Wert.BUBE)) {
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
	public boolean mussKarteZiehen(Wert abgelegtKarteWert, Wert oberstenKarteWert) {
		return !abgelegtKarteWert.equals(Wert.SIEBEN) && oberstenKarteWert.equals(Wert.SIEBEN);
	}

	@Override
	public boolean istBudeUeberBude(Wert abgelegteFarbe, Wert obersteneFarbe) {
		return abgelegteFarbe.equals(obersteneFarbe) && obersteneFarbe.equals(Wert.BUBE);
	}

	@Override
	public boolean istWunschfarbePasst(Farbe abgelegteFarbe, Farbe wunschFarbe) {
		return Objects.nonNull(wunschFarbe) && abgelegteFarbe.equals(wunschFarbe);
	}

	@Override
	public boolean wertOderFarbePasst(Karte abgelegteKarte, Karte obersteneKarte) {
		return abgelegteKarte.getWert().equals(obersteneKarte.getWert()) || abgelegteKarte.getFarbe().equals(obersteneKarte.getFarbe());
	}

	@Override
	public int zweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int zweiKartenZiehen(Karte gelegteKarte, Spieler spieler) {
		// TODO Auto-generated method stub
		return 0;
	}

}
