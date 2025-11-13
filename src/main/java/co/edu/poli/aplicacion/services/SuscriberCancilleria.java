/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SuscriberCancilleria extends CorAprobador {

    @Override
    public String enviarNotificacion() {
        return "La cancilleria ha sido notificada del cambio.";
    }

    @Override
    public String getNombre() {
        return "Cancilleria";
    }

    @Override
    public Map<String, Object> aprobar(String idTitular) {

        Map<String, Object> estadoAprobacion = new HashMap<>();
        ArrayList<String> cedulasNoAprobadas = new ArrayList<>();
        cedulasNoAprobadas.addAll(Arrays.asList("20492804", "803987654", "1000787654"));

        if (cedulasNoAprobadas.contains(idTitular)) {
            estadoAprobacion.put("estado", false);
            estadoAprobacion.put("mensaje", "El pasaporte no puede ser aprobado porque la cancillería encontró que la persona no es de nacionalidad Colombiana");

        } else if (siguiente != null) {
            return siguiente.aprobar(idTitular);
        } else {
            estadoAprobacion.put("estado", true);
            estadoAprobacion.put("mensaje", "El pasaporte ha sido aprobado por la Cancilleria.");
        }
        return estadoAprobacion;
    }

    @Override
    public String procesar(String mensaje) {
        return mediator.notify(this, mensaje);
    }

    @Override
    public String notificar(String evento) {
        return "La cancilleria ha sido notificada de: " + evento;
    }

}
