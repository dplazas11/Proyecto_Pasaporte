
package co.edu.poli.aplicacion.services;


public abstract class DecoratorAbstractTitular implements InterfaceTitular{
    
    protected InterfaceTitular titular;

    public DecoratorAbstractTitular(InterfaceTitular titular) {
        this.titular = titular;
    }

    @Override
    public abstract String getDescripcion();
}

