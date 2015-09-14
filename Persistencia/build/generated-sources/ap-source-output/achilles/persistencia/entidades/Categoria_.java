package achilles.persistencia.entidades;

import achilles.persistencia.entidades.OpcionEnvio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Long> idCategoria;
    public static volatile SingularAttribute<Categoria, String> nombre;
    public static volatile SingularAttribute<Categoria, String> notas;
    public static volatile SingularAttribute<Categoria, Double> largo;
    public static volatile SingularAttribute<Categoria, Long> idPadre;
    public static volatile ListAttribute<Categoria, OpcionEnvio> lstOpcionesEnvio;
    public static volatile SingularAttribute<Categoria, Double> ancho;
    public static volatile SingularAttribute<Categoria, Integer> nivel;
    public static volatile SingularAttribute<Categoria, Double> alto;

}