/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.persistencia.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name = "Articulo")
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdArticulo", nullable = false, unique = true)
    private Long idArticulo;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Descripcion", nullable = true)
    private String descripcion;
    @Column(name = "Precio", nullable = false)
    private double precio;

    @Column(name = "Activo", nullable = false)
    private boolean activo;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @OneToMany
    private List<OpcionEnvio> lstOpcionesEnvio;

    @OneToOne
    private Categoria categoria;

    public Articulo(Long idArticulo, Categoria categoria, String nombre, String descripcion, double precio, boolean activo, int stock) {
        this.idArticulo = idArticulo;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.stock = stock;
        this.lstOpcionesEnvio = new ArrayList<>();
    }

    public Articulo(Categoria categoria, String nombre, String descripcion, double precio, boolean activo, int stock) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.stock = stock;
        this.lstOpcionesEnvio = new ArrayList<>();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<OpcionEnvio> getLstOpcionesEnvio() {
        return lstOpcionesEnvio;
    }

    public void setLstOpcionesEnvio(List<OpcionEnvio> lstOpcionesEnvio) {
        this.lstOpcionesEnvio = lstOpcionesEnvio;
    }

    public Articulo() {
        lstOpcionesEnvio = new ArrayList<>();
    }

    public void agregarOpcionEnvio(OpcionEnvio oe) {
        this.lstOpcionesEnvio.add(oe);
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getIdArticulo() {
        return idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", activo=" + activo + ", stock=" + stock + '}';
    }

}
