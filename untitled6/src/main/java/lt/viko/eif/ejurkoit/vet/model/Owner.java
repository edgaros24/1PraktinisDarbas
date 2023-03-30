package lt.viko.eif.ejurkoit.vet.model;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * Represents main object
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String lastname;
    @OneToOne(targetEntity = Specialist.class, cascade = CascadeType.ALL)

    private Specialist specialist;
    @XmlElementWrapper(name = "animals")
    @XmlElement(name = "animal")
    @OneToMany(targetEntity = Animal.class, cascade = CascadeType.ALL)

    private List<Animal> animals;

    /**
     * @param id         int identifier
     * @param name       string name of the owner
     * @param lastname   string last name of the owner
     * @param specialist class of the specialist
     * @param animals    class list of the animals
     */

    public Owner(int id, String name, String lastname, Specialist specialist, List<Animal> animals) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.specialist = specialist;
        this.animals = animals;
    }

    public Owner() {
    }

    public Owner(String name, String lastname, Specialist specialist, List<Animal> animals) {
        this.name = name;
        this.lastname = lastname;
        this.specialist = specialist;
        this.animals = animals;
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

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    private String ConstructAnimalString() {
        String result = "";
        for (Animal animal : this.animals) {
            result += animal.toString();
        }
        return result;
    }

    public String toString() {
        return String.format("Owner:\n\tFirstName = %s\n\t" + "LastName = %s\n\t" +
                        "\tAnimals: \n\t%s\n" + "Specialist: \n\t%s\n\t", this.name,
                this.lastname, ConstructAnimalString(), this.specialist);
    }
}
