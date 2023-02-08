package de.htwberlin.kbe.gruppe7.MauMauSpiel.konfiguration;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.export.KIService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.KIService.impl.KIServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl.KartenServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl.SondereRegelnServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl.StandardRegelnServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.impl.SpielerServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.impl.SpielServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.export.UIControllerService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.impl.UIControllerImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.view.UIView;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.Characteristics;
import org.picocontainer.injectors.ConstructorInjection;

public class Konfig {
    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    public static void main(String[] arg) {
        registriereKomponenten();
        container.getComponent(UIControllerService.class).run();
    }



    private static void registriereKomponenten() {
        container.addComponent(KartenServiceImpl.class);
        container.addComponent(SpielerServiceImpl.class);
        container.addComponent(StandardRegelnServiceImpl.class);
        container.addComponent(SondereRegelnServiceImpl.class);
        container.addComponent(KIServiceImpl.class);
        container.addComponent(UIView.class);
        container.addComponent(UIControllerImpl.class);
        container.as(Characteristics.USE_NAMES).addComponent(SpielServiceImpl.class);
    }
}
