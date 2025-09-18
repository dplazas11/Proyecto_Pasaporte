package co.edu.poli.aplicacion.services;

import co.edu.poli.aplicacion.modelo.ElementoSeguridad;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;

public class BuilderPOWrapper {

    public static class Builder {

        private String id;
        private String fechaExp;
        private Titular titular;
        private Pais pais;
        private ElementoSeguridad elemSeguridad;
        private String motivoViaje;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFechaExp(String fechaExp) {
            this.fechaExp = fechaExp;
            return this;
        }

        public Builder setTitular(Titular titular) {
            this.titular = titular;
            return this;
        }

        public Builder setPais(Pais pais) {
            this.pais = pais;
            return this;
        }

        public Builder setElemSeguridad(ElementoSeguridad elemSeguridad) {
            this.elemSeguridad = elemSeguridad;
            return this;
        }

        public Builder setMotivoViaje(String motivoViaje) {
            this.motivoViaje = motivoViaje;
            return this;
        }

        public PasaporteOrdinario build() {
            return new PasaporteOrdinario(id, fechaExp, titular, pais,elemSeguridad, motivoViaje);
        }
    }

}
