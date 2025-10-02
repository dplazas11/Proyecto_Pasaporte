/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Titular;


public class DTitularAsistencia extends DecoratorAbstractTitular{
    
    private String tipoAsistencia;

    public DTitularAsistencia(InterfaceTitular titular, String tipoAsistencia) {
        super(titular);
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

      

    @Override
    public String getDescripcion() {
        return titular.getDescripcion() + ", tipo asistencia: "+tipoAsistencia;
    }

    @Override
    public String historialViajes() {
        return "1) Bogota - Medellin, 2)Medellin - Panama";
    }
    
}
