package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Biometrico;
import co.edu.poli.aplicacion.modelo.Chip;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;

public class AdaptadorElemSeguridad {

    private StrategyCreacionElemSeg estrategia;
    private ElementoSeguridad adaptElemSeg;

    public void setEstrategia(StrategyCreacionElemSeg estrategia) {
        this.estrategia = estrategia;
    }

    public String crearElemSeg(String id, String tipo, String descripcion, String detalle) {

        if (estrategia == null) {
            return "No hay estrategia definida para crear el elemento.";
        }
        this.adaptElemSeg = estrategia.crearElemento(id, tipo, descripcion, detalle);

        if (adaptElemSeg instanceof Biometrico) {
            return "Elemento de Seguridad Biometrico creado exitosamente.";
        } else if (adaptElemSeg instanceof Chip) {
            return "Elemento de Seguridad Biometrico creado exitosamente.";
        }else{
            return "Elemento de Seguridad blockchain creado exitosamente.";
        }
    }
}
