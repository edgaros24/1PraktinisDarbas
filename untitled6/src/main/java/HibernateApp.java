import jakarta.xml.bind.JAXBException;
import lt.viko.eif.ejurkoit.vet.model.Animal;
import lt.viko.eif.ejurkoit.vet.model.Owner;
import lt.viko.eif.ejurkoit.vet.model.Specialist;
import lt.viko.eif.ejurkoit.vet.util.HibernateUtil;
import lt.viko.eif.ejurkoit.vet.util.JAXBUtil;
import lt.viko.eif.ejurkoit.vet.util.MessageReceiver;
import lt.viko.eif.ejurkoit.vet.util.MessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.jms.JmsException;

import javax.jms.*;
import java.io.IOException;
import java.util.List;

/**
 * Represents main method of all classes
 */
public class HibernateApp {

    public static void main(String[] args) throws JMSException {
        //Creating an object
        Specialist specialist1 = new Specialist("Vardenis",
                "Pavardenis", "Chirurgas");
        Animal animal1 = new Animal("Tom", "Male", "Cat");
        Animal animal2 = new Animal("Mary", "Female", "Cat");
        Owner owner1 = new Owner("Ona", "Onute", specialist1, List.of(animal1, animal2));
        //Hibernate the objects to database
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(owner1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Owner> owners = session.createQuery("from Owner ", Owner.class).list();
            owners.forEach(owner -> System.out.println(owner));
            //Convert to xml (marshalling)
            owners.forEach(owner -> {
                try {
                    JAXBUtil.convertToXml(owner);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            });
            //Sending message
            MessageSender sender = null;
            try {
                sender = new MessageSender();
                sender.SendMessage("generated.xml");
            } catch (JmsException e) {
                System.out.println(e.getMessage());
            } finally {
                sender.close();
            }
            System.out.println("--------------------");
            //Receive a message
            MessageReceiver receiver;
            try {
                receiver = new MessageReceiver();
                receiver.ReceiveMessage();
                System.out.println("--------------------");

            } catch (JMSException e) {
                System.out.println(e.getMessage());
            }
            //server = Server.createTcpServer().start();
            //Return to POJO (unmarshalling)
            owners.forEach(owner -> {
                try {
                    JAXBUtil.ReturnToPOJO();
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.in.read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //server.shutdown();
        }


    }
}
