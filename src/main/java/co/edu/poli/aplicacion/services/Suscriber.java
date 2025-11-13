/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.poli.aplicacion.services;

public interface Suscriber {
    
    //METODOS OBSERVER 
    String enviarNotificacion ();
    String getNombre();
    
    //METODOS MEDIATOR
    void setMediator(ConcreteMediator mediator);
    String notificar(String evento);
    
}
