
package bean;

/**
 *
 * @author Diego Torres
 */
public class Tamano {
    private int id;
    private String nombre;
    private int slices;
    private int precio;

    public Tamano() {
    }

    public Tamano(int id, String nombre, int slices) {
        this.id = id;
        this.nombre = nombre;
        this.slices = slices;
    }
    

    public Tamano(int id, String nombre, int slices, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.slices = slices;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSlices() {
        return slices;
    }

    public void setSlices(int slices) {
        this.slices = slices;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    

    
}
