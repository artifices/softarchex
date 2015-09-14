/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.persistencia.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Nano
 */
@Entity
@Table(name = "Compra")
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdCompra", nullable = false, unique = true)
    private Long idCompra;

    @Column(name = "FechaHora", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fechaHora;

    @Column(name = "Comprador", nullable = false)
    private Long idComprador;

    @OneToOne
    private OpcionEnvio opcionEnvio;

    @OneToOne
    private Articulo articulo;

    @OneToOne
    private EstadoEnvio estadoEnvio;

    @OneToMany
    private List<Actualizacion> actualizaciones;

    public Long getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Long idComprador) {
        this.idComprador = idComprador;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public List<Actualizacion> getActualizaciones() {
        return actualizaciones;
    }

    public void setActualizaciones(List<Actualizacion> actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public Compra(Long idComprador, OpcionEnvio opcionEnvio, Articulo articulo, EstadoEnvio estadoEnvio, Actualizacion actualizacion) {
        this.fechaHora = Calendar.getInstance();
        this.idComprador = idComprador;
        this.opcionEnvio = opcionEnvio;
        this.articulo = articulo;
        this.estadoEnvio = estadoEnvio;
        this.actualizaciones = new ArrayList<>();
        this.actualizaciones.add(actualizacion);
    }

    public Compra() {
        actualizaciones = new ArrayList<>();
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getComprador() {
        return idComprador;
    }

    public OpcionEnvio getOpcionEnvio() {
        return opcionEnvio;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setComprador(Long comprador) {
        this.idComprador = comprador;
    }

    public void setOpcionEnvio(OpcionEnvio opcionEnvio) {
        this.opcionEnvio = opcionEnvio;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String SiguienteEstadoEnvio(Carrier c) {
        List<UsuarioEstado> temp = estadoEnvio.getUsuariosEstadosPosibles();
        String ret = "";

        if (!temp.isEmpty()) {

            for (int i = 0; i < temp.size(); i++) {
                if (!temp.get(i).getNombreEstado().equals("Cancelado")) {
                    if (temp.get(i).getUsuario().getIdUsuario() == c.getIdUsuario()) {
                        ret = temp.get(i).getNombreEstado();
                    }
                }
            }
        } else {
            ret = "No hay estados siguientes";
        }
        return ret;
    }

    @Override
    public String toString() {
        Calendar hoy = Calendar.getInstance();
        /*hoy.add(Calendar.DATE, hoy.get(Calendar.DATE)-fechaHora.get(Calendar.DATE));
        hoy.add(Calendar.MONTH, hoy.get(Calendar.MONTH)-fechaHora.get(Calendar.MONTH));
        hoy.add(Calendar.YEAR, hoy.get(Calendar.YEAR)-fechaHora.get(Calendar.YEAR));*/
        Long time = hoy.getTimeInMillis();
        Long fechaCompra = fechaHora.getTimeInMillis();
        int dias = (int)(time-fechaCompra)/(60*60*60*24);
        int fechaMax = opcionEnvio.getTiempoMaximo() - dias;
        int fechaMin = opcionEnvio.getTiempoMinimo()- dias;      
        
        //Calendar fechaEntrega = new ;
        //fechaEntre= estadoEnvio..add(Calendar.DATE, -dias);

        return "Compra{" + "numero de trackeo=" + idCompra + ", fecha estimada de entrega maxima: " + fechaMax + ", fecha estimada de entrega minima: " + fechaMin +  ", idComprador=" + idComprador + ", opcionEnvio=" + opcionEnvio + ", articulo=" + articulo + " precio: " + articulo.getPrecio() + ", estadoEnvio=" + estadoEnvio + '}';
    }

}
