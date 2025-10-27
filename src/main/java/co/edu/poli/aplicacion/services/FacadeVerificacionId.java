/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

/**
 *
 * @author User
 */
public class FacadeVerificacionId {
    
    public boolean verificacionId(int codigo){
        if (codigo == 1 || codigo == 2) return true;
        else return false;
    }
    
}
