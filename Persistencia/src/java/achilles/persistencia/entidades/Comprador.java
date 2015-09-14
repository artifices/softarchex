/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achilles.persistencia.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nano
 */
@Entity
@Table(name = "Comprador")
public class Comprador extends Usuario{
        
    public Comprador()
    {
        compras = new ArrayList<>();
    }
    
    @OneToMany
    private List<Compra> compras;
    
    public void agregarCompraALista(Compra c){
        compras.add(c);
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
