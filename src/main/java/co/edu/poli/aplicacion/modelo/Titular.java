
package co.edu.poli.aplicacion.modelo;

import java.io.*;
import java.util.*;


public class Titular {

    private String id;
    private String nombre;
    private String fechaNac;

    public Titular(String id, String nombre, String fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "(id: " + id + ", Nombre: " + nombre + ", Fecha Nac: " + fechaNac + ')';
    }

}
