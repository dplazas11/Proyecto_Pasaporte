/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

/**
 *
 * @author User
 */
public class DTitularSeguro extends DecoratorAbstractTitular{
    
    
    private String idSeguro;

    public DTitularSeguro(InterfaceTitular titular, String idSeguro) {
        super(titular);
        this.idSeguro = idSeguro;
    }

    public String getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(String idSeguro) {
        this.idSeguro = idSeguro;
    }

       
    

    @Override
    public String getDescripcion() {
        return titular.getDescripcion() + ", NumSeguro: " + idSeguro;
    }
    
}
