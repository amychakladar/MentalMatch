
/** This class represents therapists with their specialties.
 * Written by @amychakladar*/

public class Therapist {
    private String name;
    private String specialty;
    private String gender;
    private int age;

    public Therapist(String name, String specialty, String gender, int age) {
        this.name = name;
        this.specialty = specialty;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}