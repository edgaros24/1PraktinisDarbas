package lt.viko.eif.ejurkoit.vet.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;

/**
 * Represents animals of the owner
 * Represented by {@link Owner} class
 *
 * @author Edgar
 * @see Owner
 * @since 1.0
 */
@XmlType(propOrder = {"id", "name", "gender", "type"})
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String gender;
    private String type;

    /**
     * @param id     int identifier
     * @param name   string name of the animal
     * @param gender string gender of the animal
     * @param type   string type of animal (dog, cat)
     */

    public Animal(int id, String name, String gender, String type) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.type = type;
    }

    public Animal(String name, String gender, String type) {
        this.name = name;
        this.gender = gender;
        this.type = type;
    }

    public Animal() {

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String toString() {
        return String.format("Name: %s\n\t" + "Gender: %s\n\t" + "Type: %s\n\t", this.name, this.gender, this.type);
    }
}
