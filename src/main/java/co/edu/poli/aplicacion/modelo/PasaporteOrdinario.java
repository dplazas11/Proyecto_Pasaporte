package co.edu.poli.aplicacion.modelo;

public class PasaporteOrdinario extends Pasaporte {

    private String motivoViaje;

    
    public PasaporteOrdinario(){
        super();
    }
    public PasaporteOrdinario(String id, String fechaExp, Titular titular, Pais pais, ElementoSeguridad elemseguridad, String motivoViaje) {
        super(id, fechaExp, titular, pais, elemseguridad);
        this.motivoViaje = motivoViaje;
    }

    public String getMotivoViaje() {
        return motivoViaje;
    }

    public void setMotivoViaje(String motivoViaje) {
        this.motivoViaje = motivoViaje;
    }

    @Override
    public String toString() {
        return "PasaporteOrdinario{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", elemento seguridad=" + (getElemSeguridad()!= null ? getElemSeguridad(): "null")                
                + ", motivoDeViaje=" + motivoViaje
                + '}';
    }

}
