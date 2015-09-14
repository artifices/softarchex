/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.persistencia.manejadores;

import achilles.persistencia.entidades.Actualizacion;
import achilles.persistencia.entidades.Articulo;
import achilles.persistencia.entidades.Categoria;
import achilles.persistencia.entidades.Compra;
import achilles.persistencia.entidades.EstadoEnvio;
import achilles.persistencia.entidades.Moneda;
import achilles.persistencia.entidades.OpcionEnvio;
import achilles.persistencia.entidades.Usuario;
import achilles.persistencia.entidades.UsuarioEstado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santi
 */
@Local
public interface ManejadorDatosLocal {

    void agregarUsuario(Long id, String nombre, String tipo);

    void agregarUsuario(Usuario u);

    Usuario recuperarUsuario(Long idUsuario);

    void agregarOpcionEnvio(OpcionEnvio oe);

    void agregarArticulo(Articulo a);

    Articulo recuperarArticulo(Long idArticulo);

    List<Articulo> recuperarArticulos();

    void asociarOpcionEnvioArticulo(Long idArticulo, Long idOpcionEnvio);

    void asociarUsuarioEstado(Long idUsuario, String nombreEstado, String nombreEstadoSiguiente);

    OpcionEnvio recuperarOpcionEnvio(Long idOpcionEnvio);

    void actualizarArticulo(Articulo a);

    EstadoEnvio recuperarEstadoEnvio(String nombreEstadoEnvio);

    void agregarCompra(Compra c);
    
    void agregarCategoria(Categoria c);

    void agregarEstadoEnvio(EstadoEnvio e);

    void asociarCompraAComprador(Long idComprador, Compra c);

    Compra recuperarCompra(Long idCompra);

    void agregarActualizacion(Actualizacion actualizacion);

    void agregarUsuarioEstado(UsuarioEstado ue);

    List<Usuario> recuperarUsuarios();

    List<OpcionEnvio> recuperarOpcionesEnvio();

    List<EstadoEnvio> recuperarEstadosEnvio();

    List<Compra> recuperarCompras();

    List<UsuarioEstado> recuperarUsuarioEstados();

    List<Moneda> recuperarMonedas();

    List<Actualizacion> recuperarActualizaciones();

    List<Categoria> recuperarCategorias();
    
    Categoria recuperarCategoria(Long idCategoria);

    void asociarArticuloCategoria(Long idArticulo, Long idCategoria);

    void actualizarCompra(Compra compra);

    void agregarMoneda(Moneda moneda);
    
    Moneda recuperarMoneda();

}
