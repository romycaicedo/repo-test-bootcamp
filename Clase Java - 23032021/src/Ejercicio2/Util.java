package Ejercicio2;

import java.awt.*;

public class Util {

    public static double areaPromedio(FiguraGeometrica[] figuras){

        Circulo circulo = new Circulo();
        circulo= (Circulo) figuras[0];



        Triangulo triangulo = new Triangulo();
        triangulo = (Triangulo) figuras[1];



        Rectangulo rectangulo = new Rectangulo();
        rectangulo = (Rectangulo) figuras[2];



        return (circulo.area() + rectangulo.area() + triangulo.area())/3;
    }


}
