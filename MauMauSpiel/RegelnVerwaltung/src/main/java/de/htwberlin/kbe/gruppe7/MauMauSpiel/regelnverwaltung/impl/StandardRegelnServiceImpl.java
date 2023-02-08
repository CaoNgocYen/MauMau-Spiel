package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;

public class StandardRegelnServiceImpl implements RegelnService  {


    @Override
    public void ueberpruefenKarte(Karte abgelegteKarte, Karte obersteneKarte, Farbe FarbeWuenschen, int anzahlZuziehendKarten) throws AbgelegteKarteIstUngueltig {

    }

    @Override
    public boolean mussKarteZiehen(Spieler spieler, Karte obersteKarte, int anzahlZuziehenKarten) {
        return false;
    }

    @Override
    public boolean mussSiebenSpielen(int anzahlZuziehenKarten, Wert abgelegteWert, Wert obersteneWert) {
        return false;
    }

    @Override
    public boolean richtungWechsel(Karte abgelegteKarte) {
        return false;
    }

    @Override
    public boolean spielerAussetzen(Karte obersteKarte) {
        return false;
    }

    @Override
    public Farbe sichFarbeWuenschen(Karte abgelegteKarte, int farbe) {
        return null;
    }

    @Override
    public boolean istBudeKarte(Karte obersteKarte) {
        return false;
    }

    @Override
    public boolean istBubeAufBube(Wert abgelegteWert, Wert obersteneWert) {
        return false;
    }

    @Override
    public boolean istWunschfarbePasst(Farbe wunschFarbe, Farbe abgelegteFarbe) {
        return false;
    }

    @Override
    public boolean wertOderFarbePasst(Karte abgelegteKarte, Karte obersteneKarte) {
        return false;
    }

    @Override
    public boolean istMauUnGueltig(Spieler spieler) {
        return false;
    }

    @Override
    public boolean mussKarteZiehen(Karte obersteKarte) {
        return false;
    }
}
