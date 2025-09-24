package co.edu.poli.aplicacion.vista;

import co.edu.poli.aplicacion.modelo.Ciudad;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;
import co.edu.poli.aplicacion.services.BuilderPOWrapper;
import co.edu.poli.aplicacion.services.PrototypeTitularWrapper;

import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) throws CloneNotSupportedException {
        App.main(args); // Llama al main de tu clase App
        
        /*

        ArrayList<Ciudad> ciudades = new ArrayList();
        Titular titular = new Titular("123", "Juan Perez", "25-03-2005");
        Ciudad ciudad = new Ciudad("01", "bogota");
        ciudades.add(ciudad);
        Pais pais = new Pais("CO", "Colombia", ciudades);
        ElementoSeguridad elem = new ElementoSeguridad("A01", "ALFA", "Seguridad privada");

        PasaporteOrdinario pasaporte = new BuilderPOWrapper.Builder()
                .setId("P001")
                .setFechaExp("2025-09-15")
                .setTitular(titular)
                .setPais(pais)
                .setElemSeguridad(elem)
                .setMotivoViaje("Turismo")
                .build();

        System.out.println(pasaporte);
        //Ejemplo de uso patron prototype 
        PrototypeTitularWrapper originalPrototype = new PrototypeTitularWrapper(titular);

        // Clonamos con Prototype
        Titular copia = originalPrototype.clone();

        System.out.println("Original: " + originalPrototype);
        System.out.println("Copia: " + copia);

    }*/
    }
}
