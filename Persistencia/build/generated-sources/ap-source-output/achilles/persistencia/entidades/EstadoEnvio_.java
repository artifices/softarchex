package achilles.persistencia.entidades;

import achilles.persistencia.entidades.UsuarioEstado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-26T18:39:38")
@StaticMetamodel(EstadoEnvio.class)
public class EstadoEnvio_ { 

    public static volatile SingularAttribute<EstadoEnvio, String> nombre;
    public static volatile ListAttribute<EstadoEnvio, UsuarioEstado> usuariosEstadosPosibles;

}