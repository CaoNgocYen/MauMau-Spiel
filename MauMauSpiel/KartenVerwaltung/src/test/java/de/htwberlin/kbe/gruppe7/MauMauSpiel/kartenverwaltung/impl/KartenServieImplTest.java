package de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;

import org.junit.jupiter.api.*;

import java.util.List;

public class KartenServieImplTest {

	private KartenService kartenService;
	private Karte karte = new Karte(Wert.ACHT, Farbe.KARO);

	@BeforeAll
	public static void initialize() {
	}

	@BeforeEach
	public void setUp() {
		kartenService = (KartenService) new KartenServiceImpl();
	}


	@Test
	@DisplayName("Eine Karte soll erstellt werden")
	public void testAnlegenKarte(){

		Wert wert = Wert.NEUN;
		Farbe farbe = Farbe.PIK;

		Karte karte = kartenService.anlegenKarte(wert, farbe);

		Assertions.assertNotNull(karte);
		Assertions.assertEquals(wert, karte.getWert());
		Assertions.assertEquals(farbe, karte.getFarbe());
	}
	@Test
	@DisplayName("Ein Stapel soll erstellt werden")
	public void testAnlegenStapel(){
		int sizeStapel = 32;

		List<Karte> kartenStapel = kartenService.anlegenStapel(32);

		Assertions.assertNotNull(kartenStapel);
		Assertions.assertEquals(sizeStapel, kartenStapel.size());
	}

	@Test
	@DisplayName("Die Karten sollen zum Deck eines Spielers hinzugefügt werden")
	public void testHinzufuegfenKarte(){
		int anzahl = 4;
		List<Karte> kartenStapel = kartenService.anlegenStapel(32);

		List<Karte> spielerStapel = kartenService.hinzufuegfenKarte(kartenStapel, anzahl);

		Assertions.assertNotNull(spielerStapel);
		Assertions.assertEquals(anzahl, spielerStapel.size());
	}


	@Test
	@DisplayName("Die Karte sollt aus dem Deck eines Spielers entfernt werden")
	public void testEntfernenKarten(){
		int anzahl = 5;
		List<Karte> kartenStapel1 = kartenService.anlegenStapel(32);
		List<Karte> kartenStapel2 = kartenService.hinzufuegfenKarte(kartenStapel1, anzahl);
		kartenStapel2.add(karte);

		List<Karte> kartenStapel3 = kartenService.entfernenKarten(kartenStapel2, karte);

		Assertions.assertNotNull(kartenStapel2);
		Assertions.assertEquals(kartenStapel2, kartenStapel3);
	}

	@Test
	@DisplayName("Die Karten sollen gemischt werden")
	public void testMischenKarten() {
		List<Karte> kartenStapel = kartenService.anlegenStapel(32);
		Karte karte1 = kartenStapel.get(0);

		kartenService.mischenKarten(kartenStapel);
		Karte karte2 = kartenStapel.get(0);

		Assertions.assertNotNull(kartenStapel);
		Assertions.assertNotEquals(karte1, karte2);
	}

//	@Test
//	public void testEntfernenKartenThrowsKarteNichtGefundenException() throws KarteNichtGefundenException{
//		// Arrange
//		int anzahl = 5;
//		List<Karte> kartenStapel1 = kartenService.anlegenStapel(32);
//		List<Karte> kartenStapel2 = kartenService.hinzufuegfenKarte(kartenStapel1, anzahl);
//
//		// Act
//		kartenService.entfernenKarten(kartenStapel2, karte);
//
//		// Assert
//		Assertions.assertNotNull(kartenStapel2);
//		Assertions.assertFalse(kartenStapel2.contains(karte));
//	}

	@AfterEach
	public void tearDown() {

	}

	@AfterAll
	public static void uninitialize() {

	}

}
