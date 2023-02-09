package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.entity;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

@Entity
public class Spiel {

    private Long id;
    private List<Spieler> spielerListe;
    private Spieler aktiverSpieler;
    private int spielrunde;
    private int anzahlKartenZiehen;
    private boolean standardregeln;
    private boolean naechsterSpielerMussFarbeWuenschen;
    private boolean naechsterSpielerMussAussetzen;
    private boolean erfolgreicheAblage;
    private boolean spielrichtungRechts;
    private String siegername;

    private List<Karte> ziehstapel;
    private List<Karte> ablegestapel;
    private Farbe spielfarbe;


    /** Konstruktor einer Spielinstanz.
     *
     * @param id           Aktuelle ID des Spiels
     * @param spielerListe      Lister aller teilnehmenden Spieler
     * @param ziehstapel   Liste von Karten zum Ziehen
     * @param ablegestapel Liste von Karten zum Ablegen
     * @param spielrunde   Spielrunde
     */

    public Spiel(Long id, List<Spieler> spielerListe, List<Karte> ziehstapel, List<Karte> ablegestapel, int spielrunde) {
        this.id = id;
        this.spielerListe = spielerListe;
        this.ziehstapel = ziehstapel;
        this.ablegestapel = ablegestapel;
        this.spielrunde = spielrunde;
    }

    public Spiel() {

    }

    public Spieler getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(Spieler aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    /** Gibt die aktuelle ID des Spiels zurueck.
     *
     * @return id
     */

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /** Legt die aktuelle Spiel ID fest.
     *
     * @param id ID des Spiels
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Gibt eine Liste aller teilnehmenden Spieler dieses Spiels zurueck.
     *
     * @return Liste aller Spieler
     */
    public List<Spieler> getSpielerListe() {
        return spielerListe;
    }

    /** Setzt die Spieler.
     *
     * @param spielerListe Liste aller Spieler
     */
    public void setSpielerListe(List<Spieler> spielerListe) {
        this.spielerListe = spielerListe;
    }

    /** Gibt den aktuellen Ziehstapel zurueck.
     *
     * @return aktueller Ziehstapel
     */
    public List<Karte> getZiehstapel() {
        return ziehstapel;
    }

    /** Setzt den aktuelle Ziehstapel.
     *
     * @param ziehstapel Liste von Ziehkarten
     */
    public void setZiehstapel(List<Karte> ziehstapel) {
        this.ziehstapel = ziehstapel;
    }

    /** Gibt den aktuellen Ablegestapel zurueck.
     *
     * @return aktueller Ablagestapel
     */
    public List<Karte> getAblegestapel() {
        return ablegestapel;
    }

    /** Setzt den aktuellen Ablegestapel fest.
     *
     * @param ablegestapel Liste von Ablagekarten
     */
    public void setAblegestapel(List<Karte> ablegestapel) {
        this.ablegestapel = ablegestapel;
    }


    /** Gibt die aktuelle Rundenzahl zurueck.
     *
     * @return rundenzahl aktuelle Rundenzahl
     */
    public int getSpielrunde() {
        return spielrunde;
    }

    /** Setzt die aktuelle Rundenzahl.
     *
     * @param spielrunde aktuelle Rundenzahl
     */
    public void setSpielrunde(int spielrunde) {
        this.spielrunde = spielrunde;
    }

    /** Gibt die aktuelle Spielfarbe zurück.
     *
     * @return die aktuelle Spielfarbe
     */
    public Farbe getSpielfarbe() {
        return spielfarbe;
    }

    /**
     * Setzt die aktuelle Spielfarbe. (Kann sich aufgrund einer Wunschfarbe vom Ablegestapel unterscheiden.)
     * *
     *
     * @param farbe die zu setzende Spielfarbe
     */
    public void setSpielfarbe(Farbe farbe) {
        this.spielfarbe = farbe;
    }

    /**
     * Gibt die oberste Karte des Ablegestapels zurück.
     *
     * @return die oberste Karte des Ablegestapels
     */
    public Karte getObersteKarteAblagestapel() {
        return ablegestapel.get(ablegestapel.size() - 1);
    }


    public boolean isStandardregeln() {
        return standardregeln;
    }

    public void setStandardregeln(boolean standardregeln) {
        this.standardregeln = standardregeln;
    }

    public boolean isNaechsterSpielerMussFarbeWuenschen() {
        return naechsterSpielerMussFarbeWuenschen;
    }

    public void setNaechsterSpielerMussFarbeWuenschen(boolean naechsterSpielerMussFarbeWuenschen) {
        this.naechsterSpielerMussFarbeWuenschen = naechsterSpielerMussFarbeWuenschen;
    }

    public boolean isNaechsterSpielerMussAussetzen() {
        return naechsterSpielerMussAussetzen;
    }

    public void setNaechsterSpielerMussAussetzen(boolean naechsterSpielerMussAussetzen) {
        this.naechsterSpielerMussAussetzen = naechsterSpielerMussAussetzen;
    }

    public boolean isErfolgreicheAblage() {
        return erfolgreicheAblage;
    }

    public void setErfolgreicheAblage(boolean erfolgreicheAblage) {
        this.erfolgreicheAblage = erfolgreicheAblage;
    }

    public String getSiegername() {
        return siegername;
    }

    public void setSiegername(String siegername) {
        this.siegername = siegername;
    }

    public int getAnzahlKartenZiehen() {
        return anzahlKartenZiehen;
    }

    public void setAnzahlKartenZiehen(int anzahlKartenZiehen) {
        this.anzahlKartenZiehen = anzahlKartenZiehen;
    }

    public boolean isSpielrichtungRechts() {
        return spielrichtungRechts;
    }

    public void setSpielrichtungRechts(boolean spielrichtungRechts) {
        this.spielrichtungRechts = spielrichtungRechts;
    }
}


