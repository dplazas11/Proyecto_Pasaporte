/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;


public class AdaptadorPasaporte implements ProxyInterface  {
    
    private final PasaporteDiplomatico pasaporteAdaptado;

    public AdaptadorPasaporte(PasaporteDiplomatico pasaporteDiplomatico) {
        
        this.pasaporteAdaptado = pasaporteDiplomatico;
    }

    @Override
    public String mostrarInformacion(String rol) {
     
        return  "id=" + pasaporteAdaptado.getId()
                + "; fechaExp=" + pasaporteAdaptado.getFechaExp()
                + "; titular=" + (pasaporteAdaptado.getTitular() != null ? pasaporteAdaptado.getTitular() : "null")
                + "; pais=" + (pasaporteAdaptado.getPais() != null ? pasaporteAdaptado.getPais() : "null")
                + "; elemento seguridad=" + (pasaporteAdaptado.getElemSeguridad()!= null ? pasaporteAdaptado.getElemSeguridad(): "null")
                + "; mision=" + pasaporteAdaptado.getMision();
                
    }   
    
    
    PasaporteDiplomatico getPasaporteAdaptado(){
            return pasaporteAdaptado;
    }
        
    }
    
