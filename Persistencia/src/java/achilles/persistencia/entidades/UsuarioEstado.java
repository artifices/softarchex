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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name="UsuarioEstado")
public class UsuarioEstado implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdUsuarioEstado", nullable = false, unique = true)
    private Long idUsuarioEstado;
    
    @OneToOne
    private Usuario usuario;
    
    @Column(name="NombreEstado", nullable = false, unique = true)    
    private String nombreEstado;    

    public UsuarioEstado(Usuario usuario, String nombreEstado) {
        this.usuario = usuario;
        this.nombreEstado = nombreEstado;
    }

    public UsuarioEstado(Long idUsuarioEstado, Usuario usuario, String nombreEstado) {
        this.idUsuarioEstado = idUsuarioEstado;
        this.usuario = usuario;
        this.nombreEstado = nombreEstado;
    }

    public UsuarioEstado() {
    }

    public Long getIdUsuarioEstado() {
        return idUsuarioEstado;
    }

    public void setIdUsuarioEstado(Long idUsuarioEstado) {
        this.idUsuarioEstado = idUsuarioEstado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
}
