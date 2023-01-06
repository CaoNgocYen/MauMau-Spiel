package de.htwberlin.kbe.gruppe7.regelnverwaltung.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.regelnverwaltung.export.RegelnService;

public class SondereRegelnImplTest {
 
	private RegelnService regelnService;

    @BeforeAll
	public static void initialize() {	
	}
	
	@BeforeEach
	public void setUp() {
		RegelnServiceImpl regelnService = new RegelnServiceImpl();
	}

	@Test
    public void testspielerAussetzen(){
		
		// Arrange
    	Karte karte = new Karte(Wert.ACHT, Farbe.PIK);

		// Act
		boolean spielerAussetzen = regelnService.spielerAussetzen(karte);

		// Assert
		Assertions.assertNotNull(spielerAussetzen);
		Assertions.assertTrue(spielerAussetzen);
    }

	
	@AfterEach
	public void tearDown() {
		
	}

	@AfterAll
	public static void uninitialize() {

	}
	
}
