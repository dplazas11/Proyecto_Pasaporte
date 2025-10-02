/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

/**
 *
 * @author User
 */
public class DTitularHistorialViajes extends DecoratorAbstractTitular {
    
  private String historial;

    public DTitularHistorialViajes(String historial, InterfaceTitular titular) {
        super(titular);
        this.historial = historial;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }     
    

    @Override
    public String getDescripcion() {
        return titular.getDescripcion() + ", Historial: " + historial;
    }

}
