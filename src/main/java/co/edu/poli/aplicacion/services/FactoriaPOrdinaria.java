
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;


public class FactoriaPOrdinaria implements CreadorPasaporte {

    @Override
    public Pasaporte CrearPasaporte() {
        return new PasaporteOrdinario();
    }

  
}
