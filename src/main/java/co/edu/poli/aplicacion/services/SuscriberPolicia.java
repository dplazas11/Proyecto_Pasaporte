package co.edu.poli.aplicacion.services;

public class SuscriberPolicia implements Suscriber {

    @Override
    public String enviarNotificacion() {

        return "La policia ha sido notificada del cambio.";
    }

    @Override
    public String getNombre() {
        return "Policia";
        }

}
