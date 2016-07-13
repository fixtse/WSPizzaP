/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTest;

import bean.Ingrediente;
import bean.Pizza;
import dao.CocinaDAO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mock;

/**
 *
 * @author fixt
 */
public class JUnitTest {
    
    CocinaDAO cDAO = new CocinaDAO();
    
 
  @Test
  public void testObtenerEstado(){
      int res = cDAO.obtenerEstado(2);
      boolean b = false;
      //System.out.println(res);
      if (res != -1){
          b = true;
      }
      
      assertEquals("Obtener estado funciona",true,b);
  }  
  
  
  @Test
  public void testActualizarEstado(){
      
      boolean actEstado = false;
      
      int EstadoAnterior = cDAO.obtenerEstado(2);
      System.out.println("Estado anterior " +EstadoAnterior);
      int red = cDAO.actualizarEstado(2, "dtorres", "Ate");
      System.out.println("Estado actual "+red);
      
      switch(EstadoAnterior){
          case 0:
              if (red == EstadoAnterior+1){
          actEstado = true;
          } 
          break;
          case 1:
              if (red == EstadoAnterior+1){
          actEstado = true;
          } 
          break;
          default:
              if (red == EstadoAnterior){
          actEstado = true;
              System.out.println("Se llegó al número máximo de estado por cocina");
          }
          
      }
              
      
      
      
      
      assertEquals("Actualización de estado correcta",true, actEstado);
      
      
  }
  
  
  @Test
  public void testObtenerPizzas(){
      List<Pizza> PizzasM = cDAO.getPizza(1);
      
      List<Pizza> Pizzas = new ArrayList<>();
      Pizza pi = new Pizza();
      pi.setId(0);
      pi.setNombrePizza("Pizza americana");
      pi.setTamano("Grande");
      
      List<Ingrediente> Ingredientes = new ArrayList<>();
      Ingrediente ing = new Ingrediente();
      ing.setId(1);
      ing.setNombre("Salsa de tomate");
      
      Ingrediente ing1 = new Ingrediente();
      ing1.setId(2);
      ing1.setNombre("Jamón pizzero");
      
      Ingrediente ing2= new Ingrediente();
      ing2.setId(11);
      ing2.setNombre("Queso Mozzarella");
      
      Ingredientes.add(ing);
      Ingredientes.add(ing1);
      Ingredientes.add(ing2);
      
      pi.setIng(Ingredientes);
      
      Pizzas.add(pi);
      
      
      
      Boolean resp=false;
      
      if(PizzasM.get(0).getNombrePizza().equals(Pizzas.get(0).getNombrePizza()) &&
        PizzasM.get(0).getId() == Pizzas.get(0).getId() &&
        PizzasM.get(0).getTamano().equals(Pizzas.get(0).getTamano()) &&
        PizzasM.get(0).getIng().get(0).getNombre().equals(Pizzas.get(0).getIng().get(0).getNombre()) &&
        PizzasM.get(0).getIng().get(0).getId() == Pizzas.get(0).getIng().get(0).getId() &&     
        PizzasM.get(0).getIng().get(1).getNombre().equals(Pizzas.get(0).getIng().get(1).getNombre()) &&
        PizzasM.get(0).getIng().get(1).getId() == Pizzas.get(0).getIng().get(1).getId() &&  
        PizzasM.get(0).getIng().get(2).getNombre().equals(Pizzas.get(0).getIng().get(2).getNombre()) &&
        PizzasM.get(0).getIng().get(2).getId() == Pizzas.get(0).getIng().get(2).getId())
      {
       resp = true;
      }
        
      
      assertEquals("Correcto",true,resp);
      
  }
  
  
  
    
}
