
package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;





public class CompositeRegion implements CompEspacioGeografico{
   
    private List<CompEspacioGeografico> children = new ArrayList<>();
    private String nombre;
    
    public CompositeRegion(String nombre) {        
        this.nombre = nombre;
    }

    
    public void add(CompEspacioGeografico c){
        children.add(c);
    }
    public void remove(CompEspacioGeografico c){ 
        children.remove(c); }
    
    
    @Override
    public String getNombre() {
        StringBuilder sb = new StringBuilder();
        sb.append("Región: ").append(nombre).append("\n");
        for (CompEspacioGeografico hijo : children) {
            sb.append("   ").append(hijo.getNombre()).append("\n");
        }
        return sb.toString();
    }
    public List<CompEspacioGeografico> getChildren(){
        return children;
    }
     
}
