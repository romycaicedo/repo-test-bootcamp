import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String[] args){
        Prenda prenda1 = new Prenda("AE", "Jeans");
        Prenda prenda2 = new Prenda("UA","chaqueta");
        List<Prenda> prenda = new
                ArrayList<>();
        prenda.add(prenda1);
        prenda.add(prenda2);
        GuardaRopa gr = new GuardaRopa();
        gr.guardarPrendas(prenda);

        Prenda prenda3 = new Prenda("SHEIN", "Jeans");
        Prenda prenda4 = new Prenda("HM","chaqueta");
        List<Prenda> prendas2 = new
                ArrayList<>();
        prendas2.add(prenda1);
        prendas2.add(prenda2);

        gr.guardarPrendas(prendas2);
        //gr.devolverPrendas(gr.dic.keys().nextElement());
        gr.mostrarPrendas();


    }
}
