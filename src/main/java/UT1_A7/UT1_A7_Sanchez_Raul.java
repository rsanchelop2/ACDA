package UT1_A7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UT1_A7_Sanchez_Raul {
    public static void main(String[] args) {
        // Leer y mostrar el contenido del archivo JSON (incluyendo los atributos nulos).
        String rutaArchivo = "src/main/java/UT1_A7/A7_Recursos.json";

        StringBuilder sb = new StringBuilder();

        try  (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            while (br.ready()){
                sb.append(br.readLine());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(sb);


        // Hacer un sumatorio de los salarios de los empleados que están activos.
        int sumaSlarios = 0;
        JSONObject jO = new JSONObject(sb.toString());
        JSONArray listaEmpleados = jO.getJSONArray("empleados");

        for (int i = 0; i < listaEmpleados.length(); i++) {
            JSONObject user = listaEmpleados.getJSONObject(i);

            if (user.getBoolean("activo")) {
                if (!user.isNull("salario")) {
                    int salario = user.getInt("salario");
                    sumaSlarios = sumaSlarios + salario;
                }
            }
        }
        System.out.println("Suma salarios empleados activos: " + sumaSlarios);



        // Contar el número total de proyectos que tiene la empresa.
        int numProyectos = jO.getJSONArray("proyectos").length();
        System.out.println("La empresa tiene " + numProyectos + " proyectos");



        // Escribir en un nuevo fichero JSON, toda la información de los empleados, así como la suma de salarios y el total de proyectos
        JSONObject datos = new JSONObject();
        datos.put("empleados", listaEmpleados);
        datos.put("sumaSalarios", sumaSlarios);
        datos.put("cantidadProyectos", numProyectos);

        try (FileWriter file = new FileWriter("datos.json")) {
            file.write(datos.toString(4));
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
