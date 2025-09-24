package co.edu.poli.aplicacion.services;
import co.edu.poli.aplicacion.modelo.Ciudad;

public class AdaptadorCiudad implements CompEspacioGeografico {
    private final Ciudad ciudad;

   
    public AdaptadorCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public AdaptadorCiudad(String nombre) {
        this(new Ciudad(null, nombre));
    }
    
    @Override
    public String getNombre() {
        return ciudad.getNombre();
    }
    
}
