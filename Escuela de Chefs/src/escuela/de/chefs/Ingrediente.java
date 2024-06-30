package escuela.de.chefs;

public class Ingrediente {
    private String nombre;
    private int cantidad;
    private boolean indispensable;

    public Ingrediente(String nombre, int cantidad, boolean indispensable) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.indispensable = indispensable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isIndispensable() {
        return indispensable;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
