
package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CorAprobador implements Suscriber{
    
    protected CorAprobador siguiente;
    protected ConcreteMediator mediator; 

    public CorAprobador() {
    }
        
    //Metodos Mediator    
    @Override
    public void setMediator(ConcreteMediator mediator){
        this.mediator = mediator;
    }
    
    public abstract String procesar(String mensaje);    
    
    
    //Metodos de chain of responsability
    public void setSiguiente(CorAprobador siguiente) {
        this.siguiente = siguiente;
    }
    
    public abstract Map<String, Object> aprobar(String idTitular);


    
    
}
