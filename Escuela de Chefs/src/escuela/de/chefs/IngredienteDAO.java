package escuela.de.chefs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {
    public void agregarIngrediente(Ingrediente ingrediente) {
        String sql = "INSERT INTO ingredientes (nombre, cantidad, indispensable) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ingrediente.getNombre());
            pstmt.setInt(2, ingrediente.getCantidad());
            pstmt.setBoolean(3, ingrediente.isIndispensable());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void quitarIngrediente(String nombre) {
        String sql = "DELETE FROM ingredientes WHERE nombre = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingrediente> consultarInventario() {
        List<Ingrediente> inventario = new ArrayList<>();
        String sql = "SELECT nombre, cantidad, indispensable FROM ingredientes";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                boolean indispensable = rs.getBoolean("indispensable");
                inventario.add(new Ingrediente(nombre, cantidad, indispensable));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }
    
    public Ingrediente buscarIngredientePorNombre(String nombre) {
        String sql = "SELECT nombre, cantidad, indispensable FROM ingredientes WHERE nombre = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                boolean indispensable = rs.getBoolean("indispensable");
                return new Ingrediente(nombre, cantidad, indispensable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el ingrediente
        
}
    public void agregarIngredienteNecesario(Ingrediente ingrediente) {
        // Método para agregar un ingrediente necesario para la clase y gestionar la orden de compra
        Ingrediente existente = buscarIngredientePorNombre(ingrediente.getNombre());

        if (existente != null) {
            int cantidadDisponible = existente.getCantidad();
            if (cantidadDisponible >= ingrediente.getCantidad()) {
                // Suficiente cantidad disponible, simplemente agregar a la lista de necesarios
                System.out.println("El ingrediente está disponible en el inventario.");
            } else {
                // Calcular la cantidad faltante y agregar a la lista de orden de compra
                int cantidadFalta = ingrediente.getCantidad() - cantidadDisponible;
                System.out.println("El ingrediente no tiene suficiente cantidad en el inventario. Se agregará a la orden de compra.");
                // Aquí podrías implementar la lógica para agregar a la orden de compra
            }
        } else {
            System.out.println("El ingrediente no está en el inventario.");
            // Aquí podrías manejar cómo proceder si el ingrediente no está en el inventario
        }
    }
    
    public List<Ingrediente> consultarIngredientesNecesarios() {
        List<Ingrediente> ingredientesNecesarios = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nombre, cantidad FROM ingredientes WHERE indispensable = TRUE");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                ingredientesNecesarios.add(new Ingrediente(nombre, cantidad, true));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredientesNecesarios;
    }
    
}