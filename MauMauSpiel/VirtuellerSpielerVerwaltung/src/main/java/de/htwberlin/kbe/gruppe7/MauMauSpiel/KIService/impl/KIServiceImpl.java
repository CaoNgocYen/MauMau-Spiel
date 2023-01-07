package de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.impl;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KIServiceImpl implements KIService {
    private static Logger log = LogManager.getLogger();
    private static Random rand = new Random();
    /**
     * Methode erzeugt einen randomen Namen für KI-Spieler.
     * Ursprüngliche Code-Quelle der Methode: https://jvm-gaming.org/t/super-simple-name-generator/53855
     *
     * @return String der generierten Namen
     */
    @Override
    public String kiNamenGenerieren() {
        log.debug("KiVerwaltungImpl-kiNamenGenerieren");
        // Idee/Quelle: https://jvm-gaming.org/t/super-simple-name-generator/53855
        String[] beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru", "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer",
                "Jar", "Mjol", "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro", "Mar", "Luk" };
        String[] middle = { "air", "ir", "mi", "sor", "mee", "clo", "red", "cra", "ark", "arc", "miri", "lori",
                "cres", "mur", "zer", "marac", "zoir", "slamar", "salmar", "urak" };
        String[] end = { "d", "ed", "ark", "arc", "es", "er", "der", "tron", "med", "ure", "zur", "cred", "mur" };

        String generatedName = "KI-Spieler " + beginning[rand.nextInt(beginning.length)] + middle[rand.nextInt(middle.length)] +
                end[rand.nextInt(end.length)];
        return generatedName;
    }

    /**
     * Methode entscheidet zufällig für den KI-Spieler, welche Farbe er für sich wünscht.
     *
     * @return gewuenschte Farbe
     */
    @Override
    public Farbe kiWuenschtFarbe() {
        log.debug("KiVerwaltungImpl-kiWuenschtFarbe");

        Farbe gewuenschteFarbe = null;
        int zahl = rand.nextInt(4);
        switch(zahl) {
            case 0:
                gewuenschteFarbe = Farbe.HERZ;
                break;
            case 1:
                gewuenschteFarbe = Farbe.PIK;
                break;
            case 2:
                gewuenschteFarbe = Farbe.KARO;
                break;
            case 3:
                gewuenschteFarbe = Farbe.KREUZ;
                break;
        }
        return gewuenschteFarbe;
    }

    /**
     * Methode prüft, ob KI-Spieler "Mau" sagen muss und tut das auch richtig in allen Fällen.
     * (d.h. in unserem Fall "vergisst" KI-Spieler nie "Mau" zu sagen)
     *
     * @param spieler Spieler, der geprüft werden soll
     * @return boolescher Wert, der angibt, ob der KI-Spieler "Mau" gesagt hat
     */
    @Override
    public boolean kiMauPruefung(Spieler spieler) {
        log.debug("KiVerwaltungImpl-kiMauPruefung");

        if (spieler.getSpielerStapel().size() == 1) {
            return true;
        }
        return false;
    }
}
