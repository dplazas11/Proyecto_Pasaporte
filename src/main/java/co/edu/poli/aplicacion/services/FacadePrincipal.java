/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

/**
 *
 * @author User
 */
public class FacadePrincipal {

    private FacadeVerificacionId verificacionId;
    private FacadeVerificacionAntecedentes verificacionAntecedentes;
    private FacadeGeneracionPasaporte generacionPasaporte;

    public FacadePrincipal() {
        verificacionId = new FacadeVerificacionId();
        verificacionAntecedentes = new FacadeVerificacionAntecedentes();
        generacionPasaporte = new FacadeGeneracionPasaporte();
    }

    public void generarPasaporte(int codigo) {

        if (!verificacionId.verificacionId(codigo)) {
            System.out.println("No se pudo verificar el id");
        } else if (!verificacionAntecedentes.verificacionAntecedentes(codigo)) {
            System.out.println("Se verifico el id pero no se pudo verificar antecedentes");
        } else if (!generacionPasaporte.generacionPasaporte(codigo)) {
            System.out.println("No se pudo generar el pasaporte");
        } else {
            System.out.println("Pasaporte generado correctamente");
        }

    }

}
