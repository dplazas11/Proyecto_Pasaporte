
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Biometrico;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;

public class StrategyBiometrico implements StrategyCreacionElemSeg {

    @Override
    public ElementoSeguridad crearElemento(String id, String tipo, String descripcion, String detalle) {
        return new Biometrico(id, tipo, descripcion, detalle);
        }
    
}
