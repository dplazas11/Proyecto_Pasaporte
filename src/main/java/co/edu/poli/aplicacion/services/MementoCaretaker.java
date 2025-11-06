package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;

public class MementoCaretaker {

    private List<MementoPasaporte> historial = new ArrayList<>();

    public int agregarMemento(MementoPasaporte m) {
        historial.add(m);
        return historial.indexOf(m);
    }

    public MementoPasaporte obtenerMemento(int indice) {
        return historial.get(indice);
    }

    public String verElementos() {
        String elementos = "";
        for (MementoPasaporte mp : historial) {
            elementos = elementos + historial.indexOf(mp) + ") " + mp.toString() + "\n";
        }
        return elementos;
    }
}
