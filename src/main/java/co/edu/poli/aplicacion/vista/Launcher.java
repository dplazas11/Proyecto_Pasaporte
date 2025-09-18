package co.edu.poli.aplicacion.vista;

import co.edu.poli.aplicacion.modelo.Ciudad;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {
        //App.main(args);
        
        //Ejemplo de uso patron builder 
        ArrayList <Ciudad> ciudades = new ArrayList();
        Titular titular = new Titular("123", "Juan Perez", "25-03-2005");
        Ciudad ciudad = new Ciudad("01", "bogota");
        ciudades.add(ciudad);
        Pais pais = new Pais("CO", "Colombia",ciudades);
        ElementoSeguridad elem = new ElementoSeguridad("A01", "ALFA", "Seguridad privada");

        PasaporteOrdinario pasaporte = new PasaporteOrdinario.Builder()
                .id("P001")
                .fechaExp("2025-09-15")
                .titular(titular)
                .pais(pais)
                .elemSeguridad(elem)
                .motivoViaje("Turismo")
                .build();

        System.out.println(pasaporte);
        
        //Ejemplo de uso patron prototype 
        Titular original = new Titular("123", "Juan Perez", "06-04-2001");

        // Clonamos con Prototype
        Titular copia = original.clone();

        // Modificamos la copia
        copia.setNombre("Pedro Gomez");

        System.out.println("Original: " + original);
        System.out.println("Copia: " + copia);
    }
}
