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
    private String url;
    private List<Tamano> tam; //solo para pizzas definidas
    private String tamano; //solo para pizzas personalizadas
    private float precio;//solo para pizzas personalizadas

       public Pizza() {
    }

    public Pizza(String nombrePizza, List<Ingrediente> ing, String url, String tamano) {
        this.nombrePizza = nombrePizza;
        this.ing = ing;
        this.url = url;
        this.tamano = tamano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Tamano> getTam() {
        return tam;
    }

    public void setTam(List<Tamano> tam) {
        this.tam = tam;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    

    
  
}
