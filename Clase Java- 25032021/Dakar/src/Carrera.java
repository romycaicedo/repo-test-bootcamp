import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Carrera {
    public double distancia;
    public double premioEnDolares;
    public String nombre;
    public double cantidadDeVehiculosPermitidos;
    public List<Vehiculo> vehiculos = new ArrayList<>();
    public SocorristaMoto socorristaMoto = new SocorristaMoto();
    public SocorristaAuto socorristaAuto = new SocorristaAuto();

    public void darDeAltaAuto(double velocidad, double aceleracion, double AnguloDeGiro, String patente){
        if(vehiculos == null)
            vehiculos.add(new Autos(patente,velocidad,AnguloDeGiro,aceleracion));
        else if (vehiculos.size() < cantidadDeVehiculosPermitidos)
            vehiculos.add(new Autos(patente, velocidad, AnguloDeGiro, aceleracion));

    }
    public void darDeAltaMoto(double velocidad, double aceleracion, double AnguloDeGiro, String patente){
        if(vehiculos.size() < cantidadDeVehiculosPermitidos)
            vehiculos.add(new Motos(patente,velocidad,AnguloDeGiro,aceleracion));
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        vehiculos.remove(vehiculo);
    }
    public void eliminarVehiculoConPatente(String patente){
        for(int i = 0; i<vehiculos.size();i++){
            if(vehiculos.get(i).patente == patente)
                vehiculos.remove(i);
        }
    }

    public Vehiculo definirGanador(){
        HashMap<Double ,Vehiculo>  resultados = new HashMap<>();
        for (int i =0; i< vehiculos.size(); i++){
            double value= vehiculos.get(i).getVelocidad()* 1/2 * vehiculos.get(i).getAceleracion()/(vehiculos.get(i).getAnguloDeGiro()*(vehiculos.get(i).getPeso()-vehiculos.get(i).getRuedas()*100));
                resultados.put(value,vehiculos.get(i));
        }
        double maxValueInMap = Collections.max(resultados.keySet());
        System.out.println("Ganador: " + resultados.get(maxValueInMap).patente);
        return resultados.get(resultados.get(maxValueInMap));
    }

    private Vehiculo consultarPorPatente(String patente){
        for (int i = 0; i < vehiculos.size(); i++){
            if (vehiculos.get(i).patente == patente)
                return vehiculos.get(i);
        }
        return  null;
    }

    public void socorrerAuto(String patente){
        Vehiculo vehiculo = consultarPorPatente(patente);
        if(vehiculo.getClass().getSimpleName() == "Autos"){
            socorristaAuto.socorrer((Autos) vehiculo);
        }
    }

    public void socorrerMoto(String patente){
        Vehiculo vehiculo = consultarPorPatente(patente);
        if(vehiculo.getClass().getSimpleName() == "Motos"){
            socorristaMoto.socorrer((Motos) vehiculo);
        }
    }

}
