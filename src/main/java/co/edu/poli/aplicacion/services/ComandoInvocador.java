package co.edu.poli.aplicacion.services;

import java.util.HashMap;
import java.util.Map;

public class ComandoInvocador {

    private Comando comand;
    private final Map<String, AdaptadorVisa> BdVisas = new HashMap();

    public String ejecutarComando(Comando comand, AdaptadorVisa adapvisa) {
        if (comand instanceof ComandoEmitirVisa){
        String idVisa = adapvisa.getId();
        BdVisas.put(idVisa, adapvisa);
        return comand.ejecutar();
        } else {
            String idVisa = adapvisa.getId();
            BdVisas.remove(idVisa);
            return comand.ejecutar();
        }
    }

    public AdaptadorVisa getVisa(String id) {
        return BdVisas.get(id);
    }

}
