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
}
