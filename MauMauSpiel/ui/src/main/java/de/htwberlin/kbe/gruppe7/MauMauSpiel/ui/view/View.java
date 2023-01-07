package de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.view;


import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;

import java.util.ArrayList;

/**
 * Die Klasse implementiert UI für das Spiel Mau-Mau.
 */
public class View {

    /**
     * Methode printet in der Console eine Linie.
     */
    public void leereZeile() {
        System.out.println("_____________________________");
    }

    /**
     * Methode zeigt die Menüpunkte an.
     */
    public void willkommen() {
        System.out.println("[Hauptmenü]\nWillkommen im Mau-Mau Spiel!" +
                "\nGebe die Zahl vor der Auswahl ein um fortzufahren:" +
                "\n[1] Neues Spiel starten" +
                "\n[2] Spiel fortsetzen" +
                "\n[3] Beenden" +
                "\nDeine Eingabe:");
    }

    /**
     * Methode fragt den Spieler in der Konsole, ob er die Regeln schon kennt.
     */
    public void spielenOderRegelnAnzeigen() {
        System.out.println("Dann lass uns spielen! Kennst du die Regeln schon oder spielst du Mau-Mau zum ersten Mal?" +
                "\n[1] Ich kenne die Regeln" +
                "\n[2] Bitte zeige die Spielregeln an" +
                "\nDeine Eingabe:");
    }

    /**
     * Methode informiert die Spieler über die Spielregeln.
     */
    public void regelnAnzeigen() {
        System.out.println("[Mau-Mau Spielregeln]" +
                "\nBei Mau-Mau musst du möglichst schnell alle deine Karten Stück für Stück ablegen.\n" +
                "Farbe auf gleiche Farbe und Zahlen auf gleiche Zahlen.\n\n" +
                "Wichtig: Wenn Du nur noch eine Karte hast must Du 'Mau' sagen. Ansonsten bekommst Du zwei Strafkarten aufgebrummt.\n" +
                "Gespielt wird normalerweise mit einem Blatt aus 32 Karten. In unserem Spiel darfst du aber aussuchen,\n" +
                "mit wie vielen Karten gespielt wird.\n" +
                "So oder so werden am Rundenbeginn allen Spielern fünf Handkarten ausgeteilt und die übrigen\n" +
                "Mau-Mau-Karten zum Nachziehen als Talon beiseitegelegt.\n" +
                "Mau-Mau ist ein relativ rasantes Kartenspiel und besteht nur aus einer einzigen Spielphase, dem Ablegen.\n" +
                "Der grobe Ablauf einfach erklärt: Eine Karte wird vom Stapel aufgedeckt und daneben gelegt.\n" +
                "Nun ist der Startspieler am Zug und muss eine passende Karte ablegen.\n" +
                "Dann geht es im Uhrzeigersinn weiter. Jeder muss in seinem Zug eine Karte ablegen, die zu der obersten\n" +
                "aufgedeckten Karte passt. Und wer zuerst alle Handkarten loswird, hat gewonnen.\n\n" +
                "Keine passende Handkarte?\nIn der Regel muss deine Karte die gleiche Kartenfarbe oder den gleichen\n" +
                "Rang wie die oberste offen liegende Karte haben. Kannst du nicht legen, geht dein Zug weiter:\n" +
                "Du musst eine Karte vom Stapel ziehen. Wenn diese passt, kannst du sie noch ablegen.\n" +
                "Wenn nicht, ist der nächste Spieler an der Reihe.\n\n" +
                "Alles, was du bisher gelesen hast, waren die Grundregeln. Ähnlich wie beim Rommé scheint\n" +
                "es bei Mau-Mau in jeder Stadt oder sogar jeder Familie leicht andere Regeln zu geben. Damit du so\n" +
                "spielen kannst, wie du es gewöhnt bist, bieten wir dir neben den grundlegenden Mau-Mau-Spielregeln\n" +
                "folgende Sonderregeln an.\n\n" +
                "[Bube-Regel]\nEin Bube kann ohne Bedienen auf jede Karte außer Buben gespielt werden.\n" +
                "Wer einen Buben legt, darf sich eine Kartenfarbe wünschen, die als nächste bedient werden muss.\n" +
                "Die Farbe muss NICHT der Farbe des abgelegten Buben entsprechen. Der Wunsch bleibt solange bestehen,\n" +
                "bis er erfüllt wurde. Im Grundspiel gilt „Bube auf Bube stinkt“. Das heißt, ein Bube kann einen anderen nicht aushebeln.\n\n" +
                "[Acht-Regel]\n...\n\n" +
                "[Neun-Regel]\n...\n\n" +
                "Du siehst, Mau-Mau bleibt immer spannend und schnell und hat dabei noch so viele Mau-Mau-Varianten zu bieten!\n" +
                "Ob original Mau-Mau-Regeln oder Sonderregeln – du entscheidest, an welchen Tisch du gehst.");
    }

    /**
     * Methode fragt den Spieler, nach welchen Regeln er spielen möchte.
     */
    public void frageRegelwerk() {
        System.out.println("Nach welchen Regeln möchtest du nun spielen?\n" +
                "Bitte wähle: \n[1] Standardregeln \n[2] Erweiterte Regeln\n" +
                "Deine Eingabe:");
    }

    /**
     * Methode informiert den Spieler, dass erweiterte Regel für das Spiel ausgewählt wurden.
     */
    public void zeigeAuswahlRegelErweitert() {
        System.out.println("Du hast die erweiterte Regel für das Spiel ausgewählt.");
    }

    /**
     * Methode informiert den Spieler, dass Standardregel für das Spiel ausgewählt wurden.
     */
    public void zeigeAuswahlRegelStandard() {
        System.out.println("Du hast die Standardregel für das Spiel ausgewählt.");
    }

    /**
     * Methode fragt den Spieler, ob das Spiel gestartet werden soll.
     */
    public void bereitZumSpielen() {
        System.out.println("\nBist du jetzt bereit zum Spielen? Tippe bitte 1 oder 2 ein.\n[1] ja \n[2] nein" +
                "\nDeine Eingabe:");
    }

    /**
     * Methode informiert die Spieler, dass das Spiel gleich gestartet wird.
     */
    public void spielWirdGestartet() {
        System.out.println("Jetzt bist du bereit für ein paar Runden Mau-Mau!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bereite das Spiel vor...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lade Zuschauer ein...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mische Kartenstapel...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Neues Spiel wird gestartet.");
    }

    /**
     * Methode informiert die Spieler über die Ende des Spiels.
     */
    public void ende() {
        System.out.println("\n Das Spiel wurde beendet. Vielen Dank fürs spielen und schau bald wieder vorbei. Bis bald!");
    }

    /**
     * Methode fragt nach der Spieleranzahl.
     */
    public void spielerAnzahl() {
        System.out.println("Neues Spiel gestartet... \n Im Mau-Mau-Spiel kannst du zwischen den beliebtesten Varianten von zwei bis fünf Spielern " +
                "wählen. Dabei gelten immer die gleichen Mau-Mau-Regeln.\nWie viele Spieler nehmen heute an einem Tisch " +
                "platz? Tippe eine Zahl ein und bestätige deine Eingabe mit ENTER.\nDeine Eingabe:");
    }

    /**
     * Methode informiert Spieler über die falsch eingegebene Spieleranzahl.
     */
    public void spielerAnzahlFehler() {
        System.out.println("Fehler: Es dürfen maximal nur 5 Spieler mitspielen!\n" +
                "Bitte gib die Anzahl der gewünschten Teilnehmer nochmal ein:");
    }

    /**
     * Die Methode fragt den Spieler nach dem Namen.
     *
     * @param i index
     */
    public void nameNeuerSpielerMitIndex(int i) {
        System.out.println("[Spieler " + i + "]\nWie ist dein Name? Bitte gib ihn jetzt ein:");
    }

    public void spielerWillkommen(String name) {
        System.out.println("Willkommen in Spiel, " + name + "!");
    }

    /**
     * Methode informiert die Spieler, dass "Mau" gesagt wurde.
     *
     * @param name Name des Spielers, der eine Karte gezogen hat
     */
    public void mauGesagt(String name) {
        System.out.println("Der Spieler " + name + " hat Mau gesagt!");
    }

    /**
     * Methode informiert die Spieler, dass "Mau" nicht gesagt wurde
     *
     * @param name Name des Spielers, der vergessen hat "Mau" zu sagen
     */
    public void mauVergessen(String name) {
        System.out.println("Oh nein! Der Spieler " + name + " hat vergessen 'Mau' zu sagen und muss jetzt 2 Strafkarten ziehen.");
    }


    /**
     * Methode informiert die Spieler, dass eine Karte gezogen wurde.
     *
     * @param name Name des Spielers, der eine Karte gezogen hat
     */
    public void karteGezogen(String name) {
        System.out.println(name + " hat eine Karte gezogen.");
    }

