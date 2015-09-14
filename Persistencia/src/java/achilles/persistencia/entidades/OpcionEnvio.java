/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name="OpcionEnvio")
public class OpcionEnvio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdOpcionEnvio",nullable = false, unique = true)
    private Long idOpcionEnvio;
    
    @Column(name="Nombre",nullable = false)
    private String nombre;
    
    @Column(name="CostoFijo", nullable = false)
    private double costoFijo;
    
    @Column(name="Porcentaje", nullable = true)
    private double porcentaje;
    
    @Column(name="TiempoMinimo", nullable = false)
    private int tiempoMinimo;
    
    @Column(name="TiempoMaximo", nullable = false)
    private int tiempoMaximo;    

    public OpcionEnvio()
    {
        
    }

    public OpcionEnvio(Long idOpcionEnvio, String nombre, double costoFijo, double porcentaje, int tiempoMinimo, int tiempoMaximo) {
        this.idOpcionEnvio = idOpcionEnvio;
        this.nombre = nombre;
        this.costoFijo = costoFijo;
        this.porcentaje = porcentaje;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }

    public OpcionEnvio(String nombre, double costoFijo, double porcentaje, int tiempoMinimo, int tiempoMaximo) {
        this.nombre = nombre;
        this.costoFijo = costoFijo;
        this.porcentaje = porcentaje;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }
    
     
    public Long getIdOpcionEnvio() {
        return idOpcionEnvio;
    }

    public double getCostoFijo() {
        return costoFijo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setIdOpcionEnvio(Long idOpcionEnvio) {
        this.idOpcionEnvio = idOpcionEnvio;
    }

    public void setCostoFijo(double costoFijo) {
        this.costoFijo = costoFijo;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "OpcionEnvio{" + "idOpcionEnvio=" + idOpcionEnvio + ", nombre=" + nombre + ", costoFijo=" + costoFijo + ", porcentaje=" + porcentaje + ", tiempoMinimo=" + tiempoMinimo + ", tiempoMaximo=" + tiempoMaximo + '}';
    }
    
}
