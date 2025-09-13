/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

public class PasaporteOrdinario extends Pasaporte {

    private String MotivoDeViaje;

    public PasaporteOrdinario() {
        super();
    }

    public PasaporteOrdinario(String id, String fechaExp, Titular titular, Pais pais, String MotivoDeViaje) {
        super(id, fechaExp, titular, pais);
        this.MotivoDeViaje = MotivoDeViaje;
    }

    public String getMotivoDeViaje() {
        return MotivoDeViaje;
    }

    public void setMotivoDeViaje(String MotivoDeViaje) {
        this.MotivoDeViaje = MotivoDeViaje;
    }

    @Override
    public String toString() {
        return "PasaporteOrdinario{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", motivoDeViaje=" + MotivoDeViaje
                + '}';
    }

}
