/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import bean.Estado;
import bean.Pedido;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.LoginDAO;
import dao.PedidoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Jose Luis
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    
    
    @GET
    @Path("login")
    
    public String login(@QueryParam("usuario") String usuario,@QueryParam("password") String password) {
        LoginDAO dao=new LoginDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.login(usuario, password));
    }
    
    @GET
    @Path("loginTrabajador")
    
    public String loginTrabajador(@QueryParam("usuario") String usuario,@QueryParam("password") String password) {
        LoginDAO dao=new LoginDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.loginTrabajador(usuario, password));
    }
    
    @GET
    @Produces("application/json")
    @Path("getUsuario")
    public String getUsuario(@QueryParam("usuario") String usu){
        LoginDAO dao=new LoginDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.buscarUsuario(usu));
 
    }
    
    @GET
    @Produces("application/json")
    @Path("getPedidos")
    public String getPedidos(){
        PedidoDAO dao=new PedidoDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.getPedidos());
        //List<Pedido> l= gson.fromJson("asdasdasd", new TypeToken<ArrayList<Pedido>>(){}.getType()); PROBAR LUEGO
    }
    @GET
    @Produces("application/json")
    @Path("buscarPedidosPorID")
    public String buscarPedidosPorID(@QueryParam("id") int id){
        PedidoDAO dao=new PedidoDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.buscarPedidoPorID(id));
        //List<Pedido> l= gson.fromJson(asd, new TypeToken<ArrayList<Pedido>>(){}.getType()); PROBAR LUEGO
    }
    @GET
    @Produces("application/json")
    @Path("buscarPedidosPorUsuario")
    public String buscarPedidosPorUsuario(@QueryParam("usuario") String usuario){
        PedidoDAO dao=new PedidoDAO();
        Gson gson = new Gson();
        return gson.toJson(dao.buscarPedidoPorUsuario(usuario));
        //List<Pedido> l= gson.fromJson(asd, new TypeToken<ArrayList<Pedido>>(){}.getType()); PROBAR LUEGO
    }
    
    @PUT
    @Path("actualizarEstado")
    public void actualizarEstado(@QueryParam("estado") Estado estado,@QueryParam("id") int id){
        PedidoDAO dao=new PedidoDAO();
        dao.actualizarEstado(estado, id);
        //List<Pedido> l= gson.fromJson(asd, new TypeToken<ArrayList<Pedido>>(){}.getType()); PROBAR LUEGO
    }
    
    
}
