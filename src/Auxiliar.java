
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

    public class Auxiliar {

        // 1. Leer CSV
        public static List<Persona> leerCSV(String ruta)
                throws IOException {

            List<Persona> lista = new ArrayList<>();

            try (BufferedReader lector =
                         new BufferedReader(new FileReader(ruta))) {

                String linea;

                while ((linea = lector.readLine()) != null) {

                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    // Separar las cinco columnas
                    String[] datos = linea.split(",", 5);

                    
                    if (datos[0].trim().equalsIgnoreCase("firstName")) { // saltamos los nombres de las columnas
                        continue;
                    }

                    if (datos.length != 5) {
                        throw new IOException(
                                "Fila CSV incorrecta: " + linea
                        );
                    }

                    Persona persona = new Persona(
                            datos[0].trim(),
                            datos[1].trim(),
                            datos[2].trim(),
                            datos[3].trim(),
                            datos[4].trim()
                    );

                    lista.add(persona);
                }
            }

            return lista;
        }


        // 2. Escribir CSV con fecha y hora
        public static void escribirCSV(List<Persona> personas)
                throws IOException {

            String fechaHora = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String nombreArchivo = "personal_" + fechaHora + ".csv";

            try (BufferedWriter escritor =
                         new BufferedWriter(new FileWriter(nombreArchivo))) {

                escritor.write(
                        "firstName,lastName,country,gender,email"
                );

                escritor.newLine();

                for (Persona persona : personas) {
                    escritor.write(persona.toString());
                    escritor.newLine();
                }
            }

            System.out.println(
                    "Datos guardados en: " + nombreArchivo
            );
        }


        // 3. Leer XML
        public static List<Persona> leerXML(String ruta)
                throws Exception {

            List<Persona> lista = new ArrayList<>();

            Document documento = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new File(ruta));

            documento.getDocumentElement().normalize();

            NodeList nodos =
                    documento.getElementsByTagName("persona");

            JAXBContext contexto =
                    JAXBContext.newInstance(Persona.class);

            Unmarshaller lector = contexto.createUnmarshaller();

            for (int i = 0; i < nodos.getLength(); i++) {

                Element elemento = (Element) nodos.item(i);

                Persona persona =
                        (Persona) lector.unmarshal(elemento);

                lista.add(persona);
            }

            return lista;
        }


        // 4. Leer JSON
        public static List<Persona> leerJSON(String ruta)
                throws IOException {

            Gson gson = new Gson();

            try (FileReader lector = new FileReader(ruta)) {

                Persona[] array =
                        gson.fromJson(lector, Persona[].class);

                List<Persona> lista = new ArrayList<>();

                if (array != null) {
                    for (Persona persona : array) {
                        lista.add(persona);
                    }
                }

                return lista;
            }
        }
    }

