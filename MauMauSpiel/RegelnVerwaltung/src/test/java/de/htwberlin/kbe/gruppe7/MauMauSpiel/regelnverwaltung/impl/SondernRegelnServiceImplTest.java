package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.SondernRegelnService;

public class SondernRegelnServiceImplTest {

	private SondernRegelnService sondernRegelnService;

	private final Karte pik7 = new Karte(Wert.SIEBEN, Farbe.PIK);
	private final Karte pik9 = new Karte(Wert.NEUN, Farbe.PIK);
	private final Karte bube8 = new Karte(Wert.BUBE, Farbe.KARO);
	private final Karte kreuzAss = new Karte(Wert.ASS, Farbe.KREUZ);

	@BeforeAll
	public static void initialize() {
	}

	@BeforeEach
	public void setUp() {
		sondernRegelnService = (SondernRegelnService) new SondernRegelnServiceImpl();
	}

	@Test
	public void testBudeNichtÜberBude() throws KarteNichtGefundenException {
		int anzahlZuziehendKarten = 2;
		sondernRegelnService.ueberpruefenKarte(bube8, pik7, null, anzahlZuziehendKarten);
	}

	@Test
	public void testRichtungWechsel() {
		Assertions.assertTrue(sondernRegelnService.richtungWechsel(pik9));
	}


	@Test
	public void testSpielerAussetzen() {
		Assertions.assertFalse(sondernRegelnService.spielerAussetzen(kreuzAss));
	}

	@AfterEach
	public void tearDown() {

	}

	@AfterAll
	public static void uninitialize() {

	}

}
