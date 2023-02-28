import org.junit.Test;

import java.util.*;


/* writen with love by @amychakladar, tbd*/
public class TherapistMatcher {

    @Test
    public static void main(String[] args) {
        List<Client> people = new ArrayList<>();
        people.add(new Client("Alice", "anxiety", "female", 25));
        people.add(new Client("Bob", "depression", "male", 30));
        people.add(new Client("Charlie", "anxiety", "male", 35));

        List<Therapist> therapists = new ArrayList<>();
        therapists.add(new Therapist("Dr. Smith", "anxiety", "female", 40));
        therapists.add(new Therapist("Dr. Johnson", "depression", "male", 50));
        therapists.add(new Therapist("Dr. Lee", "anxiety", "male", 45));

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

}
