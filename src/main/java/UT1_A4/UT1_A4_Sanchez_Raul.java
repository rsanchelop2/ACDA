package UT1_A4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UT1_A4_Sanchez_Raul {
    public static void main(String[] args) {
        // Pedir a usuario una ruta a un directorio
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta: ");
        String ruta_directorio = sc.nextLine();
        File directorio = new File(ruta_directorio);

        // Listar el contenido del directorio
        if (directorio.exists() & directorio.isDirectory()){
            System.out.println(Arrays.toString(directorio.list()));
        } else {
            System.out.println("El directorio no existe");
        }

        // Pedir el nombre de un fichero (con extension) contenido en esa ruta / comprobar si existe
        System.out.print("Introduce el nombre del fichero: ");
        String fichero_in = sc.nextLine();
        String ruta_abs_fichero = ruta_directorio + "\\" + fichero_in;
        System.out.println(ruta_abs_fichero);
        File fichero = new File(ruta_abs_fichero);
        if (fichero.exists()){
            System.out.println("El fichero " + fichero_in + " existe");
        }

        // Comprobar si el archivo es CSV
        if (fichero.getName().toLowerCase().endsWith(".csv")){
            try (FileReader fr = new FileReader(ruta_abs_fichero);
                 BufferedReader br = new BufferedReader(fr)) {
                String linea = br.readLine();

                String[] num_campos = linea.split(";");
                System.out.println("El fichero CSV tiene " + num_campos.length + " campos");

            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            }

            try (FileReader fr = new FileReader(ruta_abs_fichero);
                 BufferedReader br = new BufferedReader(fr)) {

                String linea = br.readLine();
                String[] num_campos = linea.split(";");

                for (int i = 0; i < num_campos.length; i++) {

                }

            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            }
        }

    }
}
