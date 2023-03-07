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
    private int age;

    @Test
    public static void main(String[] args) {
        readClient();
    }

    public Client(String name, String specialty, String gender, int age) {
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

    public static List<Client> readClient() {
        List<Client> clients = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream("data/therapist.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                String name = nextLine[0];
                String specialty = nextLine[1];
                String gender = nextLine[2];
                int age = Integer.parseInt(nextLine[3]);
                Client client = new Client(name, specialty, gender, age);
                clients.add(client);
            }

            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return clients;
    }
}