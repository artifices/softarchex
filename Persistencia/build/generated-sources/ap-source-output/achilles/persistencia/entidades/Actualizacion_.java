package achilles.persistencia.entidades;

import achilles.persistencia.entidades.EstadoEnvio;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(Actualizacion.class)
public class Actualizacion_ { 

    public static volatile SingularAttribute<Actualizacion, EstadoEnvio> estadoEnvio;
    public static volatile SingularAttribute<Actualizacion, Long> idUsuario;
    public static volatile SingularAttribute<Actualizacion, String> lugarGeograficoActual;
    public static volatile SingularAttribute<Actualizacion, String> notaActualizacion;
    public static volatile SingularAttribute<Actualizacion, Long> idActualizacion;
    public static volatile SingularAttribute<Actualizacion, Calendar> fechaActualizacion;

}