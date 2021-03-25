package Ejercicio2;

public class Circulo extends FiguraGeometrica{
    public Circulo() {
    }

    double radio;
    double area;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double area() {
         area = PI* Math.pow(radio, 2);
         System.out.println(toString());
        return area;
    }

    @Override
    public String toString() {
        return "Circulo " +
                "area= " + area ;
    }
}
