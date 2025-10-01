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
    
    private String huellaDactilar;
    
    
    public Biometrico(String id, String tipo, String descripcion, String huellaDactilar) {
        super(id, tipo, descripcion);
        this.huellaDactilar = huellaDactilar;
    }

    public String getHuellaDactilar() {
        return huellaDactilar;
    }

    public void setHuellaDactilar(String huellaDactilar) {
        this.huellaDactilar = huellaDactilar;
    }
    
}
