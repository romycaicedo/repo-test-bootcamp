public class Autos extends Vehiculo{
    public Autos(String patente, double velocidad, double anguloDeGiro, double aceleracion) {
        super(patente,velocidad,anguloDeGiro,aceleracion);
        this.setPeso(1000);
        this.setRuedas(4);
    }
}
