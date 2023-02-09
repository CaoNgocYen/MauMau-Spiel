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

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpielerVerwaltungTest {

    SpielerService spielerService = new SpielerServiceImpl();
    private Spieler spieler;
    private List<Spieler> spielerList = new ArrayList<>();
    List<Karte> handKarten = new ArrayList<>();
    private final Karte pik8 = new Karte(Wert.ACHT, Farbe.PIK);
    private final Karte karoKoenig = new Karte(Wert.KOENIG, Farbe.KARO);
    private final Karte herz9 = new Karte(Wert.NEUN, Farbe.HERZ);

    @BeforeAll
    public static void initialize(){}

    @BeforeEach
    public void setUp(){
        spieler = new Spieler();
        handKarten.add(pik8);
        handKarten.add(karoKoenig);
        handKarten.add(herz9);
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
        spielerService.handKarteHinzufuegen(spieler, Arrays.asList(pik8, herz9));
        Assertions.assertEquals(5, spieler.getSpielerStapel().size());
    }

    @Test
    public void testEntfernenKarte() {
        spielerService.handKarteEntfernen(spieler, karoKoenig);
        handKarten.remove(karoKoenig);
        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());
    }

    @Test
    @DisplayName("...")
    void getNaechsterSpielerTest() {
        spielerList.add(spielerService.anlegenSpieler("Joe"));
        spielerList.add(spielerService.anlegenSpieler("Mary"));
        Spieler naechsterSpieler = spielerService.getNaechsterSpieler(spielerList, spielerList.get(0));
        Assertions.assertNotNull(naechsterSpieler);
        Assertions.assertEquals("Mary", naechsterSpieler.getName());
    }

    @Test
    @DisplayName("Test case for removing card from player's deck failure")
    void testRemoveCardFailure() {
        SpielerService spielerService = new SpielerServiceImpl();
        Spieler spielerJoe = spielerService.anlegenSpieler("Joe");

        List<Karte> zuziehendeKarten = Arrays.asList(pik8, herz9);
        spielerService.handKarteHinzufuegen(spielerJoe, zuziehendeKarten);

        Karte zuentferndeKarte = karoKoenig;

        Assertions.assertFalse(spielerJoe.getSpielerStapel().contains(zuentferndeKarte));
        spielerService.handKarteEntfernen(spielerJoe, zuentferndeKarte);
        Assertions.assertFalse(spielerJoe.getSpielerStapel().contains(zuentferndeKarte));
    }
//
//    @Test
//    public void testZiehenKarte(){
//        spielerService.handKarteHinzufuegen(spieler, karte1);
//        handKarten.add(karte1);
//        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());
//
//    }
//

}
