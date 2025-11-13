package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SuscriberMigracion extends CorAprobador {

    @Override
    public String enviarNotificacion() {

        return "Migracion Colombia ha sido notificada del cambio.";
    }

    @Override
    public String getNombre() {
        return "Migración Colombia";
    }

    @Override
    public Map<String, Object> aprobar(String idTitular) {
        Map<String, Object> estadoAprobacion = new HashMap<>();
        ArrayList<String> cedulasNoAprobadas = new ArrayList<>();
        cedulasNoAprobadas.addAll(Arrays.asList("1073156962", "20333951", "70396521"));

        if (cedulasNoAprobadas.contains(idTitular)) {
            estadoAprobacion.put("estado", false);
            estadoAprobacion.put("mensaje", "El pasaporte no puede ser aprobado porque Migración Colombia encontró que la persona esta relacionada con delitos internacionales");
        } else {
            estadoAprobacion.put("estado", true);
            estadoAprobacion.put("mensaje", "El pasaporte ha sido aprobado por los agentes de seguridad.");
        }
        return estadoAprobacion;
    }

    @Override
    public String procesar(String mensaje) {
       return mediator.notify(this, mensaje);
    }

    @Override
    public String notificar(String evento) {
        return "Migración colombia ha sido notificada de: " + evento;
    }
}
