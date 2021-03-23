public class Fraccion {

    public int x;
    public int y;

    public Fraccion() {
    }

    public Fraccion(int x, int y ) {
        this.x= x;
        this.y =y;

    }

    public static Fraccion sumar(Fraccion a, Fraccion b){
        Fraccion c = new Fraccion();
        c.x = a.x * b.y + b.x * a.y;
        c.y = a.y*b.y;
        return c;

    }

    public static Fraccion sumar(Fraccion a, int b){
        Fraccion c = new Fraccion();
        int d = 0;
        c.x = a.x * d + b * a.y;
        c.y = a.y* d;
        return c;

    }

    public static Fraccion resta(Fraccion a, Fraccion b){
        Fraccion c = new Fraccion();
        c.x = a.x*b.y-b.x* a.y;
        c.y = a.y*b.y;
        return c;
    }
    public static Fraccion resta(Fraccion a, int b){
        Fraccion c = new Fraccion();
        int d = 0;
        c.x = a.x* d -b* a.y;
        c.y = a.y*0;
        return c;
    }

    public static Fraccion multiplicar(Fraccion a, Fraccion b){
        Fraccion c = new Fraccion();
        c.x= a.x*b.x;
        c.y= a.y*b.y;
        return c;
    }

    public static Fraccion multiplicar(Fraccion a, int b){
        Fraccion c = new Fraccion();
        int d= 0;
        c.x= a.x*b;
        c.y= a.y*d;
        return c;
    }

    public static Fraccion dividir(Fraccion a, Fraccion b){
        return multiplicar(a,inversa(b));

    }
    public static Fraccion dividir(Fraccion a, int b){
        return multiplicar(inversa(a),b);

    }

    private static Fraccion inversa(Fraccion a){
        Fraccion c = new Fraccion();
        c.x= a.y;
        c.y = a.x;
        return c;
    }

    @Override
    public String toString() {
        String texto=x+" / "+y;
        return texto;
    }
}
