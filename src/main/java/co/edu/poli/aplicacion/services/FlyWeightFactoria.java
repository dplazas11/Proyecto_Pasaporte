/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pasaporte;
import java.util.HashMap;
import java.util.Map;

public class FlyWeightFactoria {

    private static Map<String, FlyWeightPasaporteTipo> tipos = new HashMap<>();

    public FlyWeightFactoria(Map<String, FlyWeightPasaporteTipo> pasaporteTipo) {
        FlyWeightFactoria.tipos = pasaporteTipo;
    }

    public Map<String, FlyWeightPasaporteTipo> getTipos() {
        return tipos;
    }

    public void setTipos(Map<String, FlyWeightPasaporteTipo> tipos) {
        FlyWeightFactoria.tipos = tipos;
    }

    // Registra o reutiliza un tipo de pasaporte según el país
    public static void registrarPasaporteTipo(FlyWeightPasaporteTipo pasaporteTipo) {
        String pais = pasaporteTipo.getPaisEmisor();

        if (!tipos.containsKey(pais)) {
            tipos.put(pais, pasaporteTipo);
        }

    }

    public static void mostrarInformacion(Pasaporte pasaporte) {
        StringBuilder st = new StringBuilder();

        String pais = pasaporte.getPais().getNombre();
        FlyWeightPasaporteTipo pasaporteTipo = tipos.get(pais);

        System.out.println(st.append(pasaporte).append("\n").append(pasaporteTipo));

    }

}
