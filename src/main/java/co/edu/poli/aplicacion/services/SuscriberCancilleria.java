/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;


public class SuscriberCancilleria implements Suscriber {

    @Override
    public String enviarNotificacion() {
        
        return "La cancilleria ha sido notificada del cambio.";
        }

    @Override
    public String getNombre() {
        return "Cancilleria";
        }
    
    
    
}
