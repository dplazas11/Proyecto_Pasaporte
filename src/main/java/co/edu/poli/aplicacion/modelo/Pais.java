
package co.edu.poli.aplicacion.modelo;

import java.util.ArrayList;

public class Pais {

    private String codigo;
    private String nombre;
    private ArrayList<Ciudad> ciudad;

    public Pais(String codigo, String nombre, ArrayList<Ciudad> ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ciudad> getCiudad() {
        return ciudad;
    }

    public void setCiudad(ArrayList<Ciudad> ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "(Codigo: " + codigo + ", Nombre: " + nombre + ", Ciudad: " + ciudad + ')';
    }

}
