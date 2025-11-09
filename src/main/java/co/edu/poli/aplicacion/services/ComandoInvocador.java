package co.edu.poli.aplicacion.services;

import java.util.HashMap;
import java.util.Map;

public class ComandoInvocador {

    private Comando comand;
    private Map<String, AdaptadorVisa> BdVisas = new HashMap();

    public String ejecutarComando(Comando comand, AdaptadorVisa adapvisa) {
        String idVisa = adapvisa.getId();
        BdVisas.put(idVisa, adapvisa);
        return comand.ejecutar();
    }

    public AdaptadorVisa getVisa(String id) {
        return BdVisas.get(id);
    }

}
