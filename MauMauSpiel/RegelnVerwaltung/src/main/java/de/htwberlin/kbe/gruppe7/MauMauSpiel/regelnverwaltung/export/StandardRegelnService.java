package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Farbe;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Karte;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.KarteNichtGefundenException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.entity.Wert;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.entity.Spieler;

public interface StandardRegelnService {

    /**
     * Prueft, ob der Spieler Mau gesagt hat
     * @param spieler der Spieler ist dran
     * @return false, wenn der Spieler Mau gesagt hat
     */
    boolean spielerMauNichtGesagt(Spieler spieler);

    /**
     * Prueft, ob der Spieler Mau Mau gesagt hat
     * @param spieler der Spieler ist dran
     * @return false, wenn der Spieler Mau Mau gesagt hat
     */
    boolean spielerMauMauNichtGesagt(Spieler spieler);

}
