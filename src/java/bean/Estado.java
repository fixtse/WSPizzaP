package bean;

/**
 * Created by fixt on 10/05/16.
 */
public class Estado {
    private String fechahora;
    private int id;
    private String estado;

    public Estado() {
    }

    public Estado(String fechahora, int id, String estado) {
        this.fechahora = fechahora;
        this.id = id;
        this.estado = estado;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
