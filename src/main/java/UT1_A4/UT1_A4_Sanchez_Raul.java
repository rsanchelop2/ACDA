package UT1_A4;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class UT1_A4_Sanchez_Raul {
    public static void main(String[] args) {
        // Pide al usuario la ruta absoluta del fichero csv
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta del fichero: ");
        String rutaFiche = sc.nextLine();
        File fichero = new File(rutaFiche);

        // Comprueba si existe o si es un directorio
        if (!fichero.exists() || fichero.isDirectory()) {
            System.out.println("El fichero no existe o es un directorio.");
            return;
        }

        int numCampos = 0;
        int posEdad = -1;
        int sumEdades = 0;
        int totalPersonas = 0;

        // Comprobar si el archivo es CSV
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            String primeraLinea = br.readLine();

            if (primeraLinea != null) {
                String[] cabecera = primeraLinea.split(";");
                numCampos = cabecera.length;
                System.out.println("El fichero CSV tiene " + numCampos + " campos.");

                // Buscar si existe el campo edad
                for (int i = 0; i < cabecera.length; i++) {
                    if (cabecera[i].trim().equalsIgnoreCase("edad")) {
                        posEdad = i;
                        break;
                    }
                }

                // Si existe edad leemos las siguientes filas para calcular la media
                if (posEdad != -1) {
                    String ln;
                    while ((ln = br.readLine()) != null) {
                        String[] edades = ln.split(";");
                        if (edades.length > posEdad) {
                            sumEdades += Integer.parseInt(edades[posEdad].trim());
                            totalPersonas++;
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
            return;
        }

        // Escribe los resultados en resultado.csv en la misma ruta
        File dcResul = fichero.getParentFile();
        File fichResul = new File(dcResul, "resultado.csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichResul))) {

            if (posEdad != -1 && totalPersonas > 0) {
                double mediaEdad = (double) sumEdades / totalPersonas;
                bw.write("num_campos;media_edad");
                bw.newLine();
                bw.write(numCampos + ";" + mediaEdad);
            } else { // si no hay edad da igualmente el numero de campos
                bw.write("num_campos");
                bw.newLine();
                bw.write(String.valueOf(numCampos));
            }

            System.out.println("Resultados guardados en: " + fichResul.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero resultado");
        }

        sc.close();
    }


}
