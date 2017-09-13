
import java.util.ArrayList;
public class Person {
    private String personCode;
    private String name;
    private String address;

    public Person(String personCode, String name, String address) {
        this.personCode = personCode;
        this.name = name;
        this.address = address;

    }

    public String getPersonCode() {
        return this.personCode;
    }

    public String getPerson() {
        return this.name + " " + this.address;
    }

}
