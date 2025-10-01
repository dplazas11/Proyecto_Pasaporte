
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Ciudad;
import co.edu.poli.aplicacion.modelo.Titular;


public class PrototypeTitularWrapper implements Cloneable, InterfaceTitular {
    
    private final Titular titular;

    public PrototypeTitularWrapper(Titular titular) {
        this.titular = titular;
    }

    
    @Override
    public Titular clone() throws CloneNotSupportedException {
        
        Titular copia = new Titular(
            titular.getId(),
            titular.getNombre(),
            titular.getFechaNac()
        );
        return copia;
    }

    @Override
    public String getDescripcion() {
        return titular.getNombre();
       
    }

    
    
    
    
    
}
