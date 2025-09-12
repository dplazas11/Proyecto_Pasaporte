
package co.edu.poli.aplicacion.repositorio;

import java.util.ArrayList;

public interface Filtro <T> extends Operacion<T>{
    ArrayList<T> filterId(String ident);
    
}
