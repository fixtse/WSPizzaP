package bean;

import java.util.List;

/**
 * Created by Admin on 9/05/2016.
 */
public class Pedido {
    private int id;
    private Estado estado;
    private Usuario usuario;
    private String direccion;
    private float monto;
    private List<Pizza> pizzas;
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(int id, Estado estado, Usuario usuario, String direccion, float monto, List<Pizza> pizzas, List<Producto> productos) {
        this.id = id;
        this.estado = estado;
        this.usuario = usuario;
        this.direccion = direccion;
        this.monto = monto;
        this.pizzas = pizzas;
        this.productos=productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
}
