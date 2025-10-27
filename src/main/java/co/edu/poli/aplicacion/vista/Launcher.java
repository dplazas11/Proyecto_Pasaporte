package co.edu.poli.aplicacion.vista;

import co.edu.poli.aplicacion.modelo.Ciudad;
import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.*;
import co.edu.poli.aplicacion.modelo.Titular;
import co.edu.poli.aplicacion.services.*;
import co.edu.poli.aplicacion.services.BuilderPOWrapper;
import co.edu.poli.aplicacion.services.DTitularAsistencia;
import co.edu.poli.aplicacion.services.DTitularSeguro;
import co.edu.poli.aplicacion.services.FlyWeightPasaporteTipo;
import co.edu.poli.aplicacion.services.PrototypeTitularWrapper;
import co.edu.poli.aplicacion.services.InterfaceTitular;

import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) throws CloneNotSupportedException {
        
        //Creacion de pasaporte
        ArrayList<Ciudad> ciudades= new ArrayList<>();
        Titular Carla = new Titular("1001", "Carla martinez", "11-08-99");
        Ciudad Bogota = new Ciudad("1", "Bogota");
        Ciudad Roma = new Ciudad("02", "Roma");
        ciudades.add(Bogota);
        ciudades.add(Roma);        
        Pais Colombia = new Pais("01", "Colombia", ciudades);
        Pais Italia = new Pais("02", "Italia", ciudades);
        ElementoSeguridad blockchain = new blockchain(01, "blockchain", "blockchain", "blockchain");
        
        PasaporteDiplomatico PasaporteColombia = new PasaporteDiplomatico("A0012" ,"11-08-2015" ,Carla, Colombia, blockchain, "trabajo");     
        Pasaporte PasaporteItalia = new PasaporteDiplomatico("02", "06-01-84", Carla, Italia, blockchain, "Humanitaria");
        
        ProxyAdaptadorPasaporte PastColombiaAdap = new ProxyAdaptadorPasaporte(PasaporteColombia);
        
        ProxyGeneradorPasaporte proxy = new ProxyGeneradorPasaporte(PastColombiaAdap);
        System.out.println(proxy.mostrarInformacion("generico"));
        
        
        
        
        
        
        
        
        
                
        

    }
}
