/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Estado;
import bean.Ingrediente;
import bean.Mensaje;
import bean.Pizza;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.ConexionMLab;

/**
 *
 * @author fixt
 */
public class CocinaDAO {
    
    private Estado gEstado = null;
    
    public List<Mensaje> getMensajes(){
        int estado;
        List<Mensaje> mensajes = null;
        Mensaje mensaje = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            mensajes = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                estado = (Integer)((DBObject)dbo.get("Estado")).get("id");
                if ( estado == 1 || estado == 0  ){
                    mensaje = new Mensaje((String)((DBObject)dbo.get("Estado")).get("fechahora"),estado,(Integer)dbo.get("id"));
                    mensajes.add(mensaje);                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return mensajes;
        
        
        
    }
    
    public List<Pizza> getPizza(int id){
        List<Pizza> pizzas = null;
        List<Ingrediente> ingredientes = null;
        Pizza pizza = null;
        Ingrediente ingred = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            pizzas = new ArrayList<>();            
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                BasicDBList dbo1 = (BasicDBList)dbo.get("Pizzas");
                for (Object piz : dbo1) {
                             
                DBObject dbb = DBObject.class.cast(piz);
                
                BasicDBList dbo2 = (BasicDBList) dbb.get("Ingredientes");
                ingredientes = new ArrayList<>();
                for (Object ing : dbo2) {                    
                    DBObject dbo5 = DBObject.class.cast(ing);
                    ingred = new Ingrediente((Integer) dbo5.get("id"),(String) dbo5.get("nombre"));                    
                    ingredientes.add(ingred);
                }                
                pizza = new Pizza((String)dbb.get("nombrePizza"),ingredientes,(String)dbb.get("url"),(String)dbb.get("Tamano"));       
                pizzas.add(pizza);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizzas;  
        
        
    }
    
    public Integer actualizarEstado(int idPedido) {
        int idestado;
        ConexionMLab con = new ConexionMLab();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fecha = new Date();
        MongoClient mongo = con.getConexion();
        int fields = 0;
                
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject(); 
            query1.put("$eq", idPedido);
            query.put("id", query1);
            
            DBCursor cursor = coleccion.find(query);
            DBObject dbo = cursor.next();
            idestado=(Integer)((DBObject)dbo.get("Estado")).get("id");
            gEstado = new Estado();
            switch(idestado+1){  
                case 1:
                    gEstado.setId(1);
                    gEstado.setEstado("En Proceso");
                    gEstado.setFechahora(df.format(fecha));
                    break;
                case 2:
                    gEstado.setId(2);
                    gEstado.setEstado("Preparado");
                    gEstado.setFechahora(df.format(fecha));
                    break;
                case 3:
                    gEstado.setId(2);
                    gEstado.setEstado("Preparado");
                    gEstado.setFechahora(df.format(fecha));
                    break;
                /*case 3:
                    gEstado.setId(3);
                    gEstado.setEstado("En Camino");
                    gEstado.setFechahora(df.format(fecha));
                    break;
                case 4:
                    gEstado.setId(4);
                    gEstado.setEstado("Entregado");
                    gEstado.setFechahora(df.format(fecha));
                    break;
                case 5:
                    gEstado.setId(4);
                    gEstado.setEstado("Entregado");
                    gEstado.setFechahora(df.format(fecha));
                    break;*/
            }
            DBObject dbo3 = new BasicDBObject();
            dbo3.put("fechahora",gEstado.getFechahora());
            dbo3.put("id",gEstado.getId());
            dbo3.put("estado",gEstado.getEstado());
            DBObject dbo4 = new BasicDBObject();
            dbo4.put("Estado", dbo3);
            DBObject dbo5 = new BasicDBObject();
            dbo5.put("$set",dbo4);

            //fields = 
            coleccion.update(query,dbo5).getN();
            fields = idestado+1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }   
        return fields;
        
    }
    
}
