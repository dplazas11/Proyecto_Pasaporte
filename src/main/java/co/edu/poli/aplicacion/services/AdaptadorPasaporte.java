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

    public AdaptadorPasaporte(Pasaporte pasaporte) {

        this.pasaporteAdaptado = pasaporte;
    }

    @Override
    public String mostrarInformacion(String rol) {
        if (pasaporteAdaptado instanceof PasaporteOrdinario) {
            PasaporteOrdinario p = (PasaporteOrdinario) pasaporteAdaptado;
            return "id=" + p.getId() + "\n"
                    + "Titular= " + (p.getTitular().getNombre() != null ? p.getTitular() : "null") + "\n"
                    + "FechaExp= " + p.getFechaExp() + "\n"
                    + "Pais= " + (p.getPais().getNombre() != null ? p.getPais() : "null") + "\n"
                    + "Elemento seguridad= " + (p.getElemSeguridad() != null ? p.getElemSeguridad() : "null") + "\n"
                    + "Motivo viaje= " + p.getMotivoViaje();
        } else {
            PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporteAdaptado;
            return "id=" + p.getId() + "\n"
                    + "Titular= " + (p.getTitular().getNombre() != null ? p.getTitular() : "null") + "\n"
                    + "FechaExp= " + p.getFechaExp() + "\n"
                    + "Pais= " + (p.getPais().getNombre() != null ? p.getPais() : "null") + "\n"
                    + "Elemento seguridad= " + (p.getElemSeguridad() != null ? p.getElemSeguridad() : "null") + "\n"
                    + "Mision= " + p.getMision();
        }

    }

    Pasaporte getPasaporteAdaptado() {
        return pasaporteAdaptado;
    }

    public MementoPasaporte guardar() {
        String mision = null;
        String motivoViaje = null;
        if (pasaporteAdaptado instanceof PasaporteOrdinario) {
            motivoViaje = ((PasaporteOrdinario) pasaporteAdaptado).getMotivoViaje();
        } else if (pasaporteAdaptado instanceof PasaporteDiplomatico) {
            mision = ((PasaporteDiplomatico) pasaporteAdaptado).getMision();
        }

        return new MementoPasaporte(
                pasaporteAdaptado.getId(),
                pasaporteAdaptado.getFechaExp(),
                pasaporteAdaptado.getTitular(),
                pasaporteAdaptado.getPais(),
                pasaporteAdaptado.getElemSeguridad(),
                motivoViaje,
                mision
        );

    }

    // Restaura el estado desde un MementoPasaporte
    public void restaurar(MementoPasaporte memento) {
        if (memento.getMision() != null) {
            pasaporteAdaptado = new PasaporteDiplomatico(
                    memento.getId(),
                    memento.getFechaExp(),
                    memento.getTitular(),
                    memento.getPais(),
                    memento.getElemSeguridad(),
                    memento.getMotivoViaje()
            );
            
        } else if (memento.getMotivoViaje() != null) {
            pasaporteAdaptado = new PasaporteOrdinario(
                    memento.getId(),
                    memento.getFechaExp(),
                    memento.getTitular(),
                    memento.getPais(),
                    memento.getElemSeguridad(),
                    memento.getMision()
                    
            );
        }
    }
}
