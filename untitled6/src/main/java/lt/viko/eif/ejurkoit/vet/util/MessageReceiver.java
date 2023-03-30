package lt.viko.eif.ejurkoit.vet.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Represents a method of a message receiver from activemq queue
 */
public class MessageReceiver {
    private static final String QUEUE_NAME = "MY_QUEUE3";
    private Connection connection;
    private MessageConsumer consumer;
    private Session session;

    public void ReceiveMessage() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                (ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        this.connection = connectionFactory.createConnection();
        this.connection.start();

        this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = this.session.createQueue(QUEUE_NAME);
        this.consumer = this.session.createConsumer(destination);
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Received message " + textMessage.getText() +
                    " from " + QUEUE_NAME);
        }
        connection.close();
    }
}
