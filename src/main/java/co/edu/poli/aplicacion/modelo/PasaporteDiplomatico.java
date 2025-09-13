/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

public class PasaporteDiplomatico extends Pasaporte {

    private String Mision;

    public PasaporteDiplomatico() {
        super();
    }

    public PasaporteDiplomatico( String id, String fechaExp, Titular titular, Pais pais,String Mision) {
        super(id, fechaExp, titular, pais);
        this.Mision = Mision;
    }

    @Override
    public String toString() {
        return "PasaporteDiplomatico{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", mision=" + Mision
                + '}';
    }

    

    public String getMision() {
        return Mision;
    }

    public void setMision(String Mision) {
        this.Mision = Mision;
    }

}