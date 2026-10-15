package calculadora;

import java.util.Collections;
import java.util.List;

public class Estadisticas {

    public static double calcularMedia(List<Double> datos) {
        double suma = 0;
        for (double val : datos) suma += val;
        return suma / datos.size();
    }

    public static double calcularVarianza(List<Double> datos) {
        double media = calcularMedia(datos);
        double sumaDiferenciasCuadrado = 0;
        for (double val : datos) {
            sumaDiferenciasCuadrado += Math.pow(val - media, 2);
        }
        return sumaDiferenciasCuadrado / datos.size(); 
    }

    public static double calcularDesviacionEstandar(List<Double> datos) {
        return Math.sqrt(calcularVarianza(datos));
    }

    public static double obtenerMaximo(List<Double> datos) {
        return Collections.max(datos); 
    }

    public static double obtenerMinimo(List<Double> datos) {
        return Collections.min(datos);
    }

    public static double calcularCorrelacion(List<Double> x, List<Double> y) {
        if (x.size() != y.size() || x.isEmpty()) return 0;
        
        int n = x.size();
        double sumaX = 0, sumaY = 0, sumaXY = 0, sumaX2 = 0, sumaY2 = 0;

        for (int i = 0; i < n; i++) {
            sumaX += x.get(i);
            sumaY += y.get(i);
            sumaXY += x.get(i) * y.get(i);
            sumaX2 += Math.pow(x.get(i), 2);
            sumaY2 += Math.pow(y.get(i), 2);
        }

        double numerador = (n * sumaXY) - (sumaX * sumaY);
        double denominador = Math.sqrt(((n * sumaX2) - Math.pow(sumaX, 2)) * ((n * sumaY2) - Math.pow(sumaY, 2)));

        return (denominador == 0) ? 0 : numerador / denominador;
    }
}