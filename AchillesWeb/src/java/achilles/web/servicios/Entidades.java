/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.web.servicios;

import achilles.persistencia.entidades.Actualizacion;
import achilles.persistencia.entidades.Articulo;
import achilles.persistencia.entidades.Carrier;
import achilles.persistencia.entidades.Categoria;
import achilles.persistencia.entidades.Compra;
import achilles.persistencia.entidades.Comprador;
import achilles.persistencia.entidades.EstadoEnvio;
import achilles.persistencia.entidades.Moneda;
import achilles.persistencia.entidades.OpcionEnvio;
import achilles.persistencia.entidades.Usuario;
import achilles.persistencia.entidades.UsuarioEstado;
import achilles.persistencia.manejadores.ManejadorDatosLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author santi
 */
@Stateless
@Path("/entidades")
public class Entidades {
    
    @EJB
    private ManejadorDatosLocal manLocal;
    
    @POST
    @Path("/altaarticulo")
    @Consumes("application/json")
    @Produces("application/json")
    public Articulo altaArticulo(Articulo a) {
        manLocal.agregarArticulo(a);
        return a;
    }
    
    @POST
    @Path("/altacomprador")
    @Consumes("application/json")
    @Produces("application/json")
    public Comprador altaComprador(Comprador c) {        
        manLocal.agregarUsuario(c);
        return c;
    }      
    
    @POST
    @Path("/altacarrier")
    @Consumes("application/json")
    @Produces("application/json")
    public Carrier altaCarrier(Carrier c) {        
        manLocal.agregarUsuario(c);
        return c;
    }     
    
    @POST
    @Path("/altaestadoenvio")
    @Consumes("application/json")
    @Produces("application/json")
    public EstadoEnvio alta(EstadoEnvio e) {
        manLocal.agregarEstadoEnvio(e);
        return e;
    }
    
    @POST
    @Path("/altacategoria")
    @Consumes("application/json")
    @Produces("application/json")
    public Categoria alta(Categoria c) {
        manLocal.agregarCategoria(c);
        return c;
    }
    
    @POST
    @Path("/asociararticulocategoria")
    @Consumes("application/json")
    @Produces("text/plain")
    public void AsociarArticuloCategoria( @QueryParam("idArticulo") Long idArticulo,
                                    @QueryParam("idCategoria") Long idCategoria) {       
        try {
            manLocal.asociarArticuloCategoria(idArticulo, idCategoria);
        }
        catch(Exception e) {
            
        }
    }
    
    @POST
    @Path("/altaopcionenvio")
    @Consumes("application/json")
    @Produces("application/json")
    public OpcionEnvio alta(OpcionEnvio o) {
        manLocal.agregarOpcionEnvio(o);
        return o;
    }
    
    @POST
    @Path("/asociaropcionenvio")
    @Consumes("application/json")
    @Produces("text/plain")
    public void AsociarOpcionEnvio( @QueryParam("idArticulo") Long idArticulo,
                                    @QueryParam("idOpcionEnvio") Long idOpcionEnvio) {       
        try {
            manLocal.asociarOpcionEnvioArticulo(idArticulo, idOpcionEnvio);
        }
        catch(Exception e) {
            
        }
    }
    
    @POST
    @Path("/altausuario")
    @Consumes("application/json")
    @Produces("application/json")
    public Usuario alta(Usuario u) {
        manLocal.agregarUsuario(u);
        return u;
    }
    
    @POST
    @Path("/altausuarioestado")
    @Consumes("application/json")
    @Produces("text/plain")
    public void AgregarUsuarioEstado(   @QueryParam("idUsuario") Long idUsuario,
                                        @QueryParam("nombreEstado") String nombreEstado,
                                        @QueryParam("nombreEstadoSiguiente") String nombreEstadoSiguiente) {       
        try {
            manLocal.asociarUsuarioEstado(idUsuario, nombreEstado, nombreEstadoSiguiente);
        }
        catch(Exception e) {
            
        }
    }
    
    @GET
    @Path("/recuperararticulos")
    @Produces("application/json")
    public List<Articulo> recuperarArticulos() {       
        List<Articulo> ret = null;
        try {
            ret = manLocal.recuperarArticulos();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/recuperaropcionesenvio")
    @Produces("application/json")
    public List<OpcionEnvio> recuperarOpcionesEnvio() {       
        List<OpcionEnvio> ret = null;
        try {
            ret = manLocal.recuperarOpcionesEnvio();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/recuperarestadoenvio")
    @Produces("application/json")
    public List<EstadoEnvio> recuperarEstadoEnvios() {       
        List<EstadoEnvio> ret = null;
        try {
            ret = manLocal.recuperarEstadosEnvio();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    
    @GET
    @Path("/recuperarcompras")
    @Produces("application/json")
    public List<Compra> recuperarCompras() {       
        List<Compra> ret = null;
        try {
            ret = manLocal.recuperarCompras();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/recuperarusuarios")
    @Produces("application/json")
    public List<Usuario> recuperarUsuarios() {       
        List<Usuario> ret = null;
        try {
            ret = manLocal.recuperarUsuarios();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/recuperarusuariosestados")
    @Produces("application/json")
    public List<UsuarioEstado> recuperarUsuariosEstados() {       
        List<UsuarioEstado> ret = null;
        try {
            ret = manLocal.recuperarUsuarioEstados();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/recuperarmonedas")
    @Produces("application/json")
    public List<Moneda> recuperarMonedas() {       
        List<Moneda> ret = null;
        try {
            ret = manLocal.recuperarMonedas();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    
    @GET
    @Path("/recuperaractualizaciones")
    @Produces("application/json")
    public List<Actualizacion> recuperarActualizaciones() {       
        List<Actualizacion> ret = null;
        try {
            ret = manLocal.recuperarActualizaciones();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
    @GET
    @Path("/re")
    @Produces("application/json")
    public List<Categoria> recuperarCategorias() {       
        List<Categoria> ret = null;
        try {
            ret = manLocal.recuperarCategorias();
        }
        catch(Exception e) {            
        }
        return ret;
    }
    
}
