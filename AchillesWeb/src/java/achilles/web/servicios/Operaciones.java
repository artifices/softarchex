/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.web.servicios;

import achilles.logica.operaciones.GestionCompra;
import achilles.mdb.sb.ProductorSB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
@Path("/operaciones")
public class Operaciones {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private GestionCompra gestorCompra;
    @EJB
    private ProductorSB ejbRef;
    
    @GET
    @Path("/comprararticulo")
    @Consumes("application/json")
    @Produces("text/plain")
    public String comprarArticulo(  @QueryParam("idComprador") Long idComprador,
                                    @QueryParam("idArticulo") Long idArticulo,
                                    @QueryParam("idOpcionEnvio") Long idOpcionEnvio) {
        String response = "";
        try {
            response = gestorCompra.validarCompra(idComprador, idArticulo, idOpcionEnvio);
        }
        catch(Exception e) {
        }
        return response;
    }
    
    
    @GET
    @Path("/consultarcompras")
    @Consumes("application/json")
    @Produces("text/plain")
    public String consultarCompras(  @QueryParam("idComprador") Long idComprador) {
        String response = null;
        try {
            response = gestorCompra.consultarCompra(idComprador);
        }
        catch(Exception e) {
        }
        return response;
    } 
    
    
    @GET
    @Path("/trackcompra")
    @Consumes("application/json")
    @Produces("text/plain")
    public String trackCompra(  @QueryParam("idComprador") Long idComprador,
                                @QueryParam("numeroTrackeo") Long idCompra) {
        String response = null;
        try {
            response = gestorCompra.trackCompra(idComprador, idCompra);
        }
        catch(Exception e) {
        }
        return response;
    }    
    
    
    @GET
    @Path("/cancelarcompra")
    @Consumes("application/json")
    @Produces("text/plain")
    public String cancelarCompra(  @QueryParam("idComprador") Long idComprador,
                                   @QueryParam("idCompra") Long idCompra  ) {
        String response = null;
        try {
            response = gestorCompra.cancelarCompra(idComprador,idCompra);
        }
        catch(Exception e) {
        }
        return response;
    }
    
    
    @GET
    @Path("/actualizarestado")
    @Consumes("application/json")
    @Produces("text/plain")
    public String actualizarEstado(  @QueryParam("idCarrier") Long idCarrier,
                                     @QueryParam("idCompra") Long idCompra  ) {
        String response = null;
        try {
            response = gestorCompra.siguienteEstado(idCarrier,idCompra);
            System.out.println(response);
        }
        catch(Exception e) {
        }
        return response;
    }      

    @POST
    @Path("/mensajecola")
    @Consumes("application/json")
    @Produces("application/json")
    public void enviarMensajeACola(@QueryParam("idCarrier") Long idCarrier,
                                     @QueryParam("idCompra") Long idCompra  ) {
        ejbRef.sendToQueue(idCarrier,idCompra);
    }
    
    @GET
    @Path("/actualizarMonedas")
    @Consumes("application/json")
    @Produces("application/json")
    public void actualizarMonedas() {        
        gestorCompra.actualizarCotizaciones();
    }
    
}
