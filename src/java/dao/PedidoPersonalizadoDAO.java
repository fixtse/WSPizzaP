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
import bean.Usuario;
import util.ConexionMLab;

public class PedidoPersonalizadoDAO {
//Lista de ingredientes por usuario. (ingredientes, Usuario usuario)

    public void ingresarPedidoxUsuario(List<Ingrediente> ingredientes, String usuario) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("ingredientesxusuario");

            BasicDBObject doc = new BasicDBObject();

            doc.put("id", contar() + 1);
            doc.put("usu", usuario);
            BasicDBObject doc2;
            ArrayList ingredientesA = new ArrayList();

            for (Ingrediente ingrediente : ingredientes) {
                doc2 = new BasicDBObject();
                doc2.put("id", ingrediente.getId());
                doc2.put("nombre", ingrediente.getNombre());
                ingredientesA.add(doc2);
            }
            doc.put("ingredientes", ingredientesA);

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
            DBCollection coleccion = db.getCollection("ingredientesxusuario");
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

}
