package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;

public class MementoPasaporte {

    private String id;
    private String fechaExp;
    private Titular titular;
    private Pais pais;
    private ElementoSeguridad elemSeguridad;
    private String mision;
    private String motivoViaje;

    public MementoPasaporte(Pasaporte pasport) {
        this.id = pasport.getId();
        this.fechaExp = pasport.getFechaExp();
        this.titular = pasport.getTitular();
        this.pais = pasport.getPais();
        this.elemSeguridad = pasport.getElemSeguridad();
        if (pasport instanceof PasaporteDiplomatico) {
            this.mision = ((PasaporteDiplomatico) pasport).getMision();
        } else if (pasport instanceof PasaporteOrdinario) {

            this.motivoViaje = ((PasaporteOrdinario) pasport).getMotivoViaje();
        }
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

    @Override
    public String toString() {
        return  "id=" + id + ", fechaExp=" + fechaExp + ", titular=" + titular + ", pais=" + pais + ", elemSeguridad=" + elemSeguridad + ", mision=" + mision + ", motivoViaje=" + motivoViaje + '}';
    }
    
   

}
