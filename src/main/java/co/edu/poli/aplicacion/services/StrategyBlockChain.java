
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.blockchain;

public class StrategyBlockChain implements StrategyCreacionElemSeg{

    @Override
    public ElementoSeguridad crearElemento(String id, String tipo, String descripcion, String detalle) {
        return new blockchain(detalle, id, tipo, descripcion);
    }
    
}
