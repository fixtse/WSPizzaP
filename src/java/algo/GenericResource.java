/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import bean.Estado;
import bean.Info;
import bean.Mensaje;
import bean.Pedido;
import bean.Pizza;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.CocinaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jboss.weld.context.bound.Bound;

/**
 * REST Web Service
 *
 * @author Jose Luis
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    
    
    @GET
    @Path("login")    
    public String login(@QueryParam("usuario") String usuario,@QueryParam("password") String password) {
        CocinaDAO dao=new CocinaDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.login(usuario, password));
    }
    
    @GET
    @Produces("application/json")
    @Path("getMensaje")
    public String getMensajes(){
        CocinaDAO dao=new CocinaDAO();        
        return gson.toJson(dao.getMensajes());
    }
    
    @GET
    @Produces("application/json")
    @Path("getPizza")
    public String getPizzas(@QueryParam("id")int id){
        CocinaDAO dao=new CocinaDAO();
        return gson.toJson(dao.getPizza(id));
    }
    
    @GET
    @Produces("application/json")
    @Path("Estado")
    public Integer actualizarEstado(@QueryParam("pedido") int idPedido,@QueryParam("usuario") String usuario,@QueryParam("distrito") String distrito){
        CocinaDAO dao=new CocinaDAO();        
        return dao.actualizarEstado(idPedido,usuario,distrito);
    }
    
    
    
    
}
