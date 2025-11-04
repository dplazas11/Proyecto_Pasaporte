package co.edu.poli.aplicacion.services;

public class SuscriberPolicia implements Suscriber {

    @Override
    public String enviarNotificacion() {

        return "La policia ha sio notificada del cambio.";
    }

}
