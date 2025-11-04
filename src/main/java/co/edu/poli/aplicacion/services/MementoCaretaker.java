
package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;


public class MementoCaretaker {
     private List<MementoPasaporte> historial = new ArrayList<>();

    public void agregarMemento(MementoPasaporte m) {
        historial.add(m);
    }

    public MementoPasaporte obtenerMemento(int indice) {
        return historial.get(indice);
    }
    
}
