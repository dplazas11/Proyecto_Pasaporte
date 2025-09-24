
package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;





public class RegionComposite implements EspacioGeografico{
   
    private List<EspacioGeografico> children = new ArrayList<>();
    private String nombre;
    
    public RegionComposite() {
        this.nombre = null;
    }

    /** Constructor con nombre; útil para subniveles */
    public RegionComposite(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        this.nombre = nombre;
    }
    public void add(EspacioGeografico c){
        children.add(c);
    }
    public void remove(EspacioGeografico c){ 
        children.remove(c); }
    @Override
    public String getNombre() {
       
         StringBuilder sb = new StringBuilder();

        // Si hay nombre, lo añade con salto de línea
        if (nombre != null) {
            sb.append(nombre).append("\n");
        }

        // Concatenar recursivamente la salida de cada hijo
        for (EspacioGeografico c : children) {
            
            sb.append(" - "+c.getNombre()+ "\n");
        }

        return sb.toString();
    }
    
     
}
