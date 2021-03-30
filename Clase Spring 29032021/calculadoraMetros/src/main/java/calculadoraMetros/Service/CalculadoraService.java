package calculadoraMetros.Service;

import calculadoraMetros.dto.HabitacionDTO;

import java.util.List;

public class CalculadoraService {

        public  Double calcularArea(List<HabitacionDTO> habitaciones){

            double aux=0;
            for(HabitacionDTO habitacion: habitaciones){
                aux+=habitacion.getAncho() * habitacion.getLargo();
            }
            return aux;
        }
        public  Double calcularArea(HabitacionDTO habitaciones){
            return habitaciones.getLargo()*habitaciones.getAncho();
        }
        public Double valorCasa(double area){
            return area * 800;
        }
        public  HabitacionDTO obtenerHabitacionMax(List<HabitacionDTO> habitaciones){

            HabitacionDTO habitacion = new HabitacionDTO();
            double max=0;
            for(int i=0; i<habitaciones.size(); i++){
                if(calcularArea(habitaciones.get(i))>max){
                    habitacion=habitaciones.get(i);
                    max=calcularArea(habitaciones.get(i));
                }
            }
            return habitacion;
        }
        public double[] obtenerMetro2(List<HabitacionDTO> habitaciones){

            double arr[] = new double[habitaciones.size()];
            for(int i=0; i<habitaciones.size(); i++){
                arr[i]=calcularArea(habitaciones.get(i));
            }
            return arr;
        }

    }

