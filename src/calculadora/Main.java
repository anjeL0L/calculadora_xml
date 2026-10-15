package calculadora;

import java.util.List;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Uso incorrecto. Sintaxis: java calculadora.Main <archivo.xml> <variable1> [variable2]");
            System.exit(1);
        }

        String archivoXML = args[0];
        String var1 = args[1];
        String var2 = (args.length == 3) ? args[2] : null;

        try {
            List<Double> datosVar1 = LectorXML.extraerVariable(archivoXML, var1);
            imprimirResultados(var1, datosVar1);

            if (var2 != null) {
                List<Double> datosVar2 = LectorXML.extraerVariable(archivoXML, var2);
                imprimirResultados(var2, datosVar2);
                
                System.out.println("\n--- ANÁLISIS BIVARIADO ---");
                double correlacion = Estadisticas.calcularCorrelacion(datosVar1, datosVar2);
                System.out.printf("Coeficiente de correlación (r): %.4f\n", correlacion);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: La variable seleccionada contiene datos no numericos.");
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al procesar el XML: " + e.getMessage());
        }
    }

    private static void imprimirResultados(String nombre, List<Double> datos) {
        System.out.println("\n--- ESTADÍSTICAS PARA: " + nombre + " ---");
        System.out.printf("Media: %.4f\n", Estadisticas.calcularMedia(datos));
        System.out.printf("Varianza: %.4f\n", Estadisticas.calcularVarianza(datos));
        System.out.printf("Desviación estándar: %.4f\n", Estadisticas.calcularDesviacionEstandar(datos));
        System.out.printf("Valor máximo: %.4f\n", Estadisticas.obtenerMaximo(datos));
        System.out.printf("Valor mínimo: %.4f\n", Estadisticas.obtenerMinimo(datos));
    }
}