/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

/**
 *
 * @author LENOVO
 */
public class ComandoEmitirVisa implements Comando {

    private AdaptadorVisa adapVisa;

    public ComandoEmitirVisa(AdaptadorVisa adapVisa) {
        this.adapVisa = adapVisa;
    }

    @Override
    public String ejecutar() {
        return adapVisa.emitir();
    }

}
