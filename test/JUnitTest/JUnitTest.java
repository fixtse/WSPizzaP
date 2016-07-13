/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTest;

import dao.CocinaDAO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fixt
 */
public class JUnitTest {
    
    CocinaDAO cDAO = new CocinaDAO();
    
 
  @Test
  public void testObtenerEstado(){
      int res = cDAO.obtenerEstado(5);
      boolean b = false;
      System.out.println(res);
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
      
      if (red < 2){
          if (red == EstadoAnterior+1){
          actEstado = true;
          }          
      }else{
          if (red == EstadoAnterior){
          actEstado = true;
              System.out.println("Se llegó al número máximo de estado por cocina");
          } 
      }
      
      
      assertEquals("Actualización de estado correcta",true, actEstado);
      
      
  }
    
}
