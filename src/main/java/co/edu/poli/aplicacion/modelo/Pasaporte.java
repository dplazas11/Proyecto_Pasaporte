
package co.edu.poli.aplicacion.modelo;

public abstract class Pasaporte {

    private String id;
    private String fechaExp;
    private Titular titular;
    private Pais pais;
    private ElementoSeguridad elemSeguridad;
    
    
    public Pasaporte() {}

    public Pasaporte(String id, String fechaExp, Titular titular, Pais pais, ElementoSeguridad elemSeguridad) {
        this.id = id;
        this.fechaExp = fechaExp;
        this.titular = titular;
        this.pais = pais;
        this.elemSeguridad = elemSeguridad;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public ElementoSeguridad getElemSeguridad() {
        return elemSeguridad;
    }

    public void setElemSeguridad(ElementoSeguridad elemSeguridad) {
        this.elemSeguridad = elemSeguridad;
    }

    
    

    @Override
    public String toString() {
        return "Pasaporte:{" + "id: " + id + ", Fecha Exp: " + fechaExp + ", Titular:" + titular + ", Pais:" + pais + '}';
    }

}

