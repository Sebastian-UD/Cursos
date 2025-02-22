package gm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

//@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        // Configurar el modo oscuro
        FlatDarculaLaf.setup();
        // Crear instancia de la fabrica de Spring
        ConfigurableApplicationContext contextoSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class) //Clase que tiene la configuracion del contenedor Spring
                        .headless(false) // Indica que no es una aplicación web headless
                        .web(WebApplicationType.NONE) // Define que no es una aplicación web
                        .run(args);
        // Crear un objeto de Swing
        SwingUtilities.invokeLater(()->{
            ZonaFitForma zonaFitForma = contextoSpring.getBean(ZonaFitForma.class);
            zonaFitForma.setVisible(true);
        });
    }
}
