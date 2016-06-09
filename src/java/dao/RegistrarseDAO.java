package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import bean.Usuario;
import util.ConexionMLab;

public class RegistrarseDAO {

    public void registrar(String nombre, String apellidos, String dni, int telefono, String edad, Usuario usu) {
        ConexionMLab con = new ConexionMLab();
        MongoClient mongo = con.getConexion();
        try {
            DB db = mongo.getDB("basededatos");
            DBCollection coleccion = db.getCollection("cliente");

            BasicDBObject doc = new BasicDBObject();

            doc.put("id", contar() + 1);
            doc.put("nombre", nombre);
            doc.put("apellidos", apellidos);
            doc.put("dni", dni);
            doc.put("telefono", telefono);
            doc.put("edad", edad);

            BasicDBObject doc2 = new BasicDBObject();
            doc2.put("usu", usu.getUsuario());
            doc2.put("pass", usu.getPassword());
            doc2.put("correo", usu.getCorreo());
            doc2.put("puntos", usu.getPuntos());

            doc.put("Usuario", doc2);

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
            DBCollection coleccion = db.getCollection("cliente");
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
