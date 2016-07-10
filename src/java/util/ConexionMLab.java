package util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private static final String USERNAME = "pizza";
    private static final String PASSWORD = "planeta";
    private static final String HOST = "ds023714.mlab.com";
    private static final String PORT = "23714";
    private static final String DATABASE = "pizzaplaneta";
    private MongoClient cliente;
    private MongoClientURI uri;

    public ConexionMLab() {
        uri = new MongoClientURI("mongodb://" + USERNAME + ":" + PASSWORD +"@"+ HOST + ":"+ PORT+"/" + DATABASE);
        try {
            cliente = new MongoClient(uri);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    public MongoClient getConexion() {
        return cliente;
    }
}
