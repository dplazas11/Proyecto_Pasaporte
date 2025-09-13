/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

public class PasaporteDiplomatico extends Pasaporte {

    private String tipoPasaporte;
    private String descripcion;

    public PasaporteDiplomatico() {
        super();
    }

    public PasaporteDiplomatico( String id, String fechaExp, Titular titular, Pais pais,String tipoPasaporte, String Mision) {
        super(id, fechaExp, titular, pais);
        this.tipoPasaporte = tipoPasaporte;
        this.descripcion = Mision;
    }

    @Override
    public String toString() {
        return "PasaporteDiplomatico{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", tipoPasaporte=" + tipoPasaporte
                + ", mision=" + descripcion
                + '}';
    }

    public String getTipoPasaporte() {
        return tipoPasaporte;
    }

    public void setTipoPasaporte(String tipoPasaporte) {
        this.tipoPasaporte = tipoPasaporte;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}