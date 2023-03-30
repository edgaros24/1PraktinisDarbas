package lt.viko.eif.ejurkoit.vet.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.ejurkoit.vet.model.Owner;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class represents methods of converting object to XML file
 * and converting back to POJO using JAXB marshaller and unmarshaller
 */
public final class JAXBUtil {
    //methods
    public static void convertToXml(Owner owner) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Owner.class);
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "generated.xsd");
            marshaller.marshal(owner, new File("generated.xml"));
            //OutputStream os = new FileOutputStream( "generated.xml");
            marshaller.marshal(owner, System.out);
            //marshaller.marshal(os, owner);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void ReturnToPOJO() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Owner.class);
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path path = Path.of("generated.xml");
            String xmlContent = Files.readString(path);
            System.out.print(xmlContent);
            StringReader reader = new StringReader(xmlContent);
            Owner owner = (Owner) unmarshaller.unmarshal(reader);
            System.out.println(owner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
