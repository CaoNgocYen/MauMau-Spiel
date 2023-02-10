package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl.DAO;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.export.Stapel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpielVorrichtung {

    public static List<Spieler> players() {
        List<Spieler> players = new ArrayList<>();
        players.add(new Spieler( "Hana"));
        players.add(new Spieler("Eric"));
        players.add(new Spieler("Koi"));
        players.add(new Spieler("Elex"));

        return players;
    }

    public static Stapel stapel() {
        Stapel stapel = new Stapel();
        List <Karte> karten = karten();
        Karte obersteKarte = karten.get(0);
        karten.remove(obersteKarte);
        stapel.setObersteKarte(obersteKarte);
        stapel.setZiehStapel(karten);
        return stapel;
    }

    public static Spiel spiel() {
        Spiel spiel = new Spiel(players(), stapel());
        spiel.setId(1L);
        return spiel;
    }

    public static List<Farbe> farben = Arrays.asList(
            Farbe.HERZ,
            Farbe.KARO,
            Farbe.KREUZ,
            Farbe.PIK
    );

    public static List<Wert> werten = Arrays.asList(
            Wert.SIEBEN,
            Wert.ACHT,
            Wert.NEUN,
            Wert.ZEHN,
            Wert.DAME,
            Wert.BUBE,
            Wert.ASS

    );

    public static List<Karte> karten() {
        List<Karte> karten = new ArrayList<>();
        for(Farbe farbe : farben) {
            for(Wert wert : werten) {
                karten.add(new Karte(wert, farbe));
            }
        }
        return karten;
    }
}
