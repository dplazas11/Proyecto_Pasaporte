package co.edu.poli.aplicacion.services;


import java.util.HashSet;
import java.util.Set;



public class ObserverPublisher {

    private Set<Suscriber> suscriptores = new HashSet<>();

    public String suscribir(Suscriber sus) {
        if (!suscriptores.contains(sus)){
        suscriptores.add(sus);        
        return " Se a suscrito " + sus.toString() +  " correctamente";
        }
        else {
            return "Ya se suscribió " + sus.toString();
        }
    }

    public String desuscribir(Suscriber sus) {
        
        if(suscriptores.contains(sus)){
        suscriptores.remove(sus);
        return "Se ha eliminado "  + sus.toString() + " correctament.";
        }
        else return sus.toString() + " no esta suscrito.";
    }

    public String notificarSuscribers() {
        String confirmacion = "";
        for (Suscriber sus : suscriptores) {
            confirmacion = sus.enviarNotificacion() + "\n";
        }
        
        return confirmacion;
    }
}


