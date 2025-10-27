
package co.edu.poli.aplicacion.services;


public class FlyWeightPasaporteTipo {
    
    private String colorCubierta;
    private String idioma;
    private String paisEmisor;

    public FlyWeightPasaporteTipo(String paisEmisor, String colorCubierta, String idioma) {
        this.colorCubierta = colorCubierta;
        this.idioma = idioma;
        this.paisEmisor = paisEmisor;
    }

    public String getColorCubierta() {
        return colorCubierta;
    }

    public void setColorCubierta(String colorCubierta) {
        this.colorCubierta = colorCubierta;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPaisEmisor() {
        return paisEmisor;
    }

    public void setPaisEmisor(String paisEmisor) {
        this.paisEmisor = paisEmisor;
    }

    @Override
    public String toString() {
        return "PasaporteTipo(" + "colorCubierta=" + colorCubierta + ", idioma=" + idioma + ", paisEmisor=" + paisEmisor + ')';
    }
    
    
    
}
