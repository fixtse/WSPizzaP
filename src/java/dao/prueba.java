/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Estado;
import bean.Pedido;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Diego Torres
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Estado est = new Estado();
        est.setEstado("En camino");
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        est.setFechahora(hourdateFormat.format(date));
        est.setId(1);
        
        PedidoDAO pedao = new PedidoDAO();
        int i = 0;
        i= pedao.actualizarEstado(est, 2);
        
        System.out.println(i);
    }
    
}
