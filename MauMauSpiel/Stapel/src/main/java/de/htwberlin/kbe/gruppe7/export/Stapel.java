package de.htwberlin.kbe.gruppe7.export;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Embeddable
public class Stapel {
    @Id
    @GeneratedValue
    private Long id;

    private List<Karte> ziehStapel;

    private List<Karte> ablageStapel;

    private long anzahlKartenstapel = 32;

    private int anzahlAnfangSpielerHandKarten = 5;

    private Karte obersteKarte;

    public Stapel() {
        this.ziehStapel = new ArrayList<>();
        this.ablageStapel = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnzahlKartenstapel(long anzahlKartenstapel) {
        this.anzahlKartenstapel = anzahlKartenstapel;
    }

    public void setAnzahlAnfangSpielerHandKarten(int anzahlAnfangSpielerHandKarten) {
        this.anzahlAnfangSpielerHandKarten = anzahlAnfangSpielerHandKarten;
    }

    public List<Karte> getZiehStapel() {
        return ziehStapel;
    }

    public void setZiehStapel(List<Karte> ziehStapel) {
        this.ziehStapel = ziehStapel;
    }

    public List<Karte> getAblageStapel() {
        return ablageStapel;
    }

    public void setAblageStapel(List<Karte> ablageStapel) {
        this.ablageStapel = ablageStapel;
    }

    public long getAnzahlKartenstapel() {
        return anzahlKartenstapel;
    }

    public Karte getObersteKarte() {
        return obersteKarte;
    }

    public void setObersteKarte(Karte obersteKarte) {
        this.obersteKarte = obersteKarte;
    }

    public int getAnzahlAnfangSpielerHandKarten() {
        return anzahlAnfangSpielerHandKarten;
    }

}
