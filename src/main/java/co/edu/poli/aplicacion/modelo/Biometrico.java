/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

/**
 *
 * @author User
 */
public class Biometrico extends ElementoSeguridad{
    
    private String tipoDatoBiometrico;
    
    
    public Biometrico(String id, String tipo, String descripcion, String huellaDactilar) {
        super(id, tipo, descripcion);
        this.tipoDatoBiometrico = huellaDactilar;
    }

    public String getTipoDatoBiometrico() {
        return tipoDatoBiometrico;
    }

    public void setTipoDatoBiometrico(String tipoDatoBiometrico) {
        this.tipoDatoBiometrico = tipoDatoBiometrico;
    }
    
}
