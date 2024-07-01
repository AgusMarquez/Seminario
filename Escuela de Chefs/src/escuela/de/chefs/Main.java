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
            System.out.println("4. Agregar Ingredientes Necesarios para Clase");
            System.out.println("5. Orden de Compra");
            System.out.println("6. Volver al Menú Principal");

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
                    agregarIngredientesParaClase();
                    break;
                case 5:
                    // Lógica para orden de compra
                    break;
                case 6:
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

    public static void agregarIngredientesParaClase() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Agregar Ingredientes Necesarios para Clase:");
        while (true) {
            System.out.println("Ingrese el nombre del ingrediente necesario:");
            String nombre = scanner.nextLine();

            // Consultar el inventario para verificar disponibilidad
            Ingrediente ingredienteEnInventario = ingredienteDAO.buscarIngredientePorNombre(nombre);
            if (ingredienteEnInventario == null) {
                System.out.println("El ingrediente no está disponible en el inventario.");
                continue;
            }

            System.out.println("Ingrese la cantidad necesaria:");
            int cantidadNecesaria = scanner.nextInt();

            // Verificar si hay suficiente cantidad en el inventario
            int cantidadDisponible = ingredienteEnInventario.getCantidad();
            if (cantidadDisponible >= cantidadNecesaria) {
                System.out.println("Cantidad suficiente en el inventario.");
            } else {
                System.out.println("No hay suficiente cantidad en el inventario. Faltan " +
                                   (cantidadNecesaria - cantidadDisponible) + " unidades.");
            }

            // Agregar a la lista de ingredientes necesarios
            Ingrediente ingredienteNecesario = new Ingrediente(nombre, cantidadNecesaria, true);
            ingredienteDAO.agregarIngredienteNecesario(ingredienteNecesario);

            System.out.println("¿Desea agregar otro ingrediente necesario? (s/n)");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
            scanner.nextLine(); // Limpiar el buffer antes de volver a pedir nombre
        }

        // Mostrar lista de ingredientes necesarios
        List<Ingrediente> ingredientesNecesarios = ingredienteDAO.consultarIngredientesNecesarios();
        System.out.println("Ingredientes Necesarios para la Clase:");
        for (Ingrediente ingrediente : ingredientesNecesarios) {
            System.out.println("Nombre: " + ingrediente.getNombre() +
                               ", Cantidad Necesaria: " + ingrediente.getCantidad());
        }

        // Generar y guardar PDF de la orden de compra
        // Aquí se podría implementar la lógica para guardar en PDF, según las bibliotecas disponibles

        System.out.println("Orden de Compra generada y almacenada.");

        // Volver al menú principal
        scanner.nextLine(); // Limpiar el buffer antes de volver al menú
        System.out.println("Presione Enter para volver al Menú Principal...");
        scanner.nextLine(); // Esperar la confirmación del usuario
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
    
    public static void mostrarOrdenCompra(List<Ingrediente> ordenCompra) {
    System.out.println("Orden de Compra:");
    for (Ingrediente ingrediente : ordenCompra) {
        System.out.println("Nombre: " + ingrediente.getNombre() +
                           ", Cantidad a Comprar: " + ingrediente.getCantidad());
    }
}
    
}

