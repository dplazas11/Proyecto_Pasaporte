
package co.edu.poli.aplicacion.services;

public class ComandoSuspenderVisa implements Comando{
    
    private AdaptadorVisa adapVisa;

    public ComandoSuspenderVisa(AdaptadorVisa adapVisa) {
        this.adapVisa = adapVisa;
    }
    

    @Override
    public String ejecutar() {
        return adapVisa.cancelar();
        }
    
}
