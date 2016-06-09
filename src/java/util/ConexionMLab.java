package util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;

public class ConexionMLab {

    private final String username = "grupo01";
    private final String password = "progra";
    private final String database = "basededatos";
    private MongoClient cliente;
    private MongoClientURI uri;

    public ConexionMLab() {
        uri = new MongoClientURI("mongodb://" + username + ":" + password + "@ds063124.mlab.com:63124/" + database);
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
