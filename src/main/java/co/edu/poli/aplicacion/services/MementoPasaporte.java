
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.Titular;


public class MementoPasaporte {
    private String id;
    private String fechaExp;
    private Titular titular;
    private Pais pais;
    private ElementoSeguridad elemSeguridad;
    private String mision;
    private String motivoViaje;

    public MementoPasaporte(String id, String fechaExp, Titular titular, Pais pais, ElementoSeguridad elemSeguridad, String mision, String motivoViaje) {
        this.id = id;
        this.fechaExp = fechaExp;
        this.titular = titular;
        this.pais = pais;
        this.elemSeguridad = elemSeguridad;
        this.mision = mision;
        this.motivoViaje = motivoViaje;
    }

    public String getId() {
        return id;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public Titular getTitular() {
        return titular;
    }

    public Pais getPais() {
        return pais;
    }

    public ElementoSeguridad getElemSeguridad() {
        return elemSeguridad;
    }

    public String getMision() {
        return mision;
    }

    public String getMotivoViaje() {
        return motivoViaje;
    }

    
    
   
    
    
}
