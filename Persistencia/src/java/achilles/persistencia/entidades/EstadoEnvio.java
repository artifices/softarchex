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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name = "EstadoEnvio")
public class EstadoEnvio implements Serializable {

    @Id
    @Column(name = "Nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany
    private List<UsuarioEstado> usuariosEstadosPosibles;

    public EstadoEnvio() {
        usuariosEstadosPosibles = new ArrayList<>();
    }

    public EstadoEnvio(String nombre) {
        this.nombre = nombre;
        usuariosEstadosPosibles = new ArrayList<>();
    }

    public void agregarUsuarioEstadoSiguiente(UsuarioEstado ue) {
        usuariosEstadosPosibles.add(ue);
    }
    
    public List<UsuarioEstado> getUsuariosEstadosPosibles() {
        return usuariosEstadosPosibles;
    }

    public void setUsuariosEstadosPosibles(List<UsuarioEstado> usuariosEstadosPosibles) {
        this.usuariosEstadosPosibles = usuariosEstadosPosibles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadoEnvio{" + "nombre=" + nombre;
    }

}
