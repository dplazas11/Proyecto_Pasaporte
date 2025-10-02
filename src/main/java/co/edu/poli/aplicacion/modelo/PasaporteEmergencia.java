/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;


public class PasaporteEmergencia extends Pasaporte{
    
    private String fechaExpiracion; 
    
    public PasaporteEmergencia(){
        super();
    }

    public PasaporteEmergencia(String fechaExpiracion, String id, String fechaExp, Titular titular, Pais pais, ElementoSeguridad elemSeguridad) {
        super(id, fechaExp, titular, pais, elemSeguridad);
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "PasaporteEmergencia{" + "fechaExpiracion=" + fechaExpiracion + '}';
    }
    
    
    
    
    
    
    
}
