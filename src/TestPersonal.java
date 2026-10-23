import java.util.List;
import java.util.Scanner;

public class TestPersonal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in); // hacemos la variable teclado como scanner

        try {
            System.out.print(
                    "Introduce la ruta del archivo CSV, XML o JSON: "
            );
            // La variable ruta es la ruta del archivo que queramos leer.
            String ruta = teclado.nextLine().trim();

            List<Persona> lista;
            String rutaMinusculas = ruta.toLowerCase();


            if (rutaMinusculas.endsWith(".csv")) { // Utilizamos el metodo endsWith que nos comprueba la extensión del archivo.
                lista = Auxiliar.leerCSV(ruta); // Segun la extension del archivo usamos un metodo o otro y metemos el resultado en la variable lista

            } else if (rutaMinusculas.endsWith(".xml")) {
                lista = Auxiliar.leerXML(ruta);

            } else if (rutaMinusculas.endsWith(".json")) {
                lista = Auxiliar.leerJSON(ruta);

            } else {
                System.out.println(
                        "Formato no válido. Usa CSV, XML o JSON." // Si la extension no coincide con ninguna de las expecificadas
                );
                return;
            }

            Personal personal = new Personal(lista); // Le pasamos a personal la lista que hemos leido previamente como parametro para crear una lista que a su vez con el metodo addPersona lo mete en el hasmap

            menu(teclado, personal); // utilizamos el metodo menu para que muestre las opciones disponibles

            Auxiliar.escribirCSV(personal.getPersonas()); //lo ponemos fuera del main para que reescriba el csv al utilizar el programa

        } catch (Exception e) {
            System.out.println(
                    "Error al procesar el archivo: " + e.getMessage()
            );

        } finally {
            teclado.close();
        }
    }

    public static void menu(Scanner teclado, Personal personal) {

        int opcion;

        do {
            System.out.println("\n===== MENÚ PERSONAL =====");
            System.out.println("1. Mostrar personas por país");
            System.out.println("2. Borrar persona por email");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            String entrada = teclado.nextLine().trim();

            try {
                opcion = Integer.parseInt(entrada); // convertimos a numero la entrada por teclado

            } catch (NumberFormatException e) {
                System.out.println(
                        "Debes introducir un número válido."
                );
                opcion = -1;
                continue;
            }

            switch (opcion) {

                case 1:
                    System.out.print("Introduce el país: ");

                    String country = teclado.nextLine().trim();

                    List<Persona> resultado =
                            personal.getPersonasByCountry(country);

                    if (resultado.isEmpty()) {
                        System.out.println(
                                "No hay personas de ese país."
                        );
                    } else {
                        for (Persona persona : resultado) {
                            System.out.println(persona);
                        }
                    }

                    break;

                case 2:
                    System.out.print(
                            "Introduce el email de la persona: "
                    );

                    String email = teclado.nextLine().trim();

                    boolean eliminada =
                            personal.deletePersonaByEmail(email);

                    if (eliminada) {
                        System.out.println(
                                "Persona eliminada correctamente."
                        );
                    } else {
                        System.out.println(
                                "No se encontró ninguna persona con ese email."
                        );
                    }

                    break;

                case 0:
                    System.out.println(
                            "Saliendo del programa..."
                    );
                    break;

                default:
                    System.out.println(
                            "Opción no válida. Inténtalo de nuevo."
                    );
            }

        } while (opcion != 0);
    }
}