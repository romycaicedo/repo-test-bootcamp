public class Main {

    public static void main (String[] args){
       Persona arr[] = {
            new Persona("Pepe",10)
                    ,new Persona("Pepe",50)
                            , new Persona("pepe", 30)};



       SortUtil.ordenar(arr);


       for (int i = 0; i<arr.length;i++){
           System.out.println(arr[i]);
       }
       

    }
}
