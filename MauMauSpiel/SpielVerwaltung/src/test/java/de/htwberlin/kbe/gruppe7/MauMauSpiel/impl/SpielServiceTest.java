package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.impl.SpielServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

//@RunWith(MockitoJUnitRunner.class)
public class SpielServiceTest {
    @InjectMocks
    private SpielServiceImpl spielService;
    @Mock
    private SpielerService spielerService;
    @Mock
    private RegelnService regeLnService;

    //    @Mock
//    private SondernRegelnService sondernRegelnService;
    @Mock
    private KartenService kartenService;


    private Karte pik8;
    private Karte pik9;
    private Karte herz7;
    private Karte pikKoenig;
    private List<Karte> ziehstapel;
    private Spieler alex;
    private Spieler max;
    private List<Spieler> spielerList;
    private Spiel spiel= new Spiel();

    @BeforeEach
    public void initialize (){
        pik8= new Karte(Wert.ACHT, Farbe.PIK);
        pik9 = new Karte(Wert.NEUN, Farbe.PIK);
        herz7 = new Karte(Wert.SIEBEN,Farbe.HERZ);
        pikKoenig = new Karte(Wert.KOENIG, Farbe.PIK);
        ziehstapel = new ArrayList<>();
        alex = new Spieler();
        max = new Spieler();
        spiel.setSpielfarbe(Farbe.PIK);
        spielerList.add(alex);
        spielerList.add(max);
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        ziehstapel.add(herz7);


        spiel.setAblegestapel(ziehstapel);
        spiel.setZiehstapel(ziehstapel);
        spiel.setAktiverSpieler(alex);
        spiel.setSpielrichtungRechts(true);
        spiel.setSpielfarbe(Farbe.HERZ);
        spiel.setSpielerListe(spielerList);

    }


//    @Test
//    public void testPruefeAufMau (){
//        alex.getSpielerStapel().add(herz7);
//        Assertions.assertEquals(1, alex.getSpielerStapel().size());
//
//    }

//    @Test
//    public void testKarteLegen (){
//        Mockito.when(regeLnService.richtungWechsel(any())).thenReturn(false);
//        Mockito.when(regeLnService.sichFarbeWuenschen(any(),1)).thenReturn(Farbe.HERZ);
//        Mockito.when(regeLnService.mussKarteZiehen(alex,herz7, 2)).thenReturn(2);
//        Assertions.assertEquals(2,spielService.legeKarte(spiel,herz7,spiel.getAktiverSpieler()).getAnzahlKartenZiehen());
//    }
}
