package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.DAO;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;

public interface SpielDao {
    /**
     * findet und lädt das Spiel anhand der Spiel-ID
     * @param id id des Spiels
     * @return Spiel, das gefunden wurde
     */
    Spiel findById(Long id);

    /**
     * zählt die Anzahl der gefundenen Spiele
     * @return true wenn ein Spiel in der Datenbank gefunden wurde, false wenn nicht
     */
    boolean findSpiel();
    /**
     * speichert das Spiel aus der Datenbank
     * @param spiel zu speicherndes Spiel
     */
    void saveSpiel(Spiel spiel);

    /**
     * löscht das Spiel aus der Datenbank
     * @param spiel zu löschendes Spiel
     */
    void deleteSpiel(Spiel spiel);

}

