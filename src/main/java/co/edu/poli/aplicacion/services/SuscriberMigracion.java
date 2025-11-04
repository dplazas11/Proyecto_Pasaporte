package co.edu.poli.aplicacion.services;

public class SuscriberMigracion implements Suscriber {

    @Override
    public String enviarNotificacion() {

        return "Migracion Colombia ha sido notificada del cambio.";
    }

    @Override
    public String getNombre() {
        return "Migración Colombia";
       }

}
