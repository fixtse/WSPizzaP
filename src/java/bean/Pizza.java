package bean;


import bean.Ingrediente;
import java.util.List;

/**
 * Created by fixt on 05/05/16.
 */
public class Pizza {
    private int id, tipo;
    private String nombrePizza;
    private List<Ingrediente> ing;
    private String img;
    private List<Tamano> tam; //solo para pizzas definidas
    private String tamano; //solo para pizzas personalizadas
    private float precio;//solo para pizzas personalizadas

       public Pizza() {
    }

    public Pizza(List<Ingrediente> ing, String tamano, float precio) {
        this.ing = ing;
        this.tamano = tamano;
        this.precio = precio;
    }

    
  public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }   

    public Pizza(int id, String nombrePizza, List<Ingrediente> ing, List<Tamano> tam) {
        this.id = id;
        this.nombrePizza = nombrePizza;
        this.ing = ing;
        this.tam = tam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePizza() {
        return nombrePizza;
    }

    public void setNombrePizza(String nombrePizza) {
        this.nombrePizza = nombrePizza;
    }

    public List<Ingrediente> getIng() {
        return ing;
    }

    public void setIng(List<Ingrediente> ing) {
        this.ing = ing;
    }

    public List<Tamano> getTam() {
        return tam;
    }

    public void setTam(List<Tamano> tam) {
        this.tam = tam;
    }
}
