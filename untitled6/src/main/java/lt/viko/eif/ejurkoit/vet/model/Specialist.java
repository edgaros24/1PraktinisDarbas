package lt.viko.eif.ejurkoit.vet.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;

/**
 * Represents a specialist for the animals of the owner
 * Represented by {@link Owner} class
 *
 * @author Edgar
 * @see Owner
 * @since 1.0
 */
@Entity
@Table(name = "specialist")
@XmlType(propOrder = {"id", "name", "lastname", "specialization"})
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String lastname;
    private String specialization;

    /**
     * @param id             int identifier
     * @param name           string name
     * @param lastname       string lastname
     * @param specialization string specialization of the specialist
     */

    public Specialist(int id, String name, String lastname, String specialization) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.specialization = specialization;
    }

    public Specialist() {

    }

    public Specialist(String name, String lastname, String specialization) {
        this.name = name;
        this.lastname = lastname;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String toString() {
        return String.format("Name: %s\n\t" + "LastName: %s\n\t" + "Specialization: %s\n",
                this.name, this.lastname, this.specialization);
    }
}
