package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import org.junit.jupiter.api.*;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SondereRegeLnServiceImplTest {

	private RegelnService regelnService;

	private final Karte pik7 = new Karte(Wert.SIEBEN, Farbe.PIK);
	private final Karte herzBube = new Karte(Wert.BUBE, Farbe.HERZ);
	private final Karte pik9 = new Karte(Wert.NEUN, Farbe.PIK);
	private final Karte herzAss = new Karte(Wert.ASS, Farbe.HERZ);

	private final Karte herz7 = new Karte(Wert.SIEBEN, Farbe.HERZ);
	private final Karte kreuz9 = new Karte(Wert.NEUN, Farbe.KREUZ);
	private final Karte karoBube = new Karte(Wert.BUBE, Farbe.KARO);
	private final Karte kreuzAss = new Karte(Wert.ASS, Farbe.KREUZ);
	private final Farbe Farbwunsch = Farbe.HERZ;

	private final Spieler spieler = new Spieler("Musterman");

	private int anzahlZuziehendeKarten = 0;

	@BeforeAll
	public static void initialize() {
	}

	@BeforeEach
	public void setUp() {
		regelnService = (RegelnService) new SondereRegelnServiceImpl();
	}

	@Test
	@DisplayName("sollte keine Exception werfen, wenn die Farbe der gespielten Karte mit der Farbe der obersten Karte übereinstimmt")
	public void testGespieltefarbePasstZuObersterfarbe() throws AbgelegteKarteIstUngueltig {
		regelnService.ueberpruefenKarte(pik7, pik9, null, anzahlZuziehendeKarten);
	}

	@Test
	@DisplayName("sollte keine Exception werfen, wenn der Wert der gespielten Karte mit dem Wert der obersten Karte übereinstimmt")
	public void testGespielterwertPasstZuOberstenwert() throws AbgelegteKarteIstUngueltig {
		regelnService.ueberpruefenKarte(pik9, kreuz9, null, anzahlZuziehendeKarten);
	}

	@Test
	@DisplayName("soll eine Exception werfen, wenn Label und Farbe nicht übereinstimmen")
	public void testIstWertOderFarbeUngueltig() {
		Exception exception = assertThrows(AbgelegteKarteIstUngueltig.class, () -> {
			regelnService.ueberpruefenKarte(pik9, herzAss, null, anzahlZuziehendeKarten);
		});

		String expectedMessage = "Die Karte kann nicht gespielt werden. Wert oder Farbe stimmen nicht überein.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("soll eine Exception werfen, wenn die Spielerkarte mit dem Farbwunsch übereinstimmt, aber Bube auf Bube gespielt wird")
	public void testFarwunschIstGueltig() {
		Exception exception = assertThrows(AbgelegteKarteIstUngueltig.class, () -> {
			regelnService.ueberpruefenKarte(karoBube, herzBube, Farbwunsch, anzahlZuziehendeKarten);
		});

		String expectedMessage = "Eine BUBE auf BUBE ist nicht erlaubt.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("soll keine Exception werfen, wenn die gespielte Karte dem Farbwunsch des Spielers entspricht")
	public void testFarbwunschPasstZuGespielterkarte() throws AbgelegteKarteIstUngueltig {
		regelnService.ueberpruefenKarte(herzAss, karoBube, Farbwunsch, anzahlZuziehendeKarten);
	}
	@Test
	@DisplayName("sollte keine Exception werfen, wenn Bube gespielt wird und Farbe oder Wert der obersten Karte nicht übereinstimmen")
	public void testBubeNichtAufBube() throws AbgelegteKarteIstUngueltig {
		int anzahlZuziehendKarten = 2;
		regelnService.ueberpruefenKarte(karoBube, pik7, null, anzahlZuziehendeKarten);
	}

	@Test
	@DisplayName("sollte eine Exception werfen, wenn Bube auf Bube gespielt wird")
	public void testBubeAufBube() {
		Exception exception = assertThrows(AbgelegteKarteIstUngueltig.class, () -> {
			regelnService.ueberpruefenKarte(karoBube, herzBube, null, anzahlZuziehendeKarten);
		});

		String expectedMessage = "Eine BUBE auf BUBE ist nicht erlaubt.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("soll keine Exception werfen, wenn der Wert 7 nicht gespielt wird und die Anzahl von zuziehenden Karten 0 ist, aber der Wert der obersten Karte 7 ist")
	public void testGespielterwerMussNichtGleichObersterwert () throws AbgelegteKarteIstUngueltig {
		regelnService.ueberpruefenKarte(pik9, pik7, null, anzahlZuziehendeKarten);
	}

	@Test
	@DisplayName("soll eine Exception werfen, wenn die Spielerkarte nicht mit dem Farbwunsch übereinstimmt")
	public void testFarbwunsch() {
		Exception exception = assertThrows(AbgelegteKarteIstUngueltig.class, () -> {
			regelnService.ueberpruefenKarte(pik9, karoBube, Farbwunsch, anzahlZuziehendeKarten);
		});

		String expectedMessage = "Die Karte kann nicht gespielt werden. Die Farbe entspricht nicht dem Wunsch des Spielers.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("soll eine Exception werfen, wenn der Spieler SIEBEN hat und die oberste Karte SIEBEN ist, der Spieler aber keine SIEBEN spielen will")
	public void testSiebenAufSieben() {
		spieler.setSpielerStapel(List.of(pik7));
		anzahlZuziehendeKarten = 2;
		Exception exception = assertThrows(AbgelegteKarteIstUngueltig.class, () -> {
			regelnService.ueberpruefenKarte(pik9, herz7, null, anzahlZuziehendeKarten);
		});

		String expectedMessage = "Du muss ein SIEBEN spielen.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("prüft, ob SEVEN gespielt wird")
	public void testMussKartenZiehen() {
		assertTrue(regelnService.mussKarteZiehen(herz7));
	}

	@Test
	@DisplayName("der Spieler muss Karten ziehen, wenn die Handkarte keine SIEBEN ist, aber die oberste Karte eine SIEBEN ist und der Ziehzähler größer oder gleich 2 ist.")
	public void testMussMehrAlsZweiKartenZiehen() {
		int anzahlZuziehendeKarten = 4;
		spieler.setSpielerStapel(List.of(pik9));

		assertTrue(regelnService.mussKarteZiehen(spieler, herz7, anzahlZuziehendeKarten));
	}
	@Test
	@DisplayName("prüft, ob die Karte ASS ist")
	public void testIstAss() {
		assertTrue(regelnService.spielerAussetzen(herzAss));
	}

	@Test
	@DisplayName("prüft, ob Karte BUBE ist")
	public void testIstBube() {
		assertTrue(regelnService.istBudeKarte(karoBube));
	}

	@Test
	@DisplayName("prüft, ob Karte Neun ist")
	public void testIstNeun() {
		assertTrue(regelnService.richtungWechsel(pik9));
	}

	@Test
	@DisplayName("prüft, ob Spieler 'mau' gesagt hat und eine Handkarte übrig hat")
	public void testMauIstUngueltig1() {
		spieler.setMauGesagt(true);
		spieler.setSpielerStapel(List.of(pik9));
		assertFalse(regelnService.istMauUnGueltig(spieler));
	}

	@Test
	@DisplayName("prüft, ob der Spieler mehr als eine Handkarte hat und nicht 'mau' gesagt hat")
	public void testMauIstUngueltig2() {
		spieler.setMauGesagt(false);
		spieler.setSpielerStapel(List.of(kreuz9, pik7));
		assertFalse(regelnService.istMauUnGueltig(spieler));
	}

	@Test
	@DisplayName("prüft, ob der Spieler eine Handkarte hat und nicht 'mau' gesagt hat")
	public void testMauIstUngueltig3() {
		spieler.setMauGesagt(false);
		spieler.setSpielerStapel(List.of(pik9));
		assertTrue(regelnService.istMauUnGueltig(spieler));
	}

	@AfterEach
	public void tearDown() {
	}

	@AfterAll
	public static void uninitialize() {

	}

}