    /**
     * Methode fragt den Spieler, welche Karte er legen möchte.
     */
    public void welcheKarte() {
        System.out.println("Was möchtest du nun tun? Um eine Karte zu legen, gib bitte ihre Kartennummer ein. " +
                "\nWeitere verfügbare Optionen:" +
                "\n[100]    keine passende Karte auf der Hand, erstmal eine Karte aus dem Stapel ziehen" +
                "\n[200]    Eine Karte legen und 'Mau' sagen" +
                "\n[300]    Spiel beenden" +
                "\nDeine Eingabe:");
    }

    /**
     * Methode informiert die Spieler, dass Karten gezogen werden müssen.
     *
     * @param name Name des Spielers, der Karte gezogen hat
     */
    public void mussKartenZiehen(String name, int anzahlKarten, String grund) {
        System.out.println(name + " muss " + anzahlKarten + " Karten ziehen" + grund + ".");
    }

    /**
     * Methode informiert die Spieler, dass ein Spieler aussetzen muss.
     *
     * @param name Name des Spielers, der aussetzen muss
     */
    public void mussAussetzen(String name) {
        System.out.println(name + " muss eine Runde aussetzen.");
    }

    /**
     * Methode zeigt alle Karten, die der Spieler auf der Hand hat und die oberste Karte des Ablegestapels.
     */
    public void kartenInfosAnzeigen(String obersteKarte, ArrayList<String> karten, String spielerName) {
        System.out.println("[Spiel]\n" + spielerName + " ist an der Reihe!");
        System.out.println("Dies ist die oberste Karte des Ablegestapels: " + obersteKarte + ".\nDiese " +
                "Karten hast du auf der Hand:");
        for (String karte : karten) {
            System.out.println(karte);
        }
    }

    /**
     * Methode informiert über den bevorstehenden Spielzug der KI.
     */
    public void kiSpielt(String obersteKarte, String spielerName) {
        System.out.println("[Spiel]\n" + spielerName + " ist an der Reihe!");
        System.out.println("Dies ist die oberste Karte des Ablegestapels: " + obersteKarte + ".");
    }

    /**
     * Methode informiert über den bevorstehenden Spielzug der KI.
     */
    public void kiHatGespielt(String gelegteKarte, String spielerName) {
        System.out.println("...\n" +
                "...\n" +
                spielerName + " hat die Karte " + gelegteKarte + " gelegt. \n Weiter gehts... \n");
        ;
    }

    /**
     * Die Methode informiert den Spieler über die falsche Eingabe in der Konsole während des Spiels.
     */
    public void falscheEingabe() {
        System.out.println("Fehler: Falsche Eingabe! Du hast eine Eingabe getätigt, die keinen Sinn ergibt. Bitte " +
                "wähle und tippe eine Zahl über die Tastatur ein und bestätige mit ENTER.\nDeine Eingabe:");
    }

    /**
     * Die Methode informiert den Spieler über die falsche Eingabe in der Konsole während des Spiels.
     */
    public void karteExistiertNicht() {
        System.out.println("Das war eine falsche Eingabe. Bitte versuche es erneut, waehle eine Karte deiner Hand" +
                "und gib ihre Nummer (steht in Klammern) ein.\nDeine Eingabe:");
    }


    /**
     * Die Methode informiert den Spieler darüber, dass die Karte nicht gelegt werden darf.
     */
    public void karteNichtGueltig() {
        System.out.println("Die Karte kannst du nicht auf dem Stapel ablegen. " +
                "Bitte versuche es erneut und waehle eine andere Karte.\nDeine Eingabe:");
    }

    /**
     * Bittet den Spieler, nach Ablage eines Buben sich eine Spielfarbe zu wünschen.
     */
    public void neueSpielfarbeAbfragen() {
        System.out.println("Glückwunsch, du hast einen Buben gelegt! Damit darfst du dir nun eine neue Spielfarbe wünschen:\n" +
                "Zur Auswahl stehen:\n[1] Herz\n[2] Kreuz\n[3] Pik\n[4] Karo\n" +
                "Bitte gebe die Nummer der neuen Spielfarbe nun ein: ");
    }

    /**
     * Informiert den Spieler nach Ablage eines Buben über die ausgewählte Spielfarbe.
     *
     * @param spielerName    Spieler, der einen Buben abgelegt hat und neue Farbe ausgewählt hat
     * @param neueSpielfarbe ausgewählte neue Farbe
     */
    public void neueSpielfarbeInfo(String spielerName, Farbe neueSpielfarbe) {
        System.out.println(spielerName + " hat eine neue Farbe ausgewählt: " + neueSpielfarbe + ".");
    }

    /**
     * Methode informiert die Spieler, wer gewonnen hat.
     *
     * @param name Name des Gewinners
     */
    public void gewonnen(String name) {
        System.out.println("Diesmal hat " + name + " gewonnen!");
    }

