/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * @author Nano
 */
@Entity
@Table(name="Actualizacion")
public class Actualizacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdActualizacion", nullable = false, unique = true)
    private Long idActualizacion;
    
    @Column(name="IdUsuario", nullable = false)
    private Long idUsuario;
    
    @Column(name="FechaActualizacion", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fechaActualizacion;
    
    @Column(name="LugarGeograficoActual", nullable = false)
    private String lugarGeograficoActual;//coordenadas?
    
    @Column(name="notaActualizacion", nullable = false)
    private String notaActualizacion;
    
    @OneToOne
    private EstadoEnvio estadoEnvio;

    public Actualizacion() {
    }

    public Actualizacion(Long idActualizacion, Long idUsuario, Calendar fechaActualizacion, String lugarGeograficoActual, String notaActualizacion, EstadoEnvio estadoEnvio) {
        this.idActualizacion = idActualizacion;
        this.idUsuario = idUsuario;
        this.fechaActualizacion = fechaActualizacion;
        this.lugarGeograficoActual = lugarGeograficoActual;
        this.notaActualizacion = notaActualizacion;
        this.estadoEnvio = estadoEnvio;
    }

    public Actualizacion(Long idUsuario, Calendar fechaActualizacion, String lugarGeograficoActual, String notaActualizacion, EstadoEnvio estadoEnvio) {
        this.idUsuario = idUsuario;
        this.fechaActualizacion = fechaActualizacion;
        this.lugarGeograficoActual = lugarGeograficoActual;
        this.notaActualizacion = notaActualizacion;
        this.estadoEnvio = estadoEnvio;
    }

    public Calendar getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Calendar fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getLugarGeograficoActual() {
        return lugarGeograficoActual;
    }

    public void setLugarGeograficoActual(String lugarGeograficoActual) {
        this.lugarGeograficoActual = lugarGeograficoActual;
    }

    public String getNotaActualizacion() {
        return notaActualizacion;
    }

    public void setNotaActualizacion(String notaActualizacion) {
        this.notaActualizacion = notaActualizacion;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Long getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(Long idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Actualizacion{" + "idUsuario=" + idUsuario + ", fechaActualizacion=" + fechaActualizacion.getTime() + ", lugarGeograficoActual=" + lugarGeograficoActual + ", notaActualizacion=" + notaActualizacion + ", estadoEnvio=" + estadoEnvio + '}';
    }
    
}
