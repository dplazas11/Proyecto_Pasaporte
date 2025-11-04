package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ObserverPublisher {

    private Set<Suscriber> suscriptores = new HashSet<>();

    public String suscribir(Suscriber sus) {

        for (Suscriber s : suscriptores) {
            if (s.getClass() == sus.getClass()) {

                return "Ya esta suscrito " + sus.getNombre();

            }
        }
        suscriptores.add(sus);

        return "Se suscribió " + sus.getNombre() + " correctamente";

    }

    public String desuscribir(Suscriber sus) {
        Iterator<Suscriber> iterator = suscriptores.iterator();

        while (iterator.hasNext()) {
            Suscriber s = iterator.next();
            if (s.getClass() == sus.getClass()) {
                iterator.remove();
                return "Se ha eliminado " + sus.getNombre() + " correctamente.";
            }
        }

        return sus.getNombre() + " no está suscrito.";
    }

    public String notificarSuscribers() {
        if (suscriptores.isEmpty()) {
            return "No hay suscriptores registrados.";
        }

        StringBuilder confirmacion = new StringBuilder();
        for (Suscriber sus : suscriptores) {
            confirmacion.append(sus.enviarNotificacion()).append("\n");
        }
        return confirmacion.toString();
    }

    public String verLista() {
        String mensaje = "";
        for (Suscriber sus : suscriptores) {
            String susString = sus.getNombre();
            mensaje = mensaje + susString + " ";

        }
        return mensaje;
    }
}
