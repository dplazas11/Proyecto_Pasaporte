/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Titular;

/**
 *
 * @author User
 */
public class PrototypeTitularWrapper implements Cloneable {
    
    private Titular titular;

    public PrototypeTitularWrapper(Titular titular) {
        this.titular = titular;
    }

    public Titular getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        return "PrototypeTitularWrapper{" + "titular=" + titular + '}';
    }

    
    @Override
    public Titular clone() throws CloneNotSupportedException {
        
        Titular copia = new Titular(
            titular.getId(),
            titular.getNombre(),
            titular.getFechaNac()
        );
        return copia;
    }
    
    
    
    
}
