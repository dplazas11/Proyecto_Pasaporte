/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;


public class ProxyGeneradorPasaporte implements ProxyInterface {

    private final AdaptadorPasaporte pasaporte;

    public ProxyGeneradorPasaporte(AdaptadorPasaporte pasaporteRecibido) {
        this.pasaporte = pasaporteRecibido;
    }

    @Override
    public String mostrarInformacion(String rol) {
        
        switch (rol) {
            case "admin":
                return pasaporte.mostrarInformacion(rol);
            case "usuario":
                String pasaporteCompleto = pasaporte.mostrarInformacion(rol);
                String[] ArregloPasaporteCompleto = pasaporteCompleto.split(";");
                
                String PasaporteCortado = "Pasaporte Diplomatico= " + ArregloPasaporteCompleto[0] + ", " + ArregloPasaporteCompleto[1] + ", " + ArregloPasaporteCompleto[4] + ", " + ArregloPasaporteCompleto[5];
                return PasaporteCortado;
            case "generico":
                return "No tiene permisos para ver la informacion";
            default:
                return "Ingrese un rol";
        }

    }

}
