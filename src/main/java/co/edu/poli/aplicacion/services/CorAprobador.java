
package co.edu.poli.aplicacion.services;

import java.util.Map;

public abstract class CorAprobador {
    
    protected CorAprobador siguiente;

    public void setSiguiente(CorAprobador siguiente) {
        this.siguiente = siguiente;
    }
    
    public abstract Map<String, Object> aprobar(String idTitular);
    
}
