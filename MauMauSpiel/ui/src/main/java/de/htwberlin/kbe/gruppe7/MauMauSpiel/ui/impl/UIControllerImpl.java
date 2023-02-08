package de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.export.KartenService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.SpielService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.export.UIControllerService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.view.UIView;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.Spieler;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.export.SpielerService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class UIControllerImpl implements UIControllerService {

    private static final Logger log =  LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);
    private final SpielerService spielerService;
    private final SpielService spielService;
    private final KartenService kartenService;
    private final KIService kiService;
    private final UIView UIView;
    private Spiel spiel;
    private List<String> spielerNamen;
    private final int anzahlKarten =32;
    private boolean spielWurdeAbgebrochen;
    private final Long spielID =1L;

    public UIControllerImpl(SpielService spielService, SpielerService spielerService, KartenService kartenService,
                            KIService kiService, UIView UIView){
        this.spielService  = spielService;
        this.spielerService= spielerService;
        this.kartenService= kartenService;
        this.kiService = kiService;
        this.UIView = UIView;
    }

    @Override
    public void run() {
        log.debug("UI Controller -run");
        UIView.willkommen();
        int startMenuChoice  = hauptmenuKontroller(menuPunktAuswaehlen(1,3));
        boolean spielAbbruchVorBeginn = false;
        switch (startMenuChoice){
            case 1 :
                spiel = neuesSpielErzeugen();
                break;
            case 2 :
                spiel = loadGame();
                break;
            case 3 :
                spielAbbruchVorBeginn = true;
                break;
            default:
                System.out.println("Spiel konnte nicht gestartet werden");
        }
        if (!spielAbbruchVorBeginn)
            spielablauf(spiel);

        scanner.close();
        UIView.ende();

    }
    /**
     * Startet den Spielablauf.
     * @param spielinstanz Die aktuelle Spielinstanz
     * @
     */
    private void spielablauf( Spiel spielinstanz)  {
        log.debug("UiController-spielablauf");
        boolean spielAktiv = true;
        while(spielAktiv && !spielWurdeAbgebrochen){


            try{Thread.sleep(3000);}
            catch (InterruptedException ignored){}

            if(!spielinstanz.getAktiverSpieler().isVirtuellerSpieler())
                menschlicherSpielzug();
            else
                virtuellerKISpielzug();

            if(!spielWurdeAbgebrochen) {
                // Prüfe auf Spielende
                spielAktiv = !spielService.pruefeAufSpielende(spielinstanz.getAktiverSpieler());
                if (!spielAktiv) {
                    UIView.gewonnen(spielinstanz.getAktiverSpieler().getName());
                    spielinstanz.setSiegername(spielinstanz.getAktiverSpieler().getName());
                    break;
                }
                // Setze Spielindex hoch
                spielinstanz.setSpielrunde(spielinstanz.getSpielrunde() + 1);
                spielService.setzeNaechstenSpieler(spielinstanz, spielinstanz.isNaechsterSpielerMussAussetzen());
                spielinstanz.setNaechsterSpielerMussAussetzen(false);
            }

        }
    }
    /**
     * Hier wird die Interaktion mit dem "echten" Spieler gesteuert.
     */
    private void menschlicherSpielzug() {
        log.debug("UiController-menschlicherSpielerSpielt");

        informationenSpieler(spiel.getAktiverSpieler(), spiel.getObersteKarteAblagestapel().toString());
        UIView.welcheKarte();

        Karte gelegteKarte = null;
        int eingabe = 0;
        boolean mauMauGesagt = false;
        boolean karteZiehen = false;
        spielWurdeAbgebrochen = false;
        int whileCounter = 0;

        while (!spielWurdeAbgebrochen) {
            try {
                eingabe = zahlenEingabe();
                gelegteKarte = spiel.getAktiverSpieler().getSpielerStapel().get(eingabe - 1);
                spiel = spielService.legeKarte(spiel, gelegteKarte, spiel.getAktiverSpieler());
                break;
            } catch (IndexOutOfBoundsException iobe) {
                if (eingabe == 100) {
                    karteZiehen = true;
                    break;
                } else if (eingabe == 200) { //&& !mauMauGesagt
                    mauMauGesagt = true;
                    UIView.karteLegenNachMau();
                } else if (eingabe == 300) {
                    spielWurdeAbgebrochen = true;
                    UIView.spielWirdAbgebrochen(spielID);
                } else {
                    UIView.karteExistiertNicht();
                }
            }
        }

        if (!spielWurdeAbgebrochen) {
            if (mauMauGesagt) {
                if (spielService.pruefeAufMau(spiel.getAktiverSpieler())) {
                    UIView.spielerMauSagen(spiel.getAktiverSpieler().getName());
                } else {
                    spielService.kartenAusZiehstapelZiehen(1, spiel);
                    UIView.spielerFalschMauGesagt(spiel.getAktiverSpieler().getName());
                }
            }

            if (karteZiehen) {
                spielService.kartenAusZiehstapelZiehen(1, spiel);
                UIView.karteGezogen(spiel.getAktiverSpieler().getName());
            } else if (spielService.getRegelnwerk().mussKarteZiehen(gelegteKarte)) {
                spielService.kartenAusZiehstapelZiehen(2, spiel);
                UIView.mussKartenZiehen(spielerService.getNaechsterSpieler(spiel.getSpielerListe(), spiel.getAktiverSpieler()).getName(), spiel.getAnzahlKartenZiehen(), "");
            } else if (spielService.getRegelnwerk().spielerAussetzen(gelegteKarte)) {
                spiel.setNaechsterSpielerMussAussetzen(true);
                UIView.mussAussetzen(spielerService.getNaechsterSpieler(spiel.getSpielerListe(), spiel.getAktiverSpieler()).getName());
            } else if (spielService.getRegelnwerk().mussKarteZiehen(gelegteKarte) ) {
                spiel.setNaechsterSpielerMussFarbeWuenschen(true);
                neueSpielfarbeSetzen();
            }
//            else if (spielService.getRegelnwerk().ueberpruefenKarte(gelegteKarte, spiel.getObersteKarteAblagestapel(), spiel.getSpielfarbe(), spiel.getAnzahlKartenZiehen())) {
//                List<Karte> ablegestapel = spiel.getAblegestapel();
//                ablegestapel.add(gelegteKarte);
//                spiel.setAblegestapel(ablegestapel);
//            }

            if (spielService.pruefeAufMau(spiel.getAktiverSpieler())) {
                spielService.kartenAusZiehstapelZiehen(1, spiel);
                UIView.mussKartenZiehen(spiel.getAktiverSpieler().getName(), 1, " da er nicht 'Mau' gesagt hat");
            }

            spiel.setSpielfarbe(spiel.getAblegestapel().get(spiel.getAblegestapel().size() - 1).getFarbe());

        }
    }

    /**
     * Probiert alle Karten des KI Spielers zu legen, bis eine gelegt werden kann. Falls er keine legen kann, wird eine Karte für ihn gezogen
     */
    private void virtuellerKISpielzug() {
        log.debug("UiController-virtuellerKISpielzug");
        int kartenIndex = 2;
        boolean erfolgreichGelegtOderGezogen = false;
        Karte gelegteKarte = null;

        UIView.kiSpielt(spiel.getObersteKarteAblagestapel().toString(),spiel.getAktiverSpieler().getName());
        // nach jedem Lauf wird KartenIndex hochgezählt bis eine Karte erfolgreich gelegt wurde oder bis eine Karte gezogen werden musste
        do{
            gelegteKarte = spiel.getAktiverSpieler().getSpielerStapel().get(kartenIndex - 1);
            try {
                spiel = spielService.legeKarte(spiel, gelegteKarte, spiel.getAktiverSpieler());
                erfolgreichGelegtOderGezogen = true;
            } catch(IndexOutOfBoundsException ignored){
                erfolgreichGelegtOderGezogen = false;
            }

            // Wenn KartenIndex schon größer als alle anderen Karten ist, dann ziehe neue Karte
            if (kartenIndex > spiel.getAktiverSpieler().getSpielerStapel().size()){
                spiel = spielService.kartenAusZiehstapelZiehen(1, spiel);
                UIView.kiHatKarteGezogen(spiel.getAktiverSpieler().getName());
                erfolgreichGelegtOderGezogen = true;
            }

            kartenIndex++;

        }while(!erfolgreichGelegtOderGezogen);

        UIView.kiHatGespielt(gelegteKarte.toString(),spiel.getAktiverSpieler().getName());

    }

    /**
     * Lässt die wichtigsten Infos, die der Spieler benötigt, anzeigen.
     */
    private void informationenSpieler(Spieler spieler, String obersteKarte){
        log.debug("UiController-informationenSpieler");
        List<Karte> karten = spieler.getSpielerStapel();
        ArrayList<String> kartenStrings = new ArrayList<>();
        int i = 1;
        for (Karte karte : karten) {
            kartenStrings.add("[" + i + "]    " + karte.toString());
            i++;
        }
        UIView.kartenInfosAnzeigen(obersteKarte, kartenStrings, spieler.getName());
    }

    private void neueSpielfarbeSetzen() {

        log.debug("UiController-neueSpielfarbeSetzen");
        Farbe neueSpielfarbe = null;    // Achtung: kann zu Nullpointerexception führen bei fehlerhafter Usereingabe
        UIView.neueSpielfarbeAbfragen();
        boolean bool = true;
        do {
            int usereingabe = menuPunktAuswaehlen(1, 4);
            switch (usereingabe) {
                case 1:
                    neueSpielfarbe = Farbe.HERZ;
                    bool = false;
                    break;
                case 2:
                    neueSpielfarbe = Farbe.KREUZ;
                    bool = false;
                    break;
                case 3:
                    neueSpielfarbe = Farbe.PIK;
                    bool = false;
                    break;
                case 4:
                    neueSpielfarbe = Farbe.KARO;
                    bool = false;
                    break;
                default:
                    UIView.falscheEingabe();
                    break;
            }
        } while (bool);

        spielService.aktuelleFarbe(neueSpielfarbe, spiel);
        UIView.neueSpielfarbeInfo(spiel.getAktiverSpieler().getName(), neueSpielfarbe);
    }


    private String menschlichenSpielerHinzufuegen() {
        log.debug("UiController-menschlichenSpielerHinzufuegen");
        String name = textEingabe();
        UIView.spielerWillkommen(name);
        return name;
    }

    private String textEingabe() {
        log.debug("UI Controller Texteingabe");
        String eingabe;
        while(true){
            try {
                eingabe =scanner.next();
                break;
            }catch (InputMismatchException e){
                UIView.falscheEingabe();
            }
        }
        return eingabe.toLowerCase();
    }


    /**
     * Methode fragt die gewünschte Anzahl der virtuellen Spieler ab und fügt diese dann der Spielerliste hinzu.
     *
     * @param menschlicheSpieler - bekommt die Liste der bisherigen Spielernamen uebergeben
     * @return - gibt eine Liste mit Spielernamen zurueck, die nun auch die Namen der virtuellen Spieler enthaelt
     */
    private List<String> kiSpielerHinzufuegen(List<String> menschlicheSpieler) {
        log.debug("UiController-kiSpielerHinzufuegen");
        boolean kiSpielerErforderlich = false;

        do {
            kiSpielerErforderlich = false;
            if (menschlicheSpieler.size() >= 5)
                UIView.keineKISpielerMoeglich();

            UIView.frageAnzahlKI();
            int gewuenschteAnzahl = zahlenEingabe();

            if (menschlicheSpieler.size() == 1 && gewuenschteAnzahl == 0) {
                UIView.kISpielerErforderlich();
                kiSpielerErforderlich = true;

            }

            for (int i = 1; i <= gewuenschteAnzahl; i++) {

                spielerNamen.add(kiService.kiNamenGenerieren());
            }
        } while(kiSpielerErforderlich);
        return spielerNamen;
    }

    /**
     * Methode steuert die Hauptmenü-Punkte des Spiels.
     * @param eingabe der ausgewählte Menüpunkt
     */
    public int hauptmenuKontroller(int eingabe)  {
        log.debug("UiController-menuKontroller");
        int usereingabe = 0;
        switch (eingabe) {
            case 1:
                //[1] Neues Spiel starten
                UIView.spielenOderRegelnAnzeigen();
                int eingabe2 = menuPunktAuswaehlen(1, 3);
                regelnMenuKontroller(eingabe2);
                usereingabe = 1;
                break;

            case 2:
                //[2] Spiel fortsetzen
                usereingabe = 2;
                break;

            default:
                UIView.falscheEingabe();
                break;

        }
        return usereingabe;
    }
    /**
     * Methode steuert Menü-Punkte bevor Spiel gestartet wird und fragt, ob Regeln angezeigt werden sollen.
     * @param eingabe der ausgewählte Menüpunkt
     */
    public void regelnMenuKontroller(int eingabe) {
        log.debug("UiController-regelnMenuKontroller");
        switch (eingabe) {
            case 1:
                //[1] Ich kenne die Regeln
                break;

            case 2:
                //[2] Bitte zeig Spielregeln an
                UIView.regelnAnzeigen();
                break;

            default:
                UIView.falscheEingabe();
                break;

        }

    }

    /**
     * Methode liest eine Zahl über die Tastatur ein und wählt den Menüpunkt der Zahl entsprechend aus.
     * @param min - kleinste Zahl, die der Nutzer eingeben darf
     * @param max - höchste Zahl, die der Nutzer eingeben darf
     * @return der ausgewählte Menüpunkt
     */
    public int menuPunktAuswaehlen(int min, int max) {
        log.debug("UiController-menuPunktAuswaehlen");
        int auswahl = zahlenEingabe();
        while (auswahl < min || auswahl > max) {
            UIView.falscheEingabe();
            auswahl = zahlenEingabe();
        }
        return auswahl;
    }
    /**
     * Laesst den Spieler eine Zahl über die Tastatur eingeben.
     * Falls keine Zahl eingegeben, wird der Spieler solange erneut aufgefordert, bis er eine Zahl eingibt.
     * @return eingabe Die eingelesene Zahl
     * @throws NumberFormatException
     */
    public int zahlenEingabe() {
        log.debug("UiController-zahlenEingabe");
        int eingabe = 0;
        boolean fehler = false;
        do {
            try {

                eingabe = Integer.parseInt(scanner.nextLine());
                fehler = false;
                break;
            } catch (NumberFormatException e) {
                UIView.falscheEingabe();
                fehler = true;
            }
        } while(fehler);

        return eingabe;
    }
    public void beenden (){
        log.debug("UI Controller beenden");
        System.exit(0);
    }
    private Spiel loadGame() {
        //TODO:
        return spiel;
    }
    private Spiel saveGame() {
        //TODO:
        return spiel;
    }
    private Spiel neuesSpielErzeugen() {
        //TODO:
        log.debug("UI Controller SpielErzeugen");
        spiel = new Spiel();
        spielerNamen = new ArrayList<>();
        List<Spieler> spielerList = new ArrayList<>();
        List<Karte> ersteZiehkarte = new ArrayList<>();

        spiel.setSpielrunde(0);
        int anzahlSpieler = anzahlSpieler();

        for (int i = 0; i<= anzahlSpieler-1; i++){
            UIView.nameNeuerSpielerMitIndex(i+1);
            spielerNamen.add(menschlichenSpielerHinzufuegen());
        }
        spielerNamen = kiSpielerHinzufuegen(spielerNamen);
        for (String name : spielerNamen){
            Spieler neuerSppieler = spielerService.anlegenSpieler(name);
            if(name.contains("KI-Spieler"))
                neuerSppieler.setVirtuellerSpieler(true);
            spielerList.add(neuerSppieler);
        }
        spiel.setSpielerListe(spielerList);
        spiel.setAktiverSpieler(spiel.getSpielerListe().get(0));
        spiel.setZiehstapel(kartenService.anlegenStapel(anzahlKarten));
        ersteZiehkarte.add(spiel.getZiehstapel().get(spiel.getZiehstapel().size()-1));
        spiel.getZiehstapel().remove(spiel.getZiehstapel().size()-1);
        spiel.setAblegestapel(ersteZiehkarte);
        spiel.setZiehstapel(spielService.fuenfKartenAusteilen(spiel.getZiehstapel(),spielerList));
        spielService.getRegelnwerk();
        spiel.setId(spielID);
        UIView.anzeigeSpielID(spielID.toString());
        return spiel;
    }
    private int anzahlSpieler(){
        log.debug("UiController-anzahlSpieler");
        UIView.spielerAnzahl();
        int anzahl = zahlenEingabe();
        while (anzahl < 1 || anzahl > 5) {
            UIView.spielerAnzahlFehler();
            anzahl = zahlenEingabe();
        }
        return anzahl;
    }
}
