package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.impl.KIServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VirtuellerSpielerServiceTest {
    private KIService kiService = new KIServiceImpl();

    @Test
    void testKiNamenGenerieren() {
        String name = kiService.kiNamenGenerieren();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @Test
    void testKiWuenschtFarbe() {
        Spieler kiSpieler = new Spieler();
        Farbe farbe = kiService.kiWuenschtFarbe(kiSpieler);
        assertNotNull(farbe);
    }


}
