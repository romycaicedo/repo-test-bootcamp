public class Main {
    public static void main (String[] args){
            Carrera carrera = new Carrera();
            carrera.cantidadDeVehiculosPermitidos = 3;
            carrera.darDeAltaAuto(2,2,45,"Skoda");
            carrera.darDeAltaMoto(2,3,15,"BMW");
            carrera.definirGanador();
            carrera.socorrerAuto("Skoda");
            carrera.socorrerMoto("BMW");
    }
}
