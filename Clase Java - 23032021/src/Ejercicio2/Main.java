package Ejercicio2;

public class Main {

    public static void main (String[] args){

    Circulo circulo = new Circulo();
    circulo.setRadio(10);
    Triangulo triangulo = new Triangulo();
    triangulo.setAltura(2);
    triangulo.setBase(2);
    Rectangulo rectangulo = new Rectangulo();
    rectangulo.setAltura(2);
    rectangulo.setBase(2);
    FiguraGeometrica[] figuras = new FiguraGeometrica[3];
    figuras[0] = circulo;
    figuras[1]= triangulo;
    figuras[2] = rectangulo;

    Util.areaPromedio(figuras);


    }
}
