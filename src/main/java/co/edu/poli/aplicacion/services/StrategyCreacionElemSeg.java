
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.ElementoSeguridad;


public interface StrategyCreacionElemSeg {
    
    ElementoSeguridad crearElemento (String id, String tipo, String descripcion, String detalle);
}
