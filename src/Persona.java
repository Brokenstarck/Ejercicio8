
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "persona")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persona {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String country;

    // Constructor vacío necesario para JAXB
    public Persona() {
    }

    public Persona(String firstName, String lastName,
                   String country, String gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.gender = gender;
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + ","
                + country + "," + gender + "," + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Persona)) return false;

        Persona persona = (Persona) obj;

        return Objects.equals(firstName, persona.firstName)
                && Objects.equals(lastName, persona.lastName)
                && Objects.equals(email, persona.email)
                && Objects.equals(gender, persona.gender)
                && Objects.equals(country, persona.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName, lastName, email, gender, country
        );
    }

    public List<Persona> getPersonas() {
        return java.util.Arrays.asList(this);
    }

}
