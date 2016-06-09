
package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import bean.Ingrediente;
import bean.Producto;
import util.ConexionMLab;


public class ProductoDAO {
    public List<Producto> getProductos() {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        List<Producto> producto = new ArrayList<>();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto");
            DBCursor cursor = coleccion.find();
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                producto.add(new Producto((Integer) dbo.get("id"), (String) dbo.get("nombre"), (Double)dbo.get("precio")));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return producto;
    }
    
     public Producto buscarProducto(int id) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        Producto producto = new Producto();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("producto");
            BasicDBObject query = new BasicDBObject();
            BasicDBObject query1 = new BasicDBObject();
            query1.put("$eq", id);
            query.put("id", query1);
            DBCursor cursor = coleccion.find(query);
            while (cursor.hasNext()) {
                DBObject dbo = cursor.next();
                producto = new Producto((Integer) dbo.get("id"), (String) dbo.get("nombre"), (Double)dbo.get("precio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return producto;

    }
}
