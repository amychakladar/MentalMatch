import org.junit.Test;

import java.util.*;


/** writen with love by @amychakladar
 *
 * to do:
 * a. create the ability to import csv files and store data there because aesthetics
 * b. add the ability to call back and see which therapists they said yes on.
 * */
public class TherapistMatcher {

    @Test
    public static void main(String[] args) {
        List<Client> people = new ArrayList<>();
        people.add(new Client("Ayush", "anxiety", "male", 20));
        people.add(new Client("Bob", "depression", "male", 30));
        people.add(new Client("Charlie", "anxiety", "male", 35));

        List<Therapist> therapists = new ArrayList<>();

        therapists.add(new Therapist("Dr. Smith", "anxiety", "female", 40));
        therapists.add(new Therapist("Dr. Johnson", "depression", "male", 50));
        therapists.add(new Therapist("Dr. Lee", "anxiety", "male", 45));
        therapists.add(new Therapist("Dr. Smith", "anxiety", "female", 40));
        therapists.add(new Therapist("Dr. Johnson", "depression", "male", 50));
        therapists.add(new Therapist("Dr. Lee", "anxiety", "male", 25));
        therapists.add(new Therapist("Dr. Brown", "PTSD", "male", 35));
        therapists.add(new Therapist("Dr. Williams", "eating disorders", "female", 28));
        therapists.add(new Therapist("Dr. Davis", "anxiety", "male", 52));
        therapists.add(new Therapist("Dr. Garcia", "depression", "male", 48));
        therapists.add(new Therapist("Dr. Rodriguez", "PTSD", "female", 44));
        therapists.add(new Therapist("Dr. Martinez", "addiction", "male", 39));
        therapists.add(new Therapist("Dr. Hernandez", "eating disorders", "female", 33));
        therapists.add(new Therapist("Dr. King", "depression", "male", 56));
        therapists.add(new Therapist("Dr. Wright", "anxiety", "female", 42));
        therapists.add(new Therapist("Dr. Lopez", "PTSD", "male", 47));
        therapists.add(new Therapist("Dr. Scott", "addiction", "female", 31));
        therapists.add(new Therapist("Dr. Green", "eating disorders", "male", 38));
        therapists.add(new Therapist("Dr. Adams", "anxiety", "male", 49));
        therapists.add(new Therapist("Dr. Baker", "depression", "female", 29));
        therapists.add(new Therapist("Dr. Mitchell", "PTSD", "male", 41));
        therapists.add(new Therapist("Dr. Perez", "addiction", "female", 34));
        therapists.add(new Therapist("Dr. Cooper", "eating disorders", "male", 36));
        therapists.add(new Therapist("Dr. Turner", "anxiety", "female", 43));
        therapists.add(new Therapist("Dr. Parker", "depression", "male", 51));
        therapists.add(new Therapist("Dr. Campbell", "PTSD", "male", 46));
        therapists.add(new Therapist("Dr. Phillips", "addiction", "female", 30));
        therapists.add(new Therapist("Dr. Ross", "eating disorders", "male", 37));
        therapists.add(new Therapist("Dr. Edwards", "anxiety", "male", 54));
        therapists.add(new Therapist("Dr. Collins", "depression", "female", 27));
        therapists.add(new Therapist("Dr. Stewart", "PTSD", "male", 43));
        therapists.add(new Therapist("Dr. Sanchez", "addiction", "female", 32));
        therapists.add(new Therapist("Dr. Morris", "eating disorders", "male", 40));
        therapists.add(new Therapist("Dr. Rogers", "anxiety", "female", 45));
        therapists.add(new Therapist("Dr. Reed", "depression", "male", 53));
        therapists.add(new Therapist("Dr. Cook", "PTSD", "male", 48));

        Map<Client, List<Therapist>> matches = match(people, therapists);

        // Show candidates the list of therapists, and let them choose
        for (Map.Entry<Client, List<Therapist>> entry : matches.entrySet()) {
            Client person = entry.getKey();
            List<Therapist> therapistList = entry.getValue();

            System.out.println(person.getName() + ", here are your therapist options: ");

            while (true) {
                // Sort therapists based on how well they match the candidate's preferences
                Collections.sort(therapistList, new Comparator<Therapist>() {
                    @Override
                    public int compare(Therapist t1, Therapist t2) {
                        int score1 = calculateScore(person, t1);
                        int score2 = calculateScore(person, t2);
                        return Integer.compare(score2, score1);
                    }
                });

                // Offer all therapists to the candidate, in order of how well they match
                for (Therapist therapist : therapistList) {
                    System.out.print(therapist.getName() + " (specialty: " + therapist.getSpecialty() + ", gender: " + therapist.getGender() + ", age: " + therapist.getAge() + ") - ");
                    System.out.println("Do you want to choose this therapist? (y/n)");
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        System.out.println("Great, you've chosen " + therapist.getName() + "!");
                        break;
                    }
                }

                // Ask the candidate if they want to see more therapists
                System.out.println("Do you want to see more therapists? (y/n)");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("n")) {
                    break;
                }
            }

        }

    }


    public static Map<Client, List<Therapist>> match(List<Client> people, List<Therapist> therapists) {
        Map<Client, List<Therapist>> matches = new HashMap<>();
        for (Client person : people) {
            List<Therapist> therapistList = new ArrayList<>(therapists);
            matches.put(person, therapistList);
        }
        return matches;
    }

    private static int calculateScore(Client person, Therapist therapist) {
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

    private static Map<Therapist, List<Client>> getMatches(List<Client> clients, Map<Client, List<Therapist>> chosenTherapists) {
        Map<Therapist, List<Client>> matches = new HashMap<>();

        for (Client client : clients) {
            List<Therapist> therapistList = chosenTherapists.get(client);
            for (Therapist therapist : therapistList) {
                if (!matches.containsKey(therapist)) {
                    matches.put(therapist, new ArrayList<>());
                }
                matches.get(therapist).add(client);
            }
        }

        return matches;
    }


}
