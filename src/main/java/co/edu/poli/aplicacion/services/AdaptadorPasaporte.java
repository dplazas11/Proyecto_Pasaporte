/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;

public class AdaptadorPasaporte implements ProxyInterface {

    private Pasaporte pasaporteAdaptado;

    public AdaptadorPasaporte() {
    }

    public AdaptadorPasaporte(Pasaporte pasaporte) {
        if (pasaporte instanceof PasaporteOrdinario) {
            this.pasaporteAdaptado = (PasaporteOrdinario) pasaporte;
        } else if (pasaporte instanceof PasaporteDiplomatico) {
            this.pasaporteAdaptado = (PasaporteDiplomatico) pasaporte;
        }
    }

    @Override
    public String mostrarInformacion(String rol) {
        if (pasaporteAdaptado instanceof PasaporteOrdinario) {
            PasaporteOrdinario p = (PasaporteOrdinario) pasaporteAdaptado;
            return "id=" + p.getId() + "; "
                    + "Titular= " + (p.getTitular().getNombre() != null ? p.getTitular().getNombre() : "Pendiente") + "; "
                    + "FechaExp= " + p.getFechaExp() + "; "
                    + "Pais= " + (p.getPais().getNombre() != null ? p.getPais().getNombre() : "Pendiente") + "; "
                    + "Elemento seguridad= " + (p.getElemSeguridad() != null ? p.getElemSeguridad().getTipo() : "Pendiente") + "; "
                    + "Motivo viaje= " + p.getMotivoViaje();
        } else {
            PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporteAdaptado;
            return "id=" + p.getId() + "; "
                    + "Titular= " + (p.getTitular().getNombre() != null ? p.getTitular().getNombre() : "Pendiente") + "; "
                    + "FechaExp= " + p.getFechaExp() + "; "
                    + "Pais= " + (p.getPais().getNombre() != null ? p.getPais().getNombre() : "pendiente") + "; "
                    + "Elemento seguridad= " + (p.getElemSeguridad() != null ? p.getElemSeguridad().getTipo() : "Pendiente") + "; "
                    + "Mision= " + p.getMision();
        }

    }

    public Pasaporte getPasaporteAdaptado() {
        return pasaporteAdaptado;
    }

    public MementoPasaporte guardar() {
        return new MementoPasaporte(pasaporteAdaptado);
    }

    // Restaura el estado desde un MementoPasaporte
    public Pasaporte restaurar(MementoPasaporte memento) {
        if (memento.getMision() != null) {
            pasaporteAdaptado = new PasaporteDiplomatico(
                    memento.getId(),
                    memento.getFechaExp(),
                    memento.getTitular(),
                    memento.getPais(),
                    memento.getElemSeguridad(),
                    memento.getMision()
            );

        } else if (memento.getMotivoViaje() != null) {
            pasaporteAdaptado = new PasaporteOrdinario(
                    memento.getId(),
                    memento.getFechaExp(),
                    memento.getTitular(),
                    memento.getPais(),
                    memento.getElemSeguridad(),
                    memento.getMotivoViaje()
            );
        }
        return pasaporteAdaptado;
    }
    
   
    
}
