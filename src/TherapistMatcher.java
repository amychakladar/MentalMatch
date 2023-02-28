import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* writen with love by @amychakladar */
public class TherapistMatcher {

    @Test
    public static void main(String[] args) {
        // Sample list of people with their preferences
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", "anxiety", "female", 25));
        people.add(new Person("Bob", "depression", "male", 30));
        people.add(new Person("Charlie", "anxiety", "male", 35));

        // Sample list of therapists with their specialties
        List<Therapist> therapists = new ArrayList<>();
        therapists.add(new Therapist("Dr. Smith", "anxiety", "female", 40));
        therapists.add(new Therapist("Dr. Johnson", "depression", "male", 50));
        therapists.add(new Therapist("Dr. Lee", "anxiety", "male", 45));

        // Match people with therapists based on preferences
        Map<Person, Therapist> matches = new HashMap<>();
        for (Person person : people) {
            Therapist bestMatch = null;
            int bestScore = 0;
            for (Therapist therapist : therapists) {
                int score = calculateScore(person, therapist);
                if (bestMatch == null || score > bestScore) {
                    bestMatch = therapist;
                    bestScore = score;
                }
            }
            matches.put(person, bestMatch);
        }

        // Show people who meet those preferences more often
        for (Person person : people) {
            Therapist therapist = matches.get(person);
            System.out.println(person.getName() + " matched with " + therapist.getName());
        }
    }

    // Calculate a score for how well a therapist matches a person's preferences
    private static int calculateScore(Person person, Therapist therapist) {
        int score = 0;
        if (person.getSpecialty().equals(therapist.getSpecialty())) {
            score += 2;
        }
        if (person.getGender().equals(therapist.getGender())) {
            score += 1;
        }
        if (Math.abs(person.getAge() - therapist.getAge()) <= 5) {
            score += 1;
        }
        return score;
    }

    // A class representing a person with their preferences
    private static class Person {
        private String name;
        private String specialty;
        private String gender;
        private int age;

        public Person(String name, String specialty, String gender, int age) {
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

    // A class representing a therapist with their specialties
    private static class Therapist {
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
}
