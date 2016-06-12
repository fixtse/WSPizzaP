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
import com.mongodb.QueryOperators;
import javax.management.Query;
import util.ConexionMLab;

public class PedidoDAO {

    public void agregarPedido(Pedido pedido) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");

            BasicDBObject docPedido = new BasicDBObject();

            docPedido.put("id", contar());

            BasicDBObject docEstado = new BasicDBObject();
            docEstado.put("fechahora", pedido.getEstado().getFechahora());
            docEstado.put("id", pedido.getEstado().getId());
            docEstado.put("estado", pedido.getEstado().getEstado());

            docPedido.put("Estado", docEstado);
            docPedido.put("usu", pedido.getUsuario().getUsuario());
            docPedido.put("direccion", pedido.getDireccion());

            BasicDBObject docPizza;
            BasicDBObject docIngrediente;
            ArrayList arrayPizzas = new ArrayList();
            ArrayList arrayIngredientes = new ArrayList();
            List<Ingrediente> ingredientes;
            List<Pizza> pizzas = pedido.getPizzas();
            for (Pizza pizza : pizzas) {
                docPizza = new BasicDBObject();
                ingredientes = pizza.getIng();
                //ERROR EN ESTA LINEA PARA INGRESAR UN PEDIDO PREDETERMINADO. FALTA CASTEAR
                for (Ingrediente ingrediente : ingredientes) {
                    docIngrediente = new BasicDBObject();
                    docIngrediente.put("id", ingrediente.getId());
                    docIngrediente.put("nombre", ingrediente.getNombre());
                    arrayIngredientes.add(docIngrediente);
                }
                docPizza.put("Ingredientes", arrayIngredientes);
                docPizza.put("Tamano", pizza.getTamano());
                docPizza.put("precio", pizza.getPrecio());
                arrayPizzas.add(docPizza);
            }
            docPedido.put("Pizzas", arrayPizzas);

            BasicDBObject docProducto;
            ArrayList arrayProductos = new ArrayList();
            List<Producto> productos = pedido.getProductos();
            for (Producto producto : productos) {
                docProducto = new BasicDBObject();
                docProducto.put("id", producto.getId());
                docProducto.put("nombre", producto.getNombre());
                docProducto.put("precio", producto.getPrecio());
                arrayProductos.add(docProducto);
            }
            docPedido.put("Productos", arrayProductos);

            coleccion.insert(docPedido);
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
        Ingrediente ingred = null;
        Pizza pizzita = null;
        Producto produ = null;
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
                DBObject dbo2 = (BasicDBObject) dbo.get("Estado");
                estado = new Estado();
                estado.setFechahora((String) dbo2.get("fechahora"));
                estado.setId((Integer) dbo2.get("id"));
                estado.setEstado((String) dbo2.get("estado"));

                pedido = new Pedido();
                pedido.setId((Integer) dbo.get("id"));
                pedido.setEstado(estado);
                usuario = new Usuario();
                login = new LoginDAO();
                usuario = login.buscarUsuario((String) dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String) dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList) dbo.get("Pizzas");
                pizzas = new ArrayList<>();
                for (Object piz : dbo3) {
                    pizzita = new Pizza();
                    DBObject dbb = DBObject.class.cast(piz);
                    BasicDBList dbo4 = (BasicDBList) dbb.get("Ingredientes");
                    ingredientes = new ArrayList<>();
                    for (Object ing : dbo4) {
                        ingred = new Ingrediente();
                        DBObject dbo5 = DBObject.class.cast(ing);
                        ingred.setId((Integer) dbo5.get("id"));
                        ingred.setNombre((String) dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList) dbo.get("Productos");
                productos = new ArrayList<>();
                for (Object pro : dbo6) {
                    produ = new Producto();
                    DBObject dbo7 = DBObject.class.cast(pro);
                    produ.setId((Integer) dbo7.get("id"));
                    produ.setNombre((String) dbo7.get("nombre"));
                    produ.setPrecio((Double) dbo7.get("precio"));
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
        Ingrediente ingred = null;
        Pizza pizzita = null;
        Producto produ = null;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", usu);
            query.put("usu", query1);
            DBCursor cursor = coleccion.find(query);
            pedidos = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                DBObject dbo2 = (BasicDBObject) dbo.get("Estado");
                estado = new Estado();
                estado.setFechahora((String) dbo2.get("fechahora"));
                estado.setId((Integer) dbo2.get("id"));
                estado.setEstado((String) dbo2.get("estado"));

                pedido = new Pedido();
                pedido.setId((Integer) dbo.get("id"));
                pedido.setEstado(estado);
                usuario = new Usuario();
                login = new LoginDAO();
                usuario = login.buscarUsuario((String) dbo.get("usu"));
                pedido.setUsuario(usuario);
                pedido.setDireccion((String) dbo.get("direccion"));
                BasicDBList dbo3 = (BasicDBList) dbo.get("Pizzas");
                pizzas = new ArrayList<>();
                for (Object piz : dbo3) {
                    pizzita = new Pizza();
                    DBObject dbb = DBObject.class.cast(piz);
                    BasicDBList dbo4 = (BasicDBList) dbb.get("Ingredientes");
                    ingredientes = new ArrayList<>();
                    for (Object ing : dbo4) {
                        ingred = new Ingrediente();
                        DBObject dbo5 = DBObject.class.cast(ing);
                        ingred.setId((Integer) dbo5.get("id"));
                        ingred.setNombre((String) dbo5.get("nombre"));
                        ingredientes.add(ingred);
                    }
                    pizzita.setIng(ingredientes);
                    pizzas.add(pizzita);
                }
                BasicDBList dbo6 = (BasicDBList) dbo.get("Productos");
                productos = new ArrayList<>();
                for (Object pro : dbo6) {
                    produ = new Producto();
                    DBObject dbo7 = DBObject.class.cast(pro);
                    produ.setId((Integer) dbo7.get("id"));
                    produ.setNombre((String) dbo7.get("nombre"));
                    produ.setPrecio((Double) dbo7.get("precio"));
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

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            DBCursor cursor = coleccion.find();
            pedidos = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                pedido = new Pedido();
                pedido = this.buscarPedidoPorID((Integer) dbo.get("id"));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return pedidos;
    }

    public int actualizarEstado(Estado estado, int idPedido) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        int fields = 0;
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("pedido");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", idPedido);
            query.put("id", query1);
            DBObject dbo3 = new BasicDBObject();
            dbo3.put("fechahora",estado.getFechahora());
            dbo3.put("id",estado.getId());
            dbo3.put("estado",estado.getEstado());
            DBObject dbo4 = new BasicDBObject();
            dbo4.put("Estado", dbo3);
            DBObject dbo5 = new BasicDBObject();
            dbo5.put("$set",dbo4);

            fields = coleccion.update(query,dbo5).getN();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }   
        return fields;
        
    }
    
}
