/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;


public class ProxyAdaptadorPasaporte implements ProxyInterface  {
    
    private final PasaporteDiplomatico pasaporteDiplomaticoAdap;

    public ProxyAdaptadorPasaporte(PasaporteDiplomatico pasaporteDiplomatico) {
        
        this.pasaporteDiplomaticoAdap = pasaporteDiplomatico;
    }

    @Override
    public String mostrarInformacion(String rol) {
     
        return  "id=" + pasaporteDiplomaticoAdap.getId()
                + "; fechaExp=" + pasaporteDiplomaticoAdap.getFechaExp()
                + "; titular=" + (pasaporteDiplomaticoAdap.getTitular() != null ? pasaporteDiplomaticoAdap.getTitular() : "null")
                + "; pais=" + (pasaporteDiplomaticoAdap.getPais() != null ? pasaporteDiplomaticoAdap.getPais() : "null")
                + "; elemento seguridad=" + (pasaporteDiplomaticoAdap.getElemSeguridad()!= null ? pasaporteDiplomaticoAdap.getElemSeguridad(): "null")
                + "; mision=" + pasaporteDiplomaticoAdap.getMision();
                
    }   
        
    }
    
