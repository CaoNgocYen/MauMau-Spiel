package de.htwberlin.kbe.gruppe7.MauMauSpiel.impl.Impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.impl.DAO.SpielVorrichtung;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.exception.AbgelegteKarteIstUngueltig;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.RegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.DAO.SpielDao;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.impl.SpielServiceImpl;
import de.htwberlin.kbe.gruppe7.exceptions.IllegelStapelGroesseException;
import de.htwberlin.kbe.gruppe7.export.StapelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpielServiceImplTest {
    @InjectMocks
    private SpielServiceImpl spielService;
    @Mock
    private StapelService stapelService;
    @Mock
    private RegelnService regelnService;
    @Mock
    private KartenService kartenService;
    @Mock
    private SpielerService spielerService;
    @Mock
    private KIService kiService;
    @Mock
    private SpielDao spielDao;

    private List<Spieler> spielerListe;
    private Spiel spiel;

    @BeforeEach
    public void setUp() {
        spielerListe = SpielVorrichtung.players();
        spiel = SpielVorrichtung.spiel();
    }

    @Test
    @DisplayName("sollte Spielerwunsch setzen und den Status auf false setzen")
    public void setUserWish() {
        Farbe wunschFarbe = Farbe.KREUZ;
        spiel.setNaechsterSpielerMussFarbeWuenschen(true);

        spielService.wunschFarbeSetzen(wunschFarbe, spiel);

        assertFalse(spiel.isNaechsterSpielerMussFarbeWuenschen());
        assertEquals(wunschFarbe, spiel.getWunschFarbe());
    }

    @Test
    @DisplayName("sollte regelnService, stapelService und spielerService aufrufen, wenn eine gespielte Karte validiert wird")
    public void validateCardOfPlayer() throws AbgelegteKarteIstUngueltig {
        Karte expectedTopCard = new Karte(Wert.KOENIG, Farbe.KREUZ);
        Karte gelegteKarte = new Karte(Wert.SIEBEN, Farbe.KREUZ);
        Farbe wunschFarbe = Farbe.KREUZ;
        spiel.setWunschFarbe(wunschFarbe);
        spiel.getZiehstapel().setObersteKarte(expectedTopCard);
        doNothing().when(spielerService).handKarteEntfernen(spiel.getAktiverSpieler(), gelegteKarte);

        spielService.ueberpruefenKarte(gelegteKarte, spiel);

        assertNull(spiel.getWunschFarbe());
        verify(regelnService).ueberpruefenKarte(
                argThat(card -> card.equals(gelegteKarte)),
                argThat(obersteKarte -> obersteKarte.equals(expectedTopCard)),
                argThat(farbe -> farbe.equals(wunschFarbe)),
                intThat(drawCounter -> drawCounter == spiel.getAnzahlKartenZiehen())
        );

        verify(stapelService).karteZuObersteKarteSetzen(
                argThat(deck -> deck.equals(spiel.getZiehstapel())),
                argThat(card -> card.equals(gelegteKarte))
        );
        verify(spielerService).handKarteEntfernen(
                argThat(player -> player.equals(spiel.getAktiverSpieler())),
                argThat(card -> card.equals(gelegteKarte))
        );
    }

    @Test
    @DisplayName("sollte regelnService, stapelService und kiService aufrufen, wenn eine gespielte Karte validiert wird")
    public void validateCardOfAIPlayer() throws AbgelegteKarteIstUngueltig {
        spiel.getAktiverSpieler().setVirtuellerSpieler(true);
        Karte expectedTopCard = new Karte(Wert.KOENIG, Farbe.KREUZ);
        Karte gelegteKarte = new Karte(Wert.SIEBEN, Farbe.KREUZ);
        Farbe wunschFarbe = Farbe.KREUZ;
        spiel.setWunschFarbe(wunschFarbe);
        spiel.getZiehstapel().setObersteKarte(expectedTopCard);
        doNothing().when(kiService).karteEntfernen(spiel.getAktiverSpieler(), gelegteKarte);

        spielService.ueberpruefenKarte(gelegteKarte, spiel);

        assertNull(spiel.getWunschFarbe());
        verify(regelnService).ueberpruefenKarte(
                argThat(card -> card.equals(gelegteKarte)),
                argThat(topCard -> topCard.equals(expectedTopCard)),
                argThat(suit -> suit.equals(wunschFarbe)),
                intThat(drawCounter -> drawCounter == spiel.getAnzahlKartenZiehen())
        );

        verify(stapelService).karteZuObersteKarteSetzen(
                argThat(deck -> deck.equals(spiel.getZiehstapel())),
                argThat(card -> card.equals(gelegteKarte))
        );
        verify(kiService).karteEntfernen(
                argThat(player -> player.equals(spiel.getAktiverSpieler())),
                argThat(card -> card.equals(gelegteKarte))
        );
    }


    @Test
    @DisplayName("sollte die Regel \"Bube auf Bube\" anwenden")
    public void applyIsCardJackRule() {
        Karte obersteKarte = new Karte(Wert.BUBE, Farbe.KREUZ);
        spiel.getZiehstapel().setObersteKarte(obersteKarte);
        when(regelnService.istBudeKarte(any())).thenReturn(true);

        spielService.regelnAnwenden(spiel);

        assertTrue(spiel.isNaechsterSpielerMussFarbeWuenschen());
        verify(regelnService).istBudeKarte(argThat(card -> card.equals(obersteKarte)));
    }

    @Test
    @DisplayName("sollte gezogene Karten vom Ablagestapel zu den Handkarten der Spieler hinzufügen")
    public void dealCardsToPlayer() {
        List<Karte> drawnCards = List.of(new Karte(Wert.SIEBEN, Farbe.PIK), new Karte(Wert.ACHT, Farbe.KREUZ));
        when(stapelService.kartenAusZiehstapelZiehen(any(), anyInt())).thenReturn(drawnCards);
        doNothing().when(spielerService).handKarteHinzufuegen(any(), anyList());

        spielService.mussKartenZiehen(2, spiel);

        verify(stapelService).kartenAusZiehstapelZiehen(argThat(
                        deck -> deck.equals(spiel.getZiehstapel())),
                intThat(numberOfDrawnCards -> numberOfDrawnCards == 2)
        );
        verify(spielerService).handKarteHinzufuegen(argThat(
                        player -> player.equals(spiel.getAktiverSpieler())),
                argThat(cards -> cards.equals(drawnCards))
        );
    }

    @Test
    @DisplayName("sollte gezogene Karten vom Ablagestapel zu den Handkarten der KI-Spieler hinzufügen")
    public void dealCardsToAIPlayer() {
        spiel.getAktiverSpieler().setVirtuellerSpieler(true);
        List<Karte> drawnCards = List.of(new Karte(Wert.SIEBEN, Farbe.PIK), new Karte(Wert.ACHT, Farbe.KREUZ));
        when(stapelService.kartenAusZiehstapelZiehen(any(), anyInt())).thenReturn(drawnCards);
        doNothing().when(kiService).kartenHinzufuegen(any(), anyList());

        spielService.mussKartenZiehen(2, spiel);

        verify(stapelService).kartenAusZiehstapelZiehen(argThat(
                        deck -> deck.equals(spiel.getZiehstapel())),
                intThat(numberOfDrawnCards -> numberOfDrawnCards == 2)
        );
        verify(kiService).kartenHinzufuegen(argThat(
                        player -> player.equals(spiel.getAktiverSpieler())),
                argThat(cards -> cards.equals(drawnCards))
        );
    }

    @Test
    @DisplayName("sollte handkartenHandeln aufrufen und kartenZiehen für jeden Spieler im Spiel aufrufen")
    public void shouldDrawInitialHandCards() {
        spiel.getSpielerListe().get(0).setVirtuellerSpieler(true);
        spiel.getSpielerListe().get(1).setVirtuellerSpieler(true);
        when(stapelService.spielerHandkartenHandeln(any())).thenReturn(SpielVorrichtung.karten().subList(0, 5));
        doNothing().when(kiService).kartenHinzufuegen(any(), anyList());
        doNothing().when(spielerService).handKarteHinzufuegen(any(), anyList());

        spielService.handkartenHandeln(spiel);

        verify(stapelService, times(4)).spielerHandkartenHandeln(any());
        verify(kiService, times(2)).kartenHinzufuegen(any(), anyList());
        verify(spielerService, times(2)).handKarteHinzufuegen(any(), anyList());
    }

    @Test
    @DisplayName("sollte den 'mau'-Status vom aktiven Spieler auf false zurücksetzen")
    public void testMauGesagt() {
        spiel.getAktiverSpieler().setMauGesagt(true);

        spielService.mauGesagt(spiel);

        assertFalse(spiel.getAktiverSpieler().isMauGesagt());
    }

    @Test
    @DisplayName("sollte das Spiel für zu Ende erklären, wenn der aktive Spieler keine Handkarten mehr hat")
    public void gameIsOver() {
        spiel.getAktiverSpieler().setSpielerStapel(new ArrayList<>());

        assertTrue(spielService.istSpielAngeschlossen(spiel));
    }

    @Test
    @DisplayName("sollte das Spiel nicht zu Ende erklären, wenn der aktive Spieler mindestens eine Handkarte hat")
    public void gameIsNotOver() {
        spiel.getAktiverSpieler().setSpielerStapel(List.of(new Karte(Wert.BUBE, Farbe.KREUZ)));

        assertFalse(spielService.istSpielAngeschlossen(spiel));
    }

    @Test
    @DisplayName("sollte true zurückgeben, wenn Spiele in der Datenbank gefunden werden, oder false, wenn nicht")
    public void testSpielHaben1(){
        when(spielDao.findSpiel()).thenReturn(true, false);

        assertTrue(spielService.spielHaben());
        assertFalse(spielService.spielHaben());
    }

    @Test
    @DisplayName("sollte true zurückgeben, wenn Spiele in der Datenbank gefunden werden, oder false, wenn nicht")
    public void testSpielHaben2(){
        when(spielDao.findSpiel()).thenReturn(true, false);

        assertTrue(spielService.spielHaben());
        assertFalse(spielService.spielHaben());
    }

    @Test
    @DisplayName("sollte die Delete-Methode des Daos zum Löschen eines Spielobjekts in der Datenbank aufrufen")
    public void testDeleteSpiel(){
        doNothing().when(spielDao).deleteSpiel(any());

        spielService.deleteSpiel(spiel);

        verify(spielDao).deleteSpiel(argThat(g -> g.equals(spiel)));
    }

    @Test
    @DisplayName("sollte das Spiel zurückgeben, das anhand der Spiel-ID in der Datenbank gefunden wurde")
    public void getGame(){
        when(spielDao.findById(anyLong())).thenReturn(spiel);

        Spiel aktuellesSpiel = spielService.getSavedGame(1L);

        verify(spielDao).findById(longThat(id -> id == spiel.getId()));
        assertEquals(spiel, aktuellesSpiel);
    }

}



