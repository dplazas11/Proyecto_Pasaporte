package co.edu.poli.aplicacion.modelo;

public class PasaporteOrdinario extends Pasaporte {

    private String motivoViaje;

    public PasaporteOrdinario() {
        super();
    }

    private PasaporteOrdinario(Builder builder) {
        super(builder.id, builder.fechaExp, builder.titular, builder.pais, builder.elemSeguridad);
        this.motivoViaje = builder.motivoViaje;
    }

    public String getMotivoViaje() {
        return motivoViaje;
    }

    public void setMotivoViaje(String motivoViaje) {
        this.motivoViaje = motivoViaje;
    }

    @Override
    public String toString() {
        return "PasaporteOrdinario{"
                + "id=" + getId()
                + ", fechaExp=" + getFechaExp()
                + ", titular=" + (getTitular() != null ? getTitular() : "null")
                + ", pais=" + (getPais() != null ? getPais() : "null")
                + ", elemento seguridad=" + (getElemSeguridad() != null ? getElemSeguridad() : "null")
                + ", motivoDeViaje=" + motivoViaje
                + '}';
    }

    public static class Builder {

        private String id;
        private String fechaExp;
        private Titular titular;
        private Pais pais;
        private ElementoSeguridad elemSeguridad;
        private String motivoViaje;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fechaExp(String fechaExp) {
            this.fechaExp = fechaExp;
            return this;
        }

        public Builder titular(Titular titular) {
            this.titular = titular;
            return this;
        }

        public Builder pais(Pais pais) {
            this.pais = pais;
            return this;
        }

        public Builder elemSeguridad(ElementoSeguridad elemSeguridad) {
            this.elemSeguridad = elemSeguridad;
            return this;
        }

        public Builder motivoViaje(String motivoViaje) {
            this.motivoViaje = motivoViaje;
            return this;
        }

        public PasaporteOrdinario build() {
            return new PasaporteOrdinario(this);
        }
    }

}
