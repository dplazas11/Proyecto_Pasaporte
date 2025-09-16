package co.edu.poli.aplicacion.modelo;

public class PasaporteDiplomatico extends Pasaporte {

    private String mision;

    public PasaporteDiplomatico() {
        super();
    }

    public PasaporteDiplomatico(String id, String fechaExp, Titular titular, Pais pais, String Mision) {
        super(id, fechaExp, titular, pais);
        this.mision = Mision;
    }

    @Override
    public String toString() {
        return "PasaporteDiplomatico{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", mision=" + mision
                + '}';
    }

    

    public String getMision() {
        return mision;
    }

    public void setMision(String Mision) {
        this.mision= Mision;
    }

}
