
package co.edu.poli.aplicacion.modelo;

public class ElementoSeguridad {
    
    private String id;
    private String tipo;
    private String descripcion;

    public ElementoSeguridad(String id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ElementoSeguridad{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }
    
}
