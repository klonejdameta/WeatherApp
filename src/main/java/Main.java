import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.InsertWeatherDataInMySQL;
import repository.LocationRepository;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        SessionFactory sessionFactory = new Configuration()
                .configure("weatherlady.xml").buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        Session session = sessionFactory.openSession();


        System.out.println("\n\nPlease insert the latitude");
        Double latitudeForInsert = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please insert the longitude");
        Double longitudeForInsert = scanner.nextDouble();
        scanner.nextLine();

        InsertWeatherDataInMySQL insertWeatherDataInMySQL = new InsertWeatherDataInMySQL();
        insertWeatherDataInMySQL.insertByLongitudeAndLatitude(latitudeForInsert, longitudeForInsert); // ben insert ne databaze duke perdorur koordinatat longitude dhe latitude

        LocationRepository locationRepository = new LocationRepository(entityManager);

        System.out.println(locationRepository.findByCityName("Tirana")); // kerkon ne databaze per qytetin dhe kthen te dhenat perkatese
        System.out.println("Longitude is: " + locationRepository.findLatitudeByCityName("Tirana")); // kerkon ne databaze nepermjet emrit te qytetit dhe kthen Latitude
        System.out.println("Latitude is: " + locationRepository.findLongitudeByCityName("Tirana")); // kerkon ne databaze nepermjet emrit te qytetit dhe kthen Longitude

       /* System.out.println("\n\nPlease insert the name of the city that you want to update");
        String cityName = scanner.nextLine();
        System.out.println("Please insert the new longitude");
        Double longitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please insert the new latitude");
        Double latitude = scanner.nextDouble();

        locationRepository.editLocation(cityName, longitude, latitude); // ben update longitude the latitude duke mare si input qytetin

        System.out.println("\n\n\n");*/

        // locationRepository.deleteCityRecords("Tirana"); // delete behet me subquery, nuk kam arritur ta perfundoj

        session.close();

    }
}
