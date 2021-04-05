public class Motos extends Vehiculo{

    public Motos(String patente, double velocidad, double anguloDeGiro, double aceleracion) {
        super(patente, velocidad, anguloDeGiro, aceleracion);
        this.setPeso(300);
        this.setRuedas(2);
    }
}
