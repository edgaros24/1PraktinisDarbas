package lt.viko.eif.ejurkoit.vet.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;

/**
 * Represents a method of sending a message to actvemq queue and converting XML file to string
 */
public class MessageSender {
    private static final String QUEUE_NAME = "MY_QUEUE3";
    private Connection connection;
    private MessageProducer producer;
    private Session session;

    //Connects to queue
    public MessageSender() throws JMSException {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        this.connection = connectionFactory.createConnection();
        this.connection.start();

        this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = this.session.createQueue(QUEUE_NAME);

        this.producer = this.session.createProducer(destination);
    }

    //Sends a message
    public void SendMessage(String filename) throws JMSException, IOException {
        String XMLMessage = convertXMLtoString(filename);
        TextMessage message = this.session.createTextMessage(XMLMessage);
        producer.send(message);
        System.out.println("-------------------");
        System.out.println("Sending message " + message.getText() + " to the " + QUEUE_NAME);
        System.out.println("-------------------");

    }

    public void close() {
    }

    //Converts XML file to string
    private String convertXMLtoString(String filename) throws IOException {
        File file = new File(filename);
        Reader fileReader = new FileReader(file);
        BufferedReader bufreader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufreader.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = bufreader.readLine();
        }
        String xmlToString = sb.toString();
        bufreader.close();
        return xmlToString;
    }
}
