
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Visa;

public class AdaptadorVisa {
    
    private Visa visa;

    public AdaptadorVisa(Visa visa) {
        this.visa = visa;
    }
    
     public String emitir() {
        return "Emisión de visa " + visa.getNum() + " para el país " + visa.getPais().getNombre();
    }
    
     public String cancelar() {
        return "Cancelación de visa " + visa.getNum() + " aprobada.";
    }
     public String getId(){
         return visa.getNum();
     }
     public String getPais(){
         return visa.getPais().getNombre();
     }
     public int getMultEntry(){
         return visa.getMulEntry();
     }
     public String getHora(){
         return visa.getHora();
     }
     
    @Override
     public String toString (){
         return visa.toString();
     }
}
