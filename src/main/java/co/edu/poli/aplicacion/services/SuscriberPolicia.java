package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SuscriberPolicia extends CorAprobador implements Suscriber {

    @Override
    public String enviarNotificacion() {

        return "La policia ha sido notificada del cambio.";
    }

    @Override
    public String getNombre() {
        return "Policia";
    }

    @Override
    public Map<String, Object> aprobar(String idTitular) {
        Map<String, Object> estadoAprobacion = new HashMap<>();
        ArrayList<String> cedulasNoAprobadas = new ArrayList<>();
        cedulasNoAprobadas.addAll(Arrays.asList("1000145236", "20123456", "80123654"));

        if (cedulasNoAprobadas.contains(idTitular)) {
            estadoAprobacion.put("estado", false);
            estadoAprobacion.put("mensaje", "El pasaporte no puede ser aprobado porque la policía encontró que la persona tiene antecedentes penales.");
        } else if (siguiente != null) {
            return siguiente.aprobar(idTitular);
        } else {
            estadoAprobacion.put("estado", true);
            estadoAprobacion.put("mensaje", "El pasaporte ha sido aprobado por la Cancilleria y la Policia.");
        }
        return estadoAprobacion;
    }

}
