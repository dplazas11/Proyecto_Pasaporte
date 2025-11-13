
package co.edu.poli.aplicacion.services;

public class EstadoRevision implements Estado{

    @Override
    public void avanzar(AdaptadorPais automata) {
        automata.realizarTransicion(this);
    }

    @Override
    public String nombre() {
        return "estado revision";
    
    }
    
}
