
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personal {

    private Map<Integer, Persona> personas = new HashMap<>();

    // Constructor vacío
    public Personal() {
    }

    // Constructor que recibe una lista de personas
    public Personal(List<Persona> listaPersonas) {
        for (Persona persona : listaPersonas) {
            addPersona(persona);
        }
    }

    // Añadir una persona al HashMap
    public void addPersona(Persona persona) {
        personas.put(persona.hashCode(), persona);
    }

    // Obtener todas las personas como una lista
    public List<Persona> getPersonas() {
        return new ArrayList<>(personas.values());
    }

    // Obtener personas de un país
    public List<Persona> getPersonasByCountry(String country) {
        List<Persona> resultado = new ArrayList<>();

        for (Persona persona : personas.values()) {
            if (persona.getCountry() != null
                    && persona.getCountry().equalsIgnoreCase(country)) {
                resultado.add(persona);
            }
        }

        return resultado;
    }

    // Eliminar una persona por su email
    public boolean deletePersonaByEmail(String email) {
        Integer claveEliminar = null;

        for (Map.Entry<Integer, Persona> entrada : personas.entrySet()) {
            Persona persona = entrada.getValue();

            if (persona.getEmail() != null
                    && persona.getEmail().equalsIgnoreCase(email)) {
                claveEliminar = entrada.getKey();
                break;
            }
        }

        if (claveEliminar != null) {
            personas.remove(claveEliminar);
            return true;
        }

        return false;
    }

    // Cargar personas desde un CSV
    public void leercsv(String ruta) throws IOException {
        List<Persona> lista = Auxiliar.leerCSV(ruta);

        for (Persona persona : lista) {
            addPersona(persona);
        }
    }

    // Cargar personas desde un XML
    public void leerXML(String ruta) throws Exception {
        List<Persona> lista = Auxiliar.leerXML(ruta);

        for (Persona persona : lista) {
            addPersona(persona);
        }
    }

    // Cargar personas desde un JSON
    public void leerJSON(String ruta) throws IOException {
        List<Persona> lista = Auxiliar.leerJSON(ruta);

        for (Persona persona : lista) {
            addPersona(persona);
        }
    }
}








