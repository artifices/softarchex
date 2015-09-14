/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Nano
 */
@Entity
@Table(name = "Moneda")
public class Moneda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdMoneda", nullable = false, unique = true)
    private Long idMoneda;
    @Column(name="Nombre", nullable = false)
    private String nombre;
    @Column(name="Factor", nullable = false)
    private double factor;
    @Column(name="Fecha", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;

    public Moneda() {
    }

    public Moneda(Long idMoneda, String nombre, double factor, Date fechaActualizacion) {
        this.idMoneda = idMoneda;
        this.nombre = nombre;
        this.factor = factor;
        this.fechaActualizacion = new Date();
    }

    public Moneda(String nombre, double factor) {
        this.nombre = nombre;
        this.factor = factor;
        this.fechaActualizacion = new Date();
    }

    public Long getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Long idMoneda) {
        this.idMoneda = idMoneda;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
