package de.htwberlin.kbe.gruppe7.MauMauSpiel.konfiguration;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.kartenverwaltung.impl.KartenServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.regelnverwaltung.impl.SondernRegelnServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielerverwaltung.impl.SpielerServiceImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.export.UIControllerService;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.impl.UIControllerImpl;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.ui.view.UIView;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.Characteristics;
import org.picocontainer.injectors.ConstructorInjection;

public class DependencyInjection {
    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    public static void main(String[] args) {
        registerComponents();
        container.getComponent(UIControllerService.class).run();
    }

    private static void registerComponents() {
        container.addComponent(SondernRegelnServiceImpl.class);
        container.addComponent(SpielerServiceImpl.class);
        container.addComponent(KartenServiceImpl.class);
        container.addComponent(UIView.class);
        container.addComponent(UIControllerImpl.class);
        container.as(Characteristics.USE_NAMES).addComponent(SpielerServiceImpl.class);
    }
}
