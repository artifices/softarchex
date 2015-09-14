/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name="Categoria")
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdCategoria", nullable = false, unique = true)
    private Long idCategoria;
    @Column(name="IdPadre", nullable = true)
    private Long idPadre;
    
    
    @Column(name="Nivel", nullable = true)
    private int nivel;
    @Column(name="Nombre", nullable = false)
    private String nombre;
    @Column(name="Alto", nullable = true)
    private double alto;
    @Column(name="Largo", nullable = true)
    private double largo;
    @Column(name="Ancho", nullable = true)
    private double ancho;
    @Column(name="Notas")
    private String notas;
    
    @OneToMany
    private List<OpcionEnvio> lstOpcionesEnvio;

    public Categoria(){
        lstOpcionesEnvio = new ArrayList<>();
    }

    public Categoria(Long idCategoria, Long idPadre, int nivel, String nombre, double alto, double largo, double ancho, String notas) {
        this.idCategoria = idCategoria;
        this.idPadre = idPadre;
        this.nivel = nivel;
        this.nombre = nombre;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.notas = notas;
    }

    public Categoria(Long idPadre, int nivel, String nombre, double alto, double largo, double ancho, String notas) {
        this.idPadre = idPadre;
        this.nivel = nivel;
        this.nombre = nombre;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.notas = notas;
    }
    
    
    
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
    
    public Long getIdCategoria() {
        return idCategoria;
    }
   
    public int getNivel() {
        return nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAlto() {
        return alto;
    }

    public double getLargo() {
        return largo;
    }

    public double getAncho() {
        return ancho;
    }

    public String getNotas() {
        return notas;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public List<OpcionEnvio> getLstOpcionesEnvio() {
        return lstOpcionesEnvio;
    }

    public void setLstOpcionesEnvio(List<OpcionEnvio> lstOpcionesEnvio) {
        this.lstOpcionesEnvio = lstOpcionesEnvio;
    }

}
