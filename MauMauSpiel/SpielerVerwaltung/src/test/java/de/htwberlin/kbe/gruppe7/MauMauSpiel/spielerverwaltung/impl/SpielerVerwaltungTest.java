package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.impl;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SpielerVerwaltungTest {

    SpielerService spielerService = new SpielerServiceImpl();
    private Spieler spieler;
    List<Karte> handKarten = new ArrayList<>();
    private final Karte karte1 = new Karte(Wert.ACHT, Farbe.PIK);
    private final Karte karte2 = new Karte(Wert.KOENIG, Farbe.KARO);
    private final Karte karte3 = new Karte(Wert.NEUN, Farbe.HERZ);

    @BeforeAll
    public static void initialize(){}

    @BeforeEach
    public void setUp(){
        spieler = new Spieler();
        handKarten.add(karte1);
        handKarten.add(karte2);
        handKarten.add(karte3);
        spieler.setSpielerStapel(handKarten);
    }

    @Test
    public void testNeuerSpielerAnlegen (){
        Spieler spielerJoe = spielerService.anlegenSpieler("Joe");
        spieler.setName("Joe");
        Assertions.assertEquals(spieler.getName(), spielerJoe.getName());
    }

    @Test
    public void testhandKarteHinzufuegen(){
        spieler.setSpielerStapel(handKarten);
        spielerService.handKarteHinzufuegen(spieler, Arrays.asList(karte1, karte3));
        Assertions.assertEquals(5, spieler.getSpielerStapel().size());
    }

    @Test
    public void testEntfernenKarte() {
        spielerService.handKarteEntfernen(spieler, karte2);
        handKarten.remove(karte2);
        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());
    }

    @Test
    public void testGetNaechsterSpieler() {
        Spieler spieler1 = new Spieler("Player 1");
        Spieler spieler2 = new Spieler("Player 2");
        Spieler spieler3 = new Spieler("Player 3");

        List<Spieler> spielerList = new ArrayList<>();
        spielerList.add(spieler1);
        spielerList.add(spieler2);
        spielerList.add(spieler3);

        Spieler nextPlayer = spielerService.getNaechsterSpieler(spielerList, spieler1);
        Assertions.assertEquals(nextPlayer, spieler2, "The next player should be spieler2");

        nextPlayer = spielerService.getNaechsterSpieler(spielerList, spieler2);
        Assertions.assertEquals(nextPlayer, spieler3, "The next player should be spieler3");

        nextPlayer = spielerService.getNaechsterSpieler(spielerList, spieler3);
        Assertions.assertEquals(nextPlayer, spieler1, "The next player should be spieler1");
    }

//    @Test
//    public void testZiehenKarte(){
//        spielerService.handKarteHinzufuegen(spieler, (List<Karte>) karte1);
//        handKarten.add(karte1);
//        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());
//
//    }


}
