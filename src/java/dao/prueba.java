/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Mensaje;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Torres
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Mensaje> mens = new ArrayList<>();
        CocinaDAO dao = new CocinaDAO();
        
        
        mens= dao.getMensajes();
        
        //System.out.println(mens.get(0).getHora());
        System.out.println(dao.actualizarEstado(2, "dtorres","San Borja"));
    }
    
}
