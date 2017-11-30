package br.com.azure.test.bus.queue;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by vinicius.yamauchi on 9/11/2017.
 */

public class AzureBusQueueJMS {

    private static final String namespace = "viveredev";
    private static final String sasKey = "AMmPFDvVsqahLnOuv3MteUwpeI1fIvOxJT4T/P7UjeU=";
    private static final String sasKeyName = "VivereSharedAccessKey";
    private static final String queueName = "testqueue";



    public static void main(String[] args) throws NamingException, JMSException {

        // set up JMS
        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("connectionfactory.SBCF", "amqps://VivereSenderReceiverAccessKey:iJS%2Fk4SZh1w6R3urt9pgJjFKspmRIixXGtI5kvR2ReM%3D@viveredev.servicebus.windows.net");
        hashtable.put("queue.QUEUE", "testqueue");
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.amqp_1_0.jms.jndi.PropertiesFileInitialContextFactory");
        Context context = new InitialContext(hashtable);

        // Look up ConnectionFactory and Queue
        ConnectionFactory cf = (ConnectionFactory) context.lookup("SBCF");
        Destination queue = (Destination) context.lookup("QUEUE");

        // Create Connection
        Connection connection = cf.createConnection(sasKeyName, sasKey);
        // Create Session, no transaction, client ack
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        // Create producer
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 10; i++) {
            BytesMessage message = session.createBytesMessage();
            message.writeBytes("test message".getBytes());
            producer.send(message);
        }

        producer.close();
        session.close();
        connection.stop();
        connection.close();

    }

}
