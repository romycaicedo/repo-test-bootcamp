package Ejercicio2;

public class Triangulo extends  FiguraGeometrica{
    public Triangulo() {
    }

    double base;
    double altura;
    double area;

    public double getBase() {
        return base;
    }
    public void setBase(double base) {
        this.base = base;
    }
    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double area() {
        area = base *altura/2;
        System.out.println(toString());
        return area;
    }

    @Override
    public String toString() {
        return "Triangulo " +
                "area= " + area ;
    }
}
