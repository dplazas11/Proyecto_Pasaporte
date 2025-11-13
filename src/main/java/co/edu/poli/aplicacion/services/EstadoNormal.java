/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;


public class EstadoNormal implements Estado{

    @Override
    public void avanzar(AdaptadorPais automata) {
        automata.realizarTransicion(this);
        }

    @Override
    public String nombre() {
     return "estado normal";
    }
    
    
}
