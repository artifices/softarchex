/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.logica.operaciones;

import achilles.persistencia.entidades.Actualizacion;
import achilles.persistencia.entidades.Articulo;
import achilles.persistencia.entidades.Carrier;
import achilles.persistencia.entidades.Compra;
import achilles.persistencia.entidades.Comprador;
import achilles.persistencia.entidades.EstadoEnvio;
import achilles.persistencia.entidades.Moneda;
import achilles.persistencia.entidades.OpcionEnvio;
import achilles.persistencia.entidades.Usuario;
import achilles.persistencia.logger.Log;
import achilles.persistencia.manejadores.ManejadorDatos;
import achilles.persistencia.manejadores.ManejadorDatosLocal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author santi
 */
@Stateless
@LocalBean
public class GestionCompra {

    @EJB
    private ManejadorDatosLocal manejador;

    @EJB
    private Log log;
    
    public GestionCompra() {
        manejador = new ManejadorDatos();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String validarCompra(Long idUsuario, Long idArticulo, Long idOpcionEnvio) {
        String ret;
        double costo;
        Usuario usuario = manejador.recuperarUsuario(idUsuario);
        Articulo articulo = manejador.recuperarArticulo(idArticulo);
        OpcionEnvio opcionEnvio = manejador.recuperarOpcionEnvio(idOpcionEnvio);

        if (usuario == null) {
            ret = "El usuario no existe";
            log.loguear("Error", "El usuario " + idUsuario + " no existe");
        } else if (articulo == null) {
            ret = "El artículo no existe";
            log.loguear("Error", "El artículo " + idArticulo + " no existe");
        } else if (opcionEnvio == null) {
            ret = "La opción de envío no existe";
            log.loguear("Error", "La opción de envío " + idOpcionEnvio + " no existe");
        } else if (!usuario.getTipo().equals("Comprador")) {
            ret = "El usuario no es de tipo comprador";
            log.loguear("Error", "El usuario " + idUsuario + " no es de tipo comprador");
        } else if (!articulo.isActivo()) {
            ret = "El artículo no está activo";
            log.loguear("Error", "El artículo " + idUsuario + " no está activo");
        } else if (articulo.getStock() <= 0) {
            ret = "No hay stock disponible para el artículo requerido";
            log.loguear("Error", "No hay stock disponible para el artículo " + idArticulo);
        } else if (!articulo.getLstOpcionesEnvio().contains((OpcionEnvio) opcionEnvio)) {
            ret = "La opción de envío elegida no está disponible para este artículo";
            log.loguear("Error", "La opción de envío elegida no está disponible para el artículo " + idArticulo);
        } else {
            costo = articulo.getPrecio() + opcionEnvio.getCostoFijo() + articulo.getPrecio() * opcionEnvio.getPorcentaje();
            ret = "La compra se ha realizado con éxito! \n El costo total es de: $" + costo + "\n Tiempo estimado de entrega entre " + opcionEnvio.getTiempoMinimo() + " y " + opcionEnvio.getTiempoMaximo() + " días";
            articulo.setStock(articulo.getStock() - 1);
            manejador.actualizarArticulo(articulo);
            EstadoEnvio estadoEnvio = manejador.recuperarEstadoEnvio("Nuevo");//Recupera el estado inicial de los estados
            Actualizacion actualizacion = new Actualizacion(idUsuario, Calendar.getInstance(), "Lugar Actual", "Nada", estadoEnvio);
            Compra c = new Compra(idUsuario, opcionEnvio, articulo, estadoEnvio, actualizacion);
            manejador.agregarActualizacion(actualizacion);
            System.out.println(c.getComprador().toString() + c.getArticulo().getNombre().toString());
            manejador.agregarCompra(c);
            manejador.asociarCompraAComprador(idUsuario, c);
            log.loguear("Exito", ret);
        }
        return ret;
    }

    public String cancelarCompra(Long idUsuario, Long idCompra) {
        String ret;

        try {
            Comprador comprador = (Comprador) manejador.recuperarUsuario(idUsuario);
            Compra compra = manejador.recuperarCompra(idCompra);
            if (compra == null) {
                ret = "La compra no existe";
                log.loguear("Error", "La compra " + idCompra + " no existe");
            } else if (!comprador.getCompras().contains(compra)) {
                ret = "La compra no fue hecha por el comprador especificado";
                log.loguear("Error", "La compra " + idCompra + " no fue hecha por el comprador " + idUsuario);
            } else if (!compra.getEstadoEnvio().getNombre().equals("Nuevo")) {
                ret = "La compra no puede cancelarse una vez que ya salió de la empresa";
                log.loguear("Error", "La compra " + idCompra + " no puede cancelarse una vez que ya salió de la empresa");
            } else {
                EstadoEnvio ee = manejador.recuperarEstadoEnvio("Cancelado");
                compra.setEstadoEnvio(ee);
                ret = "La compra ha sido cancelada";
                log.loguear("Error", "La compra " + idCompra + " ha sido cancelada");
            }
        } catch (Exception e) {
            ret = "El usuario no es de tipo comprador";
            log.loguear("Error", "El usuario " + idUsuario + " no es de tipo comprador");
        }
        return ret;
    }

    public String consultarCompra(Long idComprador) {
        List<Compra> ret = null;
        String retorno = "";
        Moneda moneda = manejador.recuperarMoneda();
        try {
            Comprador c = (Comprador) (manejador.recuperarUsuario(idComprador));
            ret = c.getCompras();
            Iterator it = ret.iterator();
            while (it.hasNext()) {
                retorno += it.next();
                retorno += "\n";
            }
            log.loguear("Exito", "Se consultaron las compras " + idComprador);

        } catch (Exception e) {
            retorno = "El usuario no es de tipo comprador";
            log.loguear("Error", "El usuario " + idComprador + " no es de tipo comprador");
        }

        return retorno;
    }

    
    public String trackCompra(Long idComprador, Long idCompra) {
        String retorno = "";

        try {
            Comprador c = (Comprador) (manejador.recuperarUsuario(idComprador));
            Compra compra = manejador.recuperarCompra(idCompra);      
            Moneda moneda = manejador.recuperarMoneda();
            if (compra == null) {
                retorno = "La compra no existe";
                log.loguear("Error", "La compra " + idCompra + " no existe");
            } else if (!c.getCompras().contains(compra)) {
                retorno = "La compra no fue hecha por el comprador especificado";
                log.loguear("Error", "La compra no fue hecha por el comprador " + idComprador);
            } else {
                List<Actualizacion> actualizaciones = compra.getActualizaciones();
                retorno += "La compra con id: "+ idCompra + " tiene un precio en dolares = "+moneda.getFactor()/compra.getArticulo().getPrecio() + " y precio en pesos: "+ compra.getArticulo().getPrecio()+"\n";
                Iterator it = actualizaciones.iterator();
                while (it.hasNext()) {
                    retorno += it.next();
                    retorno += "\n";
                }
                log.loguear("Exito", "Se trackeo la compra de " + idComprador + " con número de trackeo " + idCompra);
            }
        } catch (Exception e) {
            retorno = "El usuario no es de tipo comprador" + e.getMessage().toString();
            log.loguear("Error", "El usuario " + idComprador + " no es de tipo comprador");
        }
        return retorno;
    }

    
    public String siguienteEstado(Long idCarrier,Long idCompra) {
        String ret;
        Compra compra = manejador.recuperarCompra(idCompra);
        Carrier carrier;
        try {
            carrier = (Carrier) manejador.recuperarUsuario(idCarrier);
            if (compra == null) {
                ret = "No hay una compra con ese número de trackeo";
                log.loguear("Error", "No hay una compra con número de trackeo de " + idCompra);
            } else {
                String retorno = compra.SiguienteEstadoEnvio(carrier);
                EstadoEnvio estadoSiguiente = manejador.recuperarEstadoEnvio(retorno);
                if (estadoSiguiente != null) {
                    compra.setEstadoEnvio(estadoSiguiente);
                    manejador.actualizarCompra(compra);
                    ret = "Se actualizó al siguiente estado exitosamente";
                    log.loguear("Exito", "Se actualizó al siguiente estado exitosamente para la compra " + idCompra);
                } else {
                    ret = "No se pudo avanzar al siguiente estado. Motivo: " + retorno;
                    log.loguear("Error", "No se pudo avanzar al siguiente estado. Motivo: " + retorno);
                }
            }
        } catch (Exception e) {
            ret = "El usuario no es de tipo carrier";
            log.loguear("Error", "El usuario " + idCarrier + " no es de tipo carrier");
        }
        return ret;
    }

    public void actualizarCotizaciones() {
        try {
            URL url = new URL("https://api.mercadolibre.com/currency_conversions/search?from=USD&to=UYU");
            
            //Definir proxy para conexion
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxycen.ort.edu.uy", 80));
            //Pasar proxy por parametro a la conexion
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();           
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output,ratio = "1";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                ratio = output.substring(9, 14);
            }
                System.out.println(ratio);                
                Moneda moneda = new Moneda("Dolar",Double.parseDouble(ratio));
                manejador.agregarMoneda(moneda);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
