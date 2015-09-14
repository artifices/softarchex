/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.io.Serializable;
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
@Table(name="Usuario")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdUsuario", nullable = false, unique = true)
    private Long idUsuario;
    @Column(name="Nombre", nullable = false, unique = true)
    private String nombre;
    @Column(name="Tipo",nullable = false)
    private String tipo;

    public Usuario()
    {
        
    }
    
    public Usuario(Long id, String nombre, String tipo)
    {
        this.idUsuario = id;        
        this.nombre = nombre;
        this.tipo = tipo;

    }
    
    public Usuario(String nombre, String tipo)
    {
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }   
}
