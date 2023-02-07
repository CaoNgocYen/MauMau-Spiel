package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import java.util.Objects;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegelnServiceImpl implements RegelnService {

	private static Logger logger = LogManager.getLogger(RegelnServiceImpl.class);
	@Override
	public void ueberpruefenKarte(Karte abgelegteKarte , Karte obersteKarte, Farbe Wunschfarbe, int anzahlZuziehendeKarten) throws AbgelegteKarteIstUngueltig {
		Farbe abgelegteFarbe = abgelegteKarte.getFarbe();
		Wert abgelegtKarteWert = abgelegteKarte.getWert();
		Wert obersteKarteWert = obersteKarte.getWert();
		if (mussSiebenSpielen(anzahlZuziehendeKarten, abgelegtKarteWert, obersteKarteWert)) {
			logger.info("Karte {} ist nicht gültig, da eine SIEBEN gespielt werden muss", abgelegteKarte);
			throw new AbgelegteKarteIstUngueltig("Du muss ein SIEBEN spielen.");
		}

		if (!wertOderFarbePasst(abgelegteKarte, obersteKarte) && !abgelegtKarteWert.equals(Wert.BUBE) && Objects.isNull(Wunschfarbe)) {
			logger.info("Karte {} ist ungültig, da Wert oder Farbe nicht übereinstimmen", abgelegteKarte);
			throw new AbgelegteKarteIstUngueltig("Die Karte kann nicht gespielt werden. Wert oder Farbe stimmen nicht überein.");
		}

		if (istBubeAufBube(abgelegtKarteWert, obersteKarteWert)) {
			logger.info("Karte {} ist nicht gültig, weil Bube auf Bube nicht erlaubt ist", abgelegteKarte);
			throw new AbgelegteKarteIstUngueltig("Eine BUBE auf BUBE ist nicht erlaubt.");
		}

		if (Wunschfarbe != null && !istWunschfarbePasst(Wunschfarbe, abgelegteFarbe)) {
			logger.info("Karte {} ist nicht gültig, weil die Farbe nicht dem Wunsch des Spielers entspricht", abgelegteKarte);
			throw new AbgelegteKarteIstUngueltig("Die Karte kann nicht gespielt werden. Die Farbe entspricht nicht dem Wunsch des Spielers.");
		}
	}
	@Override
	public boolean spielerAussetzen(Karte obersteKarte) {
		return obersteKarte.getFarbe().equals(Farbe.PIK);
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
	public boolean mussKarteZiehen(Spieler spieler, Karte obersteKarte, int anzahlZuziehendeKarten) {
		boolean hatSieben = spieler.getSpielerStapel().stream().filter(k -> k.getWert().equals(Wert.SIEBEN)).findFirst().isPresent();
		boolean istObersteKarteSieben = mussKarteZiehen(obersteKarte);
		boolean istAnzahlGroesserAlsZwei = anzahlZuziehendeKarten >= 2;
		return !hatSieben && istObersteKarteSieben && istAnzahlGroesserAlsZwei;
	}

	@Override
	public boolean mussSiebenSpielen (int anzahlZuziehenKarten, Wert abgelegteWert, Wert obersteneWert) {
		return anzahlZuziehenKarten >= 2 && obersteneWert.equals(Wert.SIEBEN) && !abgelegteWert.equals(Wert.SIEBEN);
	}

	@Override
	public boolean istBudeKarte(Karte obersteKarte) {
		return obersteKarte.getWert().equals(Wert.BUBE);
	}

	@Override
	public boolean istBubeAufBube(Wert abgelegteFarbe, Wert obersteneFarbe) {
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

//	@Override
//	public boolean prüfeZweiKartenZiehen(Karte gelegteKarte) {
//		return gelegteKarte.getWert().equals(Wert.SIEBEN);
//	}
//
//	@Override
//	public boolean prüfeGelegteKarteGueltig(Karte gelegteKarte, Karte letzteStapelKarte, Farbe spielFarbe) {
//		if((gelegteKarte.getFarbe() == letzteStapelKarte.getFarbe()) || (gelegteKarte.getWert() == letzteStapelKarte.getWert()) || gelegteKarte.getFarbe().equals(spielFarbe) ){
//			return true;
//		}
//		else return false;
//	}

	@Override
	public boolean istMauUnGueltig(Spieler spieler) {
		if (spieler.getSpielerStapel().size() <= 1 && spieler.isMauGesagt()) {
			return false;
		}
		if (spieler.getSpielerStapel().size() > 1 && !spieler.isMauGesagt()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean mussKarteZiehen(Karte obersteKarte) {
		return obersteKarte.getWert().equals(Wert.SIEBEN);
	}

}
