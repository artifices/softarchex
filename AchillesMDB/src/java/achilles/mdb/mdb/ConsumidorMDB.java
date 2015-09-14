/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.mdb.mdb;

import achilles.logica.operaciones.GestionCompra;
import java.io.IOException;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

/**
 *
 * @author santi
 */
@MessageDriven(mappedName = "jms/Queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumidorMDB implements MessageListener {

    @EJB
    GestionCompra gestorCompra;

    public ConsumidorMDB() {

    }

    @Override
    public void onMessage(Message message) {
        try {

            if (message != null) {

                TextMessage txt = (TextMessage) message;

                String contenido = txt.getText();

                System.out.println("LLEGO EN QUEUE POR MDB1!!!");

                System.out.println("CONTENIDO = " + contenido);
                String idCarrier = "";
                String idCompra = "";
            
                try {
                    JsonFactory jfactory = new JsonFactory();

                    JsonParser jParser = jfactory.createJsonParser(contenido);

                    while (jParser.nextToken() != JsonToken.END_OBJECT) {
                        String actual = jParser.getCurrentName();

                        if (("idCarrier").equals(actual)) {
                            jParser.nextToken();
                            idCarrier = jParser.getText();
                        }

                        if (("idCompra").equals(actual)) {
                            jParser.nextToken();
                            idCompra = jParser.getText();
                        }
                    }
                    jParser.close();

                } catch (JsonGenerationException e) {                    
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String response = null;
                try {
                    System.out.println(idCarrier);
                    System.out.println(idCompra);
                    response = gestorCompra.siguienteEstado(Long.parseLong(idCarrier), Long.parseLong(idCompra));                    
                    System.out.println(response);
                } catch (Exception e) {
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
