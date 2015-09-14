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
import achilles.persistencia.entidades.Comprador;
import achilles.persistencia.entidades.EstadoEnvio;
import achilles.persistencia.entidades.Moneda;
import achilles.persistencia.entidades.OpcionEnvio;
import achilles.persistencia.entidades.Usuario;
import achilles.persistencia.entidades.UsuarioEstado;
import achilles.persistencia.logger.Log;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author santi
 */
@Stateless
public class ManejadorDatos implements ManejadorDatosLocal {

    @EJB
    private Log log;

    @PersistenceContext
    private EntityManager em;

    public ManejadorDatos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AchillesPersistenciaPU");
        this.em = emf.createEntityManager();
    }

    @Override
    public void agregarUsuario(Long id, String nombre, String tipo) {
        Usuario u = new Usuario(id, nombre, tipo);
        em.persist(u);
        em.flush();
    }

    @Override
    public Usuario recuperarUsuario(Long idUsuario) {
        Usuario usuario = (Usuario) em.find(Usuario.class, idUsuario);
        return usuario;
    }

    @Override
    public void agregarUsuario(Usuario u) {
        em.persist(u);
        em.flush();
        log.loguear("Exito", "Se agregó el usuario " + u.getNombre());

    }

    @Override
    public void agregarArticulo(Articulo a) {
        em.persist(a);
        em.flush();
        log.loguear("Exito", "Se agregó el articulo " + a.getNombre());
    }

    @Override
    public void agregarOpcionEnvio(OpcionEnvio oe) {
        em.persist(oe);
        em.flush();
        log.loguear("Exito", "Se agregó la opción de envío " + oe.getNombre());
    }

    @Override
    public Articulo recuperarArticulo(Long idArticulo) {
        Articulo articulo = (Articulo) em.find(Articulo.class, idArticulo);
        return articulo;
    }

    @Override
    public OpcionEnvio recuperarOpcionEnvio(Long idOpcionEnvio) {
        OpcionEnvio opcionEnvio = (OpcionEnvio) em.find(OpcionEnvio.class, idOpcionEnvio);
        return opcionEnvio;
    }

    @Override
    public void actualizarArticulo(Articulo a) {
        em.merge(a);
        em.flush();
    }

    @Override
    public EstadoEnvio recuperarEstadoEnvio(String nombreEstadoEnvio) {
        EstadoEnvio estadoEnvio = (EstadoEnvio) em.find(EstadoEnvio.class, nombreEstadoEnvio);
        return estadoEnvio;
    }

    @Override
    public void agregarCompra(Compra c) {
        em.persist(c);
        em.flush();
        log.loguear("Exito", "Se agregó la compra con id: " + c.getIdCompra());
    }

    @Override
    public void agregarCategoria(Categoria c) {
        em.persist(c);
        em.flush();
        log.loguear("Exito", "Se agregó la categoria con id: " + c.getIdCategoria());
    }

    @Override
    public void asociarCompraAComprador(Long idComprador, Compra c) {
        Comprador comprador = (Comprador) em.find(Comprador.class, idComprador);
        comprador.agregarCompraALista(c);
        em.merge(comprador);
        em.flush();
    }

    @Override
    public void agregarEstadoEnvio(EstadoEnvio e) {
        em.persist(e);
        em.flush();
        log.loguear("Exito", "Se agregó el estado de envío: " + e.getNombre());
    }

    @Override
    public void asociarOpcionEnvioArticulo(Long idArticulo, Long idOpcionEnvio) {
        Articulo articulo = recuperarArticulo(idArticulo);
        OpcionEnvio oe = recuperarOpcionEnvio(idOpcionEnvio);
        articulo.agregarOpcionEnvio(oe);
    }

    @Override
    public Compra recuperarCompra(Long idCompra) {
        Compra compra = (Compra) em.find(Compra.class, idCompra);
        return compra;
    }

    @Override
    public void agregarActualizacion(Actualizacion a) {
        em.persist(a);
        em.flush();
        //log.loguear("Exito", "Se agregó la actualización de envío: " + a.getEstadoEnvio().getNombre());
    }

    @Override
    public void agregarUsuarioEstado(UsuarioEstado ue) {
        em.persist(ue);
        em.flush();
        log.loguear("Exito", "Se agregó el usuarioestado" + ue.toString());
    }

    @Override
    public void asociarUsuarioEstado(Long idUsuario, String nombreEstado, String nombreEstadoSiguiente) {
        EstadoEnvio ee = recuperarEstadoEnvio(nombreEstado);
        Usuario usuario = recuperarUsuario(idUsuario);
        UsuarioEstado ue = new UsuarioEstado(usuario, nombreEstadoSiguiente);
        ee.agregarUsuarioEstadoSiguiente(ue);
        em.persist(ue);
        em.flush();
    }

    @Override
    public List<Usuario> recuperarUsuarios() {
        List<Usuario> ret = em.createQuery(
                "SELECT u "
                + "FROM Usuario u").getResultList();
        return ret;
    }
    
    @Override
    public Moneda recuperarMoneda() {
        List<Moneda> ret = em.createQuery(
                "SELECT m "
                + "FROM Moneda m").getResultList();
        
        return ret.get(ret.size()-1);
    }
    
    @Override
    public List<OpcionEnvio> recuperarOpcionesEnvio() {
        List<OpcionEnvio> ret = em.createQuery(
                "SELECT o "
                + "FROM OpcionEnvio o").getResultList();
        return ret;
    }

    @Override
    public List<EstadoEnvio> recuperarEstadosEnvio() {
        List<EstadoEnvio> ret = em.createQuery(
                "SELECT e "
                + "FROM EstadoEnvio e").getResultList();
        return ret;
    }

    @Override
    public List<Compra> recuperarCompras() {
        List<Compra> ret = em.createQuery(
                "SELECT c "
                + "FROM Compra c").getResultList();
        return ret;
    }

    @Override
    public List<UsuarioEstado> recuperarUsuarioEstados() {
        List<UsuarioEstado> ret = em.createQuery(
                "SELECT u "
                + "FROM UsuarioEstado u").getResultList();
        return ret;
    }

    @Override
    public List<Moneda> recuperarMonedas() {
        List<Moneda> ret = em.createQuery(
                "SELECT m "
                + "FROM Moneda m").getResultList();
        return ret;
    }

    @Override
    public List<Actualizacion> recuperarActualizaciones() {
        List<Actualizacion> ret = em.createQuery(
                "SELECT a "
                + "FROM Actualizacion a").getResultList();
        return ret;
    }

    @Override
    public List<Categoria> recuperarCategorias() {
        List<Categoria> ret = em.createQuery(
                "SELECT c "
                + "FROM Categoria c").getResultList();
        return ret;
    }

    @Override
    public List<Articulo> recuperarArticulos() {
        List<Articulo> ret = em.createQuery(
                "SELECT a "
                + "FROM Articulo a").getResultList();
        return ret;
    }

    @Override
    public void asociarArticuloCategoria(Long idArticulo, Long idCategoria) {
        Articulo articulo = recuperarArticulo(idArticulo);
        Categoria categoria = recuperarCategoria(idCategoria);
        articulo.setCategoria(categoria);
        em.merge(articulo);
        em.flush();
    }

    @Override
    public Categoria recuperarCategoria(Long idCategoria) {
        Categoria categoria = (Categoria) em.find(Categoria.class, idCategoria);
        return categoria;
    }

    @Override
    public void actualizarCompra(Compra compra) {
        em.merge(compra);
        em.flush();
    }

    @Override
    public void agregarMoneda(Moneda moneda) {
        em.persist(moneda);
        em.flush();
        
        log.loguear("Exito", "Se agregó ratio de moneda nuevo");
    }

}
