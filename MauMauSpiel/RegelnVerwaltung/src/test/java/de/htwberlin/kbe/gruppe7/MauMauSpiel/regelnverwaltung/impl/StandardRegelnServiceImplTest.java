package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.StandardRegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.entity.Spieler;

public class StandardRegelnServiceImplTest {

    private StandardRegelnService standardRegelnService;

    private final Karte pik7 = new Karte(Wert.SIEBEN, Farbe.PIK);
    private final Karte karo8 = new Karte(Wert.ACHT, Farbe.KARO);
    private final Spieler spieler = new Spieler("Alex");


    @BeforeAll
    public static void initialize() {
    }

    @BeforeEach
    public void setUp() {
        standardRegelnService = (StandardRegelnService) new StandardRegelnServiceImpl();
    }


    @Test
    public void testSpielerMauNichtGesagt() {
        spieler.setGesagtMau(true);
        spieler.setSpielerStapel(List.of(pik7, karo8));

        Assertions.assertFalse(standardRegelnService.spielerMauNichtGesagt(spieler));
    }

    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void uninitialize() {

    }

}
