package de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl;

/**
 *
 * @author Sergey Orlov (s0571777)
 * @author Yen Ngoc Cao  (s0577979)
 * @author Abduqani Ibrahim (s0583541)
 * @version 1.0
 *  *
 */

import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.StandardRegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.export.StandardRegelnService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.entity.Spieler;

public class StandardRegelnServiceImpl implements StandardRegelnService {



    @Override
    public boolean spielerMauNichtGesagt(Spieler spieler) {
        if (spieler.getSpielerStapel().size() == 2 && spieler.isGesagtMau()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean spielerMauMauNichtGesagt(Spieler spieler) {
        if (spieler.getSpielerStapel().size() == 1 && spieler.isGesagtMauMau()) {
            return false;
        }

        return true;
    }

}
