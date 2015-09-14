package achilles.persistencia.entidades;

import achilles.persistencia.entidades.Categoria;
import achilles.persistencia.entidades.OpcionEnvio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(Articulo.class)
public class Articulo_ { 

    public static volatile SingularAttribute<Articulo, String> nombre;
    public static volatile SingularAttribute<Articulo, Categoria> categoria;
    public static volatile SingularAttribute<Articulo, Long> idArticulo;
    public static volatile SingularAttribute<Articulo, Integer> stock;
    public static volatile SingularAttribute<Articulo, Double> precio;
    public static volatile SingularAttribute<Articulo, String> descripcion;
    public static volatile ListAttribute<Articulo, OpcionEnvio> lstOpcionesEnvio;
    public static volatile SingularAttribute<Articulo, Boolean> activo;

}