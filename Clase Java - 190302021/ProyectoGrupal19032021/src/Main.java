import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main (String[] args){
        /*CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(50000);
        cuenta.transferencia(30000);
        cuenta.ingreso(10000);
        cuenta.egreso(400);
        cuenta.reintegro(400);
        System.out.println(cuenta.getSaldo());*/

        /*Contador contador = new Contador();
        contador.setCounter(1);
        contador.incrementar(3);
        contador.decrementar(2);
        System.out.println(contador.getCounter());*/

        /*Libro libro = new Libro();
        libro.setTitulo("Inferno");
        libro.setAutor("Umberto Eco");
        libro.setId(1);
        libro.setEstado(false);
        libro.prestamo();
        libro.devolucion();
        System.out.println(libro.toString());*/

        /*Fraccion x = new Fraccion(1,2);
        Fraccion y = new Fraccion(1,3);

        System.out.println("El resultado es: "+ Fraccion.sumar(x,y));
        System.out.println("El resultado es: "+ Fraccion.resta(x,y));
        System.out.println("El resultado es: "+ Fraccion.multiplicar(x,y));
        System.out.println("El resultado es: "+ Fraccion.dividir(x,y));

        System.out.println("El resultado es: "+ Fraccion.sumar(x,3));
        System.out.println("El resultado es: "+ Fraccion.multiplicar(x,3));
        System.out.println("El resultado es: "+ Fraccion.resta(x,3));
        System.out.println("El resultado es: "+ Fraccion.dividir(x,3));*/

        Fecha fecha = new Fecha();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(GregorianCalendar.YEAR, 2019);

        // In gregorian calender month is started from 0
        // so for april month will be 03 not 04
        calendar.set(GregorianCalendar.MONTH, 03);

        calendar.set(GregorianCalendar.DATE, 03);

        //fecha.setCalendar(calendar);
        System.out.println("Gregorian date: "
                + calendar.getTime());

        Fecha.convert(calendar);


    }
}
