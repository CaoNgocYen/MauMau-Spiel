package de.htwberlin.kbe.gruppe7.MauMauSpiel.exceptionservice;

public class TechnischeExeption extends Exception{

    public TechnischeExeption() {
    }

    public TechnischeExeption (String message){
        super("Es ist eine Fehler aufgetreten, bitte informiere Entwickler "+ message);
    }
}
