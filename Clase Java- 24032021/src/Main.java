public class Main {

    public static void main (String[] args){
       Persona persona = new Persona();
       persona.setDni(123456);
       persona.setNombre("Pepe");

        Persona persona2 = new Persona();
        persona2.setDni(1234567);
        persona2.setNombre("Pepe");


        Precedable<Persona> arr[] = new Precedable[2];
       arr[0] = persona;
       arr[1] = persona2;

       SortUtil.ordenar(arr);

       

    }
}
