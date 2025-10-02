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
    
    private long hash;

    public blockchain(long hash, String id, String tipo, String descripcion) {
        super(id, tipo, descripcion);
        this.hash = hash;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }
    
    
    
    
}
