public class Vehiculo {

    public double velocidad;
    public double aceleracion;
    public double anguloDeGiro;
    public String patente;
    public double peso;
    public double ruedas;

    public Vehiculo(String patente, double velocidad, double anguloDeGiro, double aceleracion) {
        this.setAceleracion(aceleracion);
        this.setAnguloDeGiro(anguloDeGiro);
        this.setVelocidad(velocidad);
        this.setPatente(patente);
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getAnguloDeGiro() {
        return anguloDeGiro;
    }

    public void setAnguloDeGiro(double anguloDeGiro) {
        this.anguloDeGiro = anguloDeGiro;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getRuedas() {
        return ruedas;
    }

    public void setRuedas(double ruedas) {
        this.ruedas = ruedas;
    }
}
