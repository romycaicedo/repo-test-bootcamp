
import java.util.*;


public class GuardaRopa {
    HashMap<Integer, List<Prenda>> dic = new HashMap<>();
    int counter =0;

    public void guardarPrendas(List<Prenda> listaDePrendas){
        dic.put(counter,listaDePrendas);
        counter++;

    }

    public void mostrarPrendas(){
        dic.forEach((k,v)->{
            System.out.println("Lista de prendas " + k + " : ");
            for (Prenda prenda : v) {
                System.out.println("Modelo:" + prenda.modelo + " \n Marca: "+ prenda.marca);
            }
        });

        }


    public List<Prenda> devolverPrendas(Integer numero){
        List<Prenda> prendas = dic.get(numero);
        dic.remove(numero);
        return prendas;
    }


}