    /**
     * Methode informiert die Spieler, dass ein Spieler Mau gesagt hat.
     *
     * @param name Name des Spielers der Mau gesagt hat
     */
    public void spielerMauSagen(String name) {
        System.out.println(name + " hat Mau gesagt!");
    }

    /**
     * Methode informiert den Spieler, dass er nach dem Mausagen noch eine Karte legen darf.
     */
    public void karteLegenNachMau() {
        System.out.println("Du hast Mau gesagt und darfst nun noch eine Karte legen:");
    }

    /**
     * Methode informiert den Spieler, der faelschlicherweise Mau gesagt hat, dass er zwei Strafkarten ziehen muss.
     *
     * @param name Name des Spielers der Mau gesagt hat
     */
    public void spielerFalschMauGesagt(String name) {
        System.out.println(name + " du musst zwei Karten ziehen, da du mehr als eine Karte auf der Hand hast und Mau gesagt hast!");
    }

    /**
     * Methode informiert die Spieler, dass Kartenstapel gemischt wurde.
     */
    public void kartenStapelGemischt() {
        System.out.println("Kartenstapel wurde erfolgreich gemischt.");
    }

    /**
     * Methode zeigt für Spieler auf der Konsole an, was für eine Karte der (anderer) Spieler gelegt hat.
     *
     * @param spielername Spieler, der die Karte gelegt hat
     * @param karte       die abgelegte Karte
     */
    public void karteGelegt(String spielername, Karte karte) {
        System.out.println("[Zug] " + spielername + " hat " + karte + " gelegt.");
    }

    public void frageNachSpielID() {
        System.out.println("Du möchtest ein altes Spiel fortführen. Gib bitte nun die Spiel-ID des alten Spiels ein: ");
    }

    /**
     * Die Methode fragt, wie viele KI Spieler dem Spiel hinzugefügt werden sollen.
     */
    public void spielErfolgreichGespeichert() {
        System.out.println("Autosaved");
    }

    public void keinSpielGefunden(String lastID) {
        System.out.println("Unter dieser Spiel-ID konnte kein Spiel gefunden werden, bitte gib diese erneut ein. \n" +
                "Die letzte bekannte Spiel ID in der Datenbank lautet: " + lastID);
    }

    public void keinSpielVorhanden() {
        System.out.println("In der Datenbank befindet sich noch kein Spiel. Bitte starte das Spiel erneut und wähle zu Beginn neues Spiel starten.");
    }


    public void spielfortsetzungUnmöglich() {
        System.out.println("Dieses Spiel kann nicht fortgesetzt werden. Es wurde bereits zu Ende gespielt!");
    }

    public void anzeigeSpielID(String spielid) {
        System.out.println("Die aktuelle Spiel-ID lautet: " + spielid);
    }

    /**
            * Die Methode informiert den Spieler darüber, dass das Spiel nun abgebrochen wird.
     */
    public void spielWirdAbgebrochen(Long spielid) {
        System.out.println("Das Spiel wird nun wie gewünscht abgebrochen. \nMit deiner Spiel-ID " + spielid +
                " kannst du das Spiel beim nächsten Mal einfach fortsetzen.");
    }

    /**
            * Die Methode fragt, wie viele KI Spieler dem Spiel hinzugefügt werden sollen.
     */
    public void frageAnzahlKI() {
        System.out.println("Möchtest du gegen virtuelle Spieler spielen? Du kannst maximal gegen 4 virtuelle Spieler spielen. Gebe einen numerischen Wert von 0-4 ein: ");
    }

    /**
            * Die Methode zeigt an, dass bereits zu viele reale Spieler am Spiel teilnehmen.
            */
    public void keineKISpielerMoeglich() {
        System.out.println("Damit habt ihr die maximale Anzahl an Spielern bereits erreicht. Virtuelle Spieler können nicht mehr am Spiel teilnehmen.");
    }

    /**
            * Die Methode zeigt an, dass mindestens ein KI Spieler am Spiel teilnehmen muss, damit gespielt werden kann.
     */
    public void kISpielerErforderlich() {
        System.out.println("Da du alleine spielst, musst du mindestens gegen einen virtuellen Spieler spielen. Bitte korrigiere deine Eingabe: ");
    }

    /**
     * Die Methode zeigt an, dass bereits zu viele reale Spieler am Spiel teilnehmen.
     */
    public void kiHatKarteGezogen(String kiName) {
        System.out.println("Virtueller Spieler " + kiName + " musste eine Karte ziehen.");
    }
}
