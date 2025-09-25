package co.edu.poli.aplicacion.services;

import java.util.ArrayList;
import java.util.List;

public class CompositeRegion implements CompEspacioGeografico {

    private List<CompEspacioGeografico> children = new ArrayList<>();
    private String nombre;

    public CompositeRegion(String nombre) {
        this.nombre = nombre;
    }

    public void add(CompEspacioGeografico c) {
        children.add(c);
    }

    public void remove(CompEspacioGeografico c) {
        children.remove(c);
    }

    @Override
    public String getNombre() {
        StringBuilder sb = new StringBuilder();

        // Detectar el nivel en función de cuántas veces aparece "   " en el texto acumulado
        String etiqueta = "";

        sb.append(etiqueta).append("->").append(nombre).append("\n");

        for (CompEspacioGeografico hijo : children) {
            // añadir indentación y procesar al hijo
            String nombreHijo = hijo.getNombre();
            for (String linea : nombreHijo.split("\n")) {
                if (!linea.isBlank()) {
                    sb.append(" ").append(linea).append("\n");
                }
            }
        }

        return sb.toString();

    }

    public List<CompEspacioGeografico> getChildren() {
        return children;
    }

}
