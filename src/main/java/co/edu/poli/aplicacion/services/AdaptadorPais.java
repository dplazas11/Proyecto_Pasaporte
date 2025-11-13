package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.Pais;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorPais {

    private Pais adapPais;
    private Estado estadoActual;

    // Transiciones posibles y direcciones elegidas
    private Map<String, List<String>> transiciones = new HashMap<>();
    private Map<String, String> direccionElegida = new HashMap<>();

    public AdaptadorPais(Pais pais) {
        this.estadoActual = new EstadoNormal();
    }

    public String getEstadoActualNombre() {
        return estadoActual.nombre();
    }

    public void setEstadoActual(Estado nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public void agregarTransiciones(String origen, List<String> destinos) {
        transiciones.put(origen, destinos);
    }

    public List<String> posiblesTransiciones(String origen) {
        return transiciones.getOrDefault(origen, new ArrayList<>());
    }

    public void setDireccion(String origen, String destino) {
        direccionElegida.put(origen, destino);
    }

    public String getDireccion(String origen) {
        return direccionElegida.getOrDefault(origen, "");
    }

    public void realizarTransicion(Estado estado) {
        String actual = estado.nombre();
        String destino = getDireccion(actual);

        if (destino.isEmpty()) {
            System.out.println("No se ha definido una dirección desde " + actual);
            return;
        }

        switch (destino) {
            case "estado normal":
                setEstadoActual(new EstadoNormal());
                break;
            case "estado revision":
                setEstadoActual(new EstadoRevision());
                break;
            case "solicitud visa":
                setEstadoActual(new EstadoSolicitudVisa());
                break;
            case "frontera cerrada":
                setEstadoActual(new EstadoFronteraCerrada());
                break;
            default:
                String mensaje = "no valido";
        }
    }

    public void avanzar() {

        estadoActual.avanzar(this);

    }

    // Permite reconfigurar el autómata sin reiniciar
    /*public void reconfigurar(Scanner sc) {
        System.out.println("\n⚙️  --- RECONFIGURAR TRANSICIONES ---");
        for (String estado : Arrays.asList("A", "B", "C", "D")) {
            System.out.print("Ingrese las nuevas transiciones posibles desde " + estado + " (separadas por coma, o vacío si ninguna): ");
            String entrada = sc.nextLine().trim();
            List<String> destinos = entrada.isEmpty() ? new ArrayList<>()
                    : Arrays.asList(entrada.toUpperCase().split(","));
            agregarTransiciones(estado, destinos);
        }
        System.out.println("✅ Transiciones actualizadas correctamente.\n");
    }*/
}
