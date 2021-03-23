package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args ){

        //punto1();
        //punto2();
        //punto3();
        //punto4();
        punto5();

    }

        public static void punto1(){
            System.out.print("Ingrese la cantidad de números:");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();

            for(int i=0; i<n*2; i++){
                if(i%2==0){
                    System.out.print("\n"+i);
                }

        }

    }


    public static void punto2(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de números:");
        int n = scanner.nextInt();

        System.out.print("Ingrese el número:");
        int m = scanner.nextInt();

        for(int i=1; i<=n; i++){
            if(m%i==0){
                System.out.print("\n"+i);
            }

        }

    }

    public static void punto3(){

        Scanner scanner = new Scanner(System.in);
        boolean esPrimo = false;

        System.out.print("Ingrese el número:");
        int n = scanner.nextInt();

        for (int i=2; i<=n/2; i++){
            if (n%i ==0){
                esPrimo= true;
                break;
            }

        }

        if (!esPrimo)
            System.out.println(n + " es número primo.");
        else
            System.out.println(n + " no es número primo.");
    }

    public static void punto4(){

        Scanner scanner = new Scanner(System.in);
        boolean esPrimo, a;
        int n, j=0, s=2, m;
        System.out.print("Cuantos Números desea generar:");
        n = scanner.nextInt();

        for(int x=1; x <= n; x++){
            a = false;
            while(!a){
                m=2;
                esPrimo = true;

                while ((esPrimo) && (m < s )){
                    if (s % m == 0)
                        esPrimo = false;
                    else
                        m++;
                }
                if(esPrimo){
                    j++;
                    System.out.println(s);
                    a=true;
                }
                s++;
            }
        }



    }
    public static void punto5(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cuantos Números desea generar:");
        int n = scanner.nextInt();
        System.out.print("Cuantos digitos:");
        int m = scanner.nextInt();
        System.out.print("Cual digito:");
        int d = scanner.nextInt();
        int  counter = 0;
        for(int i = 0; ; i++) {
            String numeros = Integer.toString(i);
            List<Integer[]> lista = new ArrayList<Integer[]>();
            List<Integer[]> listaRetorno = contador(numeros, lista);
            if (listaRetorno != null) {
                for (Integer[] fila : listaRetorno) {
                    //System.out.println(fila[0] + "=" + fila[1]);
                    if (fila[0].equals(d) && fila[1].equals(m)) {
                        System.out.println(numeros);
                        counter++;
                    }
                }
            }
            if(counter >= n)
                break;
        }



    }

    public static List<Integer[]> contador(String numeros, List<Integer[]> lista) {
        if (numeros == null) {
            return null;
        }

        numeros = numeros.trim();
        if (numeros.equals("")) {
            return lista;
        } else {
            String numero = numeros.substring(0,1);
            char[] arrayNum = numeros.toCharArray();
            int count = 0;
            for (Character num : arrayNum) {
                if (num.toString().equals(numero)) {
                    count++;
                }
            }
            if (count==1) {
                count=0;
            }
            Integer[] fila = new Integer[2];
            fila[0] = Integer.parseInt(numero);
            fila[1] = count;
            lista.add(fila);
            numeros = numeros.replaceAll(numero, " ");
        }
        return contador(numeros, lista);
    }

}