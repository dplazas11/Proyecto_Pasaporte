/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.modelo;

/**
 *
 * @author User
 */
public class Chip extends ElementoSeguridad{
    
    private String numeroChip;
    
    public Chip(String id, String tipo, String descripcion, String numeroChip) {
        super(id, tipo, descripcion);
        this.numeroChip = numeroChip;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }
    
}
