import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** This class represents clients with their preferenes.
 * Written by @amychakladar*/

public class Client {
    private String name;
    private String specialty;
    private String gender;
    private int gender_importance;
    private int age;
    private int age_importance;

    @Test
    public static void main(String[] args) {
        readClient();
    }

    public Client(String name, String specialty, String gender, int gender_importance, int age, int age_importance) {
        this.name = name;
        this.specialty = specialty;
        this.gender = gender;
        this.gender_importance = gender_importance;
        this.age = age;
        this.age_importance = age_importance;
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

    public int getImportanceGender() {
        return gender_importance;
    }
    public int getImportanceAge() {
        return age_importance;
    }

    public static List<Client> readClient() {
        List<Client> clients = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream("data/client.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                String name = nextLine[0];
                String specialty = nextLine[1];
                String gender = nextLine[2];
                int gender_importance = Integer.parseInt(nextLine[3]);
                int age = Integer.parseInt(nextLine[4]);
                int age_importance = Integer.parseInt(nextLine[5]);
                Client client = new Client(name, specialty, gender, gender_importance, age, age_importance);
                clients.add(client);
            }

            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return clients;
    }
}