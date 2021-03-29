
import java.util.*;


public class GuardaRopa {
    Dictionary<Integer, List<Prenda>> dic = new Hashtable();
    int counter =0;

    public Integer guardarPrendas(List<Prenda> listaDePrendas){
        Random random = new Random();
        int identificador = 1;
        if(dic.keys().equals(identificador))
        dic.put( identificador+1, listaDePrendas);
        else
            dic.put( identificador, listaDePrendas);
        return identificador;
    }

    public void mostrarPrendas(){
        for(int j = 0; j<dic.size();j++){
            List<Prenda> prendas= dic.elements().nextElement();
            for (int i=0; i<prendas.size();i++){
                System.out.println("Prenda: "+ dic.keys().nextElement() +" Marca: " + prendas.get(i).getMarca() + " Modelo: " + prendas.get(i).getModelo() );
            }

        }

    }

    public List<Prenda> devolverPrendas(Integer numero){
        return dic.remove(numero);
    }


}
