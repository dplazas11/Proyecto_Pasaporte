package co.edu.poli.aplicacion.vista;

import co.edu.poli.aplicacion.modelo.Ciudad;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;
import co.edu.poli.aplicacion.services.BuilderPOWrapper;
import co.edu.poli.aplicacion.services.DTitularAsistencia;
import co.edu.poli.aplicacion.services.DTitularSeguro;
import co.edu.poli.aplicacion.services.PrototypeTitularWrapper;
import co.edu.poli.aplicacion.services.InterfaceTitular;

import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) throws CloneNotSupportedException {
        //App.main(args); // Llama al main de tu clase App   

        Titular titularCarlos = new Titular("123", "Carlos Perez", "25-03-2005");
        Titular titularSara = new Titular("456", "Sara Perez", "15-08-2009");

        InterfaceTitular adaptTitularCarlos = new PrototypeTitularWrapper(titularCarlos);
        InterfaceTitular adaptTitularSara = new PrototypeTitularWrapper(titularSara);

        InterfaceTitular CarlosConAsis = new DTitularAsistencia(adaptTitularCarlos, "Medica");
        InterfaceTitular CarlosConTodo = new DTitularSeguro(CarlosConAsis, "S9876");

        InterfaceTitular SaraConSeg = new DTitularSeguro(adaptTitularSara, "S1234");

        System.out.println("Carlos: " + CarlosConTodo.getDescripcion());
        System.out.println("Sara: " + SaraConSeg.getDescripcion());
        

    }
}
