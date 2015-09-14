package achilles.persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(Moneda.class)
public class Moneda_ { 

    public static volatile SingularAttribute<Moneda, String> nombre;
    public static volatile SingularAttribute<Moneda, Long> idMoneda;
    public static volatile SingularAttribute<Moneda, Double> factor;
    public static volatile SingularAttribute<Moneda, Date> fechaActualizacion;

}