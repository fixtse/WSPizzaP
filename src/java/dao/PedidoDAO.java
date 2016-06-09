package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import bean.Estado;
import bean.Ingrediente;
import bean.Pedido;
import bean.Pizza;
import bean.Producto;
import bean.Usuario;
import com.mongodb.BasicDBList;
import util.ConexionMLab;

public class PedidoDAO {

    public void agregar(Estado estado, String usuario, String direccion, List<Pizza> pizzas, List<Producto> productos) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject doc = new BasicDBObject();

            doc.put("id", contar() + 1);

            BasicDBObject doc1 = new BasicDBObject();
            doc1.put("fechahora", estado.getFechahora());
            doc1.put("id", estado.getId());
            doc1.put("estado", estado.getEstado());

            doc.put("Estado", doc1);

            doc.put("usu", usuario);

            doc.put("direccion", direccion);

            BasicDBObject doc2;
            BasicDBObject doc4;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayIngredientes = new ArrayList();
            List<Ingrediente> ingredientes;
            for (Pizza pizza : pizzas) {
                doc2 = new BasicDBObject();
                ingredientes = pizza.getIng();
                for (Ingrediente ingrediente : ingredientes) {
                    doc4 = new BasicDBObject();
                    doc4.put("id", ingrediente.getId());
                    doc4.put("nombre", ingrediente.getNombre());
                    arrayIngredientes.add(doc4);
                }
                doc2.put("Ingredientes", arrayIngredientes);
                doc2.put("precio", pizza.getPrecio());
                arrayPizzas.add(doc2);
            }
            doc.put("Pizzas", arrayPizzas);

            BasicDBObject doc3;
            ArrayList arrayProductos = new ArrayList();
            for (Producto producto : productos) {
                doc3 = new BasicDBObject();
                doc3.put("id", producto.getId());
                doc3.put("nombre", producto.getNombre());
                doc3.put("precio", producto.getPrecio());
                arrayProductos.add(doc3);
            }
            doc.put("Productos", arrayProductos);

            coleccion.insert(doc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
    }
       
    private Integer contar() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int cont = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                cont = cont + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return cont;
    }
    
    public Pedido buscarPedidoPorID(int id) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Pedido pedido = null;
        Estado estado = null;
        Usuario usuario = null;
        LoginDAO login = null;
        List<Pizza> pizzas = null;
        List<Ingrediente> ingredientes = null;
        List<Producto> productos = null;
        Ingrediente ingred=null;
        Pizza pizzita = null;
        Producto produ=null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject)dbo.get("Estado");
                estado = new Estado();
                estado.setFechahora((String)dbo2.get("fechahora"));
                estado.setId((Integer)dbo2.get("id"));
                estado.setEstado((String)dbo2.get("estado"));
                
                pedido=new Pedido();
                pedido.setId((Integer)dbo.get("id"));
                pedido.setEstado(estado);
                usuario= new Usuario();
                login = new LoginDAO();
                usuario=login.buscarUsuario((String)dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String)dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList)dbo.get("Pizzas");
                pizzas=new ArrayList<>();
                for (Object piz : dbo3){
                    pizzita= new Pizza();
                    DBObject dbb = DBObject.class.cast(piz);
                    BasicDBList dbo4 = (BasicDBList)dbb.get("Ingredientes");
                    ingredientes= new ArrayList<>();
                    for (Object ing : dbo4){
                        ingred=new Ingrediente();
                        DBObject dbo5 = DBObject.class.cast(ing);
                        ingred.setId((Integer)dbo5.get("id"));
                        ingred.setNombre((String)dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList)dbo.get("Productos");
                productos=new ArrayList<>();
                for (Object pro : dbo6){
                    produ=new Producto();
                    DBObject dbo7 = DBObject.class.cast(pro);
                    produ.setId((Integer)dbo7.get("id"));
                    produ.setNombre((String)dbo7.get("nombre"));
                    produ.setPrecio((Double)dbo7.get("precio"));
                    productos.add(produ);
                }
                pedido.setPizzas(pizzas);
                pedido.setProductos(productos);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedido;

    }
    public List<Pedido> buscarPedidoPorUsuario(String usu) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        Estado estado = null;
        Usuario usuario = null;
        LoginDAO login = null;
        List<Pizza> pizzas = null;
        List<Ingrediente> ingredientes = null;
        List<Producto> productos = null;
        Ingrediente ingred=null;
        Pizza pizzita = null;
        Producto produ=null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", usu);
            query.put("usu", query1);
            DBCursor cursor = coleccion.find(query);
            pedidos=new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject)dbo.get("Estado");
                estado = new Estado();
                estado.setFechahora((String)dbo2.get("fechahora"));
                estado.setId((Integer)dbo2.get("id"));
                estado.setEstado((String)dbo2.get("estado"));
                
                
                pedido=new Pedido();
                pedido.setId((Integer)dbo.get("id"));
                pedido.setEstado(estado);
                usuario= new Usuario();
                login = new LoginDAO();
                usuario=login.buscarUsuario((String)dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String)dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList)dbo.get("Pizzas");
                pizzas=new ArrayList<>();
                for (Object piz : dbo3){
                    pizzita= new Pizza();
                    DBObject dbb = DBObject.class.cast(piz);
                    BasicDBList dbo4 = (BasicDBList)dbb.get("Ingredientes");
                    ingredientes= new ArrayList<>();
                    for (Object ing : dbo4){
                        ingred=new Ingrediente();
                        DBObject dbo5 = DBObject.class.cast(ing);
                        ingred.setId((Integer)dbo5.get("id"));
                        ingred.setNombre((String)dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList)dbo.get("Productos");
                productos=new ArrayList<>();
                for (Object pro : dbo6){
                    produ=new Producto();
                    DBObject dbo7 = DBObject.class.cast(pro);
                    produ.setId((Integer)dbo7.get("id"));
                    produ.setNombre((String)dbo7.get("nombre"));
                    produ.setPrecio((Double)dbo7.get("precio"));
                    productos.add(produ);
                }
                pedido.setPizzas(pizzas);
                pedido.setProductos(productos);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;

    }
    
    public List<Pedido> getPedidos(){
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            pedidos= new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                pedido= new Pedido();
                pedido=this.buscarPedidoPorID((Integer)dbo.get("id"));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;
    }
    public void actualizarEstado(){
        
    }
}
