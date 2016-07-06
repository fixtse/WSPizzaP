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
    private List<Pizza> pizzas = null;
    private List<Ingrediente> ingredientes = null;
    private Pizza pizza = null;
    private Ingrediente ingred = null;
    
    public List<Mensaje> getMensajes(){
        int estado;
        List<Mensaje> mensajes = null;
        Mensaje mensaje = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");                     
            DBCursor cursor = coleccion.find();
            mensajes = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                BasicDBList dbo1 = (BasicDBList)dbo.get("estados");
                DBObject est = (DBObject)(dbo1.get(dbo1.size()-1));                
                estado = (Integer)est.get("id");
                if ( estado == 1 || estado == 0  ){
                    mensaje = new Mensaje((String)est.get("fechaHora"),estado,(Integer)dbo.get("_id"));
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
        
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);
            DBCursor cursor = coleccion.find(query);
            pizzas = new ArrayList<>();            
            if (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                //System.out.println("si " + (String)dbo.get("username"));                
                BasicDBList dbo1 = (BasicDBList)((DBObject) dbo.get("productos")).get("promociones");                                    
                    if (dbo1 != null){
                        for (Object promo : dbo1) {
                            DBObject dbo3 = DBObject.class.cast(promo);
                            BasicDBList dboPi = (BasicDBList)dbo3.get("pizzas");
                            for(Object pizza : dboPi){                                 
                                recorrer(DBObject.class.cast(pizza));
                            }

                        }                    
                    }
                    BasicDBList dboP1 = (BasicDBList)((DBObject) dbo.get("productos")).get("pizzas");
                    if (dboP1 != null){
                        for (Object pizza : dboP1) {                            
                            recorrer(DBObject.class.cast(pizza));
                        }
                    }

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pizzas;  
        
        
    
    }
    
     void recorrer(DBObject dbb) {      
        
        
        //(String)dbb.get("url")
        String tam="",nom;
        nom = (String)dbb.get("nombre");
        Eingredientes(nom);
        int t = (Integer)dbb.get("tamanoId");
        if ( t == 1) {
            tam = "Personal";
        }else if(t == 2){
            tam = "Mediana";
        }else if(t == 3){
            tam = "Grande";
        }else if(t == 4){
            tam = "Fiesta";
        }
        pizza = new Pizza(nom,ingredientes," ",tam);       
        for (int i=0;i<(Integer)dbb.get("cantidad");i++){
            pizzas.add(pizza);
        }
        
        
                
    }
     
    void Eingredientes(String nombre){
        
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pizza");
            BasicDBObject query = new BasicDBObject();
            query.put("nombre", nombre);
            ingredientes = new ArrayList<>();
            DBCursor cursor = coleccion.find(query);                        
            if (cursor.hasNext()) {
                DBObject dbo = cursor.next();                                
                BasicDBList dbo2 = (BasicDBList)dbo.get("ingredientes"); 
                
                    if (dbo2 != null){
                        for (Object ing : dbo2) {                    
                            DBObject dbo5 = (DBObject)ing;
                            ingred = new Ingrediente((Integer) dbo5.get("id"),(String)dbo5.get("nombre"));                    
                            ingredientes.add(ingred);
                        }                  
                    }                    

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }        
        
        
        
        
    }   
    
    
    public Integer actualizarEstado(int idPedido, String username) {
        int idestado;
        ConexionMLab con = new ConexionMLab();  
        MongoClient mongo = con.getConexion();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fecha = new Date();
        int fields = 0;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            query.put("_id", idPedido);
            DBCursor cursor = coleccion.find(query);           
            
            DBObject dbo = cursor.next();
            BasicDBList dbo1 = (BasicDBList)dbo.get("estados");            
            idestado=(Integer)((DBObject)(dbo1.get(dbo1.size()-1))).get("id");
            
        gEstado = new Estado();
        switch(idestado+1){           
            case 1:
                gEstado.setId(1);
                gEstado.setFechaHora(df.format(fecha));
                gEstado.setUsername(username);
                break;
            case 2:
                gEstado.setId(2);
                gEstado.setFechaHora(df.format(fecha));
                gEstado.setUsername(username);
                break;
            case 3: 
                gEstado.setId(2);
                gEstado.setFechaHora(df.format(fecha));
                gEstado.setUsername(username);
                break;            
        }        
        
            
            DBObject dbo3 = new BasicDBObject();
            dbo3.put("id",gEstado.getId());
            dbo3.put("fechahora",gEstado.getFechaHora());
            dbo3.put("username",gEstado.getUsername());
            DBObject dbo4 = new BasicDBObject();
            dbo4.put("estados", dbo3);
            DBObject dbo5 = new BasicDBObject();
            dbo5.put("$push",dbo4);

            fields = coleccion.update(query,dbo5).getN();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }   
        return fields;
        
    }
    
    void agregarPedidoDistribuidor(int idPedido){
        ConexionMLab con = new ConexionMLab();  
        MongoClient mongo = con.getConexion();
        int pedidos = 0;
        try {
            
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("empleado");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", "dtorres");
            query.put("username", query1);
            DBObject dbo3 = new BasicDBObject();
            dbo3.put("id",idPedido);
            DBObject dbo4 = new BasicDBObject();
            dbo4.put("pedidos", dbo3);
            DBObject dbo5 = new BasicDBObject();
            dbo5.put("$push",dbo4);

            pedidos = coleccion.update(query,dbo5).getN();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           mongo.close();
        }
    }
    
    
    public Integer login(String usuario, String password) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int variable = 0;
        try {
            DB db = mongo.getDB("pizzaplaneta");
            DBCollection coleccion = db.getCollection("usuario");
            BasicDBObject query = new BasicDBObject();
            query.put("username", usuario);
            query.put("password", password); 
            DBCursor cursor = coleccion.find(query);
            if (cursor.hasNext()) {
                variable = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return variable;

    }
    
}
