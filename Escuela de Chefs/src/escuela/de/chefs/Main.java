package escuela.de.chefs;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static IngredienteDAO ingredienteDAO = new IngredienteDAO();

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Administrador");
            System.out.println("2. Profesor");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    menuAdministrador();
                    break;
                case 2:
                    menuProfesor();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void menuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú Administrador:");
            System.out.println("1. Agregar Ingrediente");
            System.out.println("2. Quitar Ingrediente");
            System.out.println("3. Consultar Inventario");
            System.out.println("4. Orden de Compra");
            System.out.println("5. Volver al Menú Principal");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarIngrediente();
                    break;
                case 2:
                    quitarIngrediente();
                    break;
                case 3:
                    consultarInventario();
                    break;
                case 4:
                    // Lógica para orden de compra
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void agregarIngrediente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del ingrediente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la cantidad del ingrediente:");
        int cantidad = scanner.nextInt();

        System.out.println("¿El ingrediente es indispensable? (1 para Sí, 2 para No):");
        int indispensableOpcion = scanner.nextInt();
        boolean indispensable = indispensableOpcion == 1;

        Ingrediente ingrediente = new Ingrediente(nombre, cantidad, indispensable);
        ingredienteDAO.agregarIngrediente(ingrediente);

        System.out.println("Ingrediente agregado exitosamente.");
    }

    public static void quitarIngrediente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del ingrediente a quitar:");
        String nombre = scanner.next();

        ingredienteDAO.quitarIngrediente(nombre);

        System.out.println("Ingrediente quitado exitosamente.");
    }

    public static void consultarInventario() {
        List<Ingrediente> inventario = ingredienteDAO.consultarInventario();
        System.out.println("Inventario actual:");
        for (Ingrediente ingrediente : inventario) {
            System.out.println("Nombre: " + ingrediente.getNombre() +
                               ", Cantidad: " + ingrediente.getCantidad() +
                               ", Indispensable: " + (ingrediente.isIndispensable() ? "Sí" : "No"));
        }
    }

    public static void menuProfesor() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú Profesor:");
            System.out.println("1. Quitar Ingrediente");
            System.out.println("2. Volver al Menú Principal");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    quitarIngrediente();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
