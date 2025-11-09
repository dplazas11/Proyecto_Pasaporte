
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Chip;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;


public class StrategyChip implements StrategyCreacionElemSeg{

    @Override
    public ElementoSeguridad crearElemento(String id, String tipo, String descripcion, String detalle) {
       return new Chip(id, tipo, descripcion, detalle);
               
    }
    
}
