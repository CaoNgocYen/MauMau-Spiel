package de.htwberlin.kbe.gruppe7.spielerverwaltung.impl;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.*;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.export.SpielerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;


public class SpielerVerwaltungTest {

    SpielerService spielerService = new SpielerServiceImpl();
    private Spieler spieler;
    List<Karte>handKarten = new ArrayList<>();
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
    public void testZiehenKarte(){
        spielerService.ziehenKarte(spieler, karte1);
        handKarten.add(karte1);
        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());

    }

    @Test
    public void testEntfernenKarte() throws KarteNichtGefundenException {
        spielerService.entfernenKarte(spieler, karte2);
        handKarten.remove(karte2);
        Assertions.assertEquals(handKarten, spieler.getSpielerStapel());

    }

}
