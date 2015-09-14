/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achilles.mdb.sb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;

/**
 *
 * @author santi
 */
@Stateless
@LocalBean
public class ProductorSB {

    @Resource(lookup = "ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/Queue")
    private Queue queue;

    public void sendToQueue(Long idCarrier, Long idCompra) {

        try {

            Connection connection = connectionFactory.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(queue);

            TextMessage message = session.createTextMessage();

            String json = EscribirJson(idCarrier, idCompra);
            message.setText(json);
            producer.send(message);

            session.close();

            connection.close();

        } catch (JMSException ex) {
            Logger.getLogger(ProductorSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String EscribirJson(Long idCarrier, Long idCompra) {
        String json = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            JsonFactory jfactory = new JsonFactory();
            JsonGenerator jGenerator = jfactory.createJsonGenerator(out);

            jGenerator.writeStartObject();
            jGenerator.writeStringField("idCarrier", idCarrier.toString());
            jGenerator.writeStringField("idCompra", idCompra.toString());
            jGenerator.writeEndObject();

            jGenerator.close();

            json = out.toString("UTF-8");
        } catch (JsonGenerationException e) {
            json = "error" + e.getLocalizedMessage().toString();
            e.printStackTrace();
        } catch (JsonMappingException e) {
            json = "error" + e.getLocalizedMessage().toString();
            e.printStackTrace();
        } catch (IOException e) {
            json = "error" + e.getLocalizedMessage().toString();
            e.printStackTrace();
        } catch (Exception e) {
            json = "error" + e.getLocalizedMessage().toString();
            e.printStackTrace();
        }
        return json;
    }
}
