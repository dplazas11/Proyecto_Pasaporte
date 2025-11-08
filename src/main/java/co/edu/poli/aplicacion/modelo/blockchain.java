/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

/**
 *
 * @author User
 */
public class blockchain extends ElementoSeguridad{
    
    private String hash;

    public blockchain(String hash, String id, String tipo, String descripcion) {
        super(id, tipo, descripcion);
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
    
    
}
