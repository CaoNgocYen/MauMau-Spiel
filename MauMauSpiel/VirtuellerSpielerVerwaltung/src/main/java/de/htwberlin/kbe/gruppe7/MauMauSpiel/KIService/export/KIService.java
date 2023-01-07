package de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export;



import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.spielerverwaltung.entity.Spieler;

/**
 *  Die Schnittstelle enthält die Methoden für die Verwaltung der virtuellen Spieler (KI-Spieler).
 */
public interface KIService {

    /** Methode erzeugt einen randomen Namen für KI-Spieler.
     * Ursprüngliche Code-Quelle der Methode: https://jvm-gaming.org/t/super-simple-name-generator/53855
     * @return String der generierten Namen
     */
    public String kiNamenGenerieren();

    /** Methode entscheidet zufällig für den KI-Spieler, welche Farbe er für sich wünscht.
     * @return gewuenschte Farbe
     */
    public Farbe kiWuenschtFarbe();

    /** Methode prüft, ob KI-Spieler "Mau" sagen muss und tut das auch richtig in allen Fällen.
     * (d.h. in unserem Fall "vergisst" KI-Spieler nie "Mau" zu sagen)
     * @param spieler Spieler, der geprüft werden soll
     * @return boolescher Wert, der angibt, ob der KI-Spieler "Mau" gesagt hat
     */


    public boolean kiMauPruefung(Spieler spieler);
}

