package bean;

/**
 * Created by fixt on 10/05/16.
 */
public class Estado {

    public static final String NOMBRE_ID0 = "En cola";
    public static final String NOMBRE_ID1 = "En proceso";
    public static final String NOMBRE_ID2 = "Terminado";
    public static final String NOMBRE_ID3 = "En camino";
    public static final String NOMBRE_ID4 = "Entregado";

    private int id;
    private String fechaHora;
    private String username;

    public Estado() {
    }

    public Estado(int id, String fechaHora, String username) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return getNombrePorId(id);
    }
    
    public static String getNombrePorId(int id) {
        switch (id) {
            case 0:
                return NOMBRE_ID0;
            case 1:
                return NOMBRE_ID1;
            case 2:
                return NOMBRE_ID2;
            case 3:
                return NOMBRE_ID3;
            case 4:
                return NOMBRE_ID4;
            default:
                return "";
        }
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
