package de.htwberlin.kbe.gruppe7.kartenverwaltung.impl;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl.KartenServiceImpl;
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
	public void testAnlegenKarte(){
		// Arrange
		Wert wert = Wert.NEUN;
		Farbe farbe = Farbe.PIK;

		// Act
		Karte karte = kartenService.anlegenKarte(wert,farbe);

		// Assert
		Assertions.assertNotNull(karte);
		Assertions.assertEquals(wert, karte.getWert());
		Assertions.assertEquals(farbe, karte.getFarbe());
	}


	@Test
	public void testAnlegenStapel(){
		// Arrange
		int sizeStapel = 32;

		// Act
		List<Karte> kartenStapel = kartenService.anlegenStapel();

		// Assert
		Assertions.assertNotNull(kartenStapel);
		Assertions.assertEquals(sizeStapel, kartenStapel.size());
	}
	

	@Test
	public void testHinzufuegfenKarte(){
		// Arrange
		int anzahl = 4;
		List<Karte> kartenStapel = kartenService.anlegenStapel();

		// Act
		List<Karte> spielerStapel = kartenService.hinzufuegfenKarte(kartenStapel, anzahl);

		// Assert
		Assertions.assertNotNull(spielerStapel);
		Assertions.assertEquals(anzahl, spielerStapel.size());
	}
	

	@Test
	public void testEntfernenKarten() throws KarteNichtGefundenException {
		// Arrange
		int anzahl = 5;
		List<Karte> kartenStapel1 = kartenService.anlegenStapel();
		List<Karte> kartenStapel2 = kartenService.hinzufuegfenKarte(kartenStapel1, anzahl);
		kartenStapel2.add(karte);
	
		// Act
		List<Karte> kartenStapel3 = kartenService.entfernenKarten(kartenStapel2, karte);

		// Assert
		Assertions.assertNotNull(kartenStapel2);
		Assertions.assertEquals(kartenStapel2, kartenStapel3);
	}
	

	@Test
	public void testEntfernenKartenThrowsKarteNichtGefundenException() throws KarteNichtGefundenException{
		// Arrange
		int anzahl = 5;
		List<Karte> kartenStapel1 = kartenService.anlegenStapel();
		List<Karte> kartenStapel2 = kartenService.hinzufuegfenKarte(kartenStapel1, anzahl);
	
		// Act
		kartenService.entfernenKarten(kartenStapel2, karte);

		// Assert
		Assertions.assertNotNull(kartenStapel2);
		Assertions.assertFalse(kartenStapel2.contains(karte));
	}
	

	@Test
	public void testMischenKarten() {
		// Arrange
		List<Karte> kartenStapel = kartenService.anlegenStapel();
		Karte karte1 = kartenStapel.get(0);
	
		// Act
		kartenService.mischenKarten(kartenStapel);
		Karte karte2 = kartenStapel.get(0);
		
		// Assert
		Assertions.assertNotNull(kartenStapel);
		Assertions.assertNotEquals(karte1, karte2);
	}
	
	@AfterEach
	public void tearDown() {
		
	}

	@AfterAll
	public static void uninitialize() {

	}
	
}
