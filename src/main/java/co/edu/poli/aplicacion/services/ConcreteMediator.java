/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ConcreteMediator {

    List<Suscriber> componentes = new ArrayList();

    public void agregarComponente(CorAprobador componente) {
        componentes.add(componente);
        componente.setMediator(this);
    }

    public String notify(Suscriber sender, String mensaje) {
        if (sender instanceof AdaptadorPasaporte) {
            String respuesta = "";
            for (Suscriber c : componentes) {
                if (c instanceof SuscriberPolicia || c instanceof SuscriberMigracion) {
                    respuesta = respuesta + c.notificar(mensaje) + "\n";
                }
            }
            return respuesta;

        } else if (sender instanceof SuscriberCancilleria) {
            for (Suscriber c : componentes) {
                if (c instanceof SuscriberPolicia) {
                    return c.notificar(mensaje);
                }
            }

        } else if (sender instanceof SuscriberMigracion) {
            for (Suscriber c : componentes) {
                if (c instanceof SuscriberCancilleria) {
                    return c.notificar(mensaje);
                }

            }
        } else if (sender instanceof SuscriberPolicia) {
            String respuesta = "";
            for (Suscriber c : componentes) {
                if (c instanceof SuscriberCancilleria || c instanceof SuscriberMigracion) {
                    respuesta = respuesta + c.notificar(mensaje) + "\n";
                }

            }
            return respuesta;
        }
        return null;
    }
}
