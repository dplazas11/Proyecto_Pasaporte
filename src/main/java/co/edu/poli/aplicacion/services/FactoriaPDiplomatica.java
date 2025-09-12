
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;


/**
 *
 * @author Pardo
 */
public class FactoriaPDiplomatica implements CreadorPasaporte {

    @Override
    public Pasaporte CrearPasaporte() {
        return new PasaporteDiplomatico();
    }
  
    
    
}

