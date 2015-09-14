package achilles.persistencia.entidades;

import achilles.persistencia.entidades.Actualizacion;
import achilles.persistencia.entidades.Articulo;
import achilles.persistencia.entidades.EstadoEnvio;
import achilles.persistencia.entidades.OpcionEnvio;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, OpcionEnvio> opcionEnvio;
    public static volatile SingularAttribute<Compra, EstadoEnvio> estadoEnvio;
    public static volatile ListAttribute<Compra, Actualizacion> actualizaciones;
    public static volatile SingularAttribute<Compra, Articulo> articulo;
    public static volatile SingularAttribute<Compra, Long> idComprador;
    public static volatile SingularAttribute<Compra, Calendar> fechaHora;
    public static volatile SingularAttribute<Compra, Long> idCompra;

}