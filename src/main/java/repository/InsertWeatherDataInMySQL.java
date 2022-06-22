package repository;

import entity.LocationEntity;
import entity.ParametersEntity;
import entity.WindEntity;
import model.WeatherResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.WeatherService;

import javax.persistence.EntityManager;

public class InsertWeatherDataInMySQL {
    public void insertByLongitudeAndLatitude(Double latitude, Double longitude) {
        SessionFactory sessionFactory = new Configuration()
                .configure("weatherlady.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManager entityManager = sessionFactory.createEntityManager();

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setLatitude(latitude);
        locationEntity.setLongitude(longitude);
//        locationEntity.setCityName("Tirana");

        WeatherResponse entity = new WeatherService().getLiveWeatherValues(Double.toString(locationEntity.getLatitude()), Double.toString(locationEntity.getLongitude()));
//        System.out.println(entity.toString());

        locationEntity.setCityName(entity.getName());
        locationEntity.setCountryName(entity.getSys().getCountry());
        locationEntity.setLatitude(entity.getCoord().getLat());
        locationEntity.setLongitude(entity.getCoord().getLon());
        locationEntity.setRegion(entity.getName());
        session.save(locationEntity);


        ParametersEntity parametersEntity = new ParametersEntity();
        parametersEntity.setTemperature(entity.getMain().getTemp());
        parametersEntity.setPressure(entity.getMain().getPressure());
        parametersEntity.setHumidity(entity.getMain().getHumidity());
        parametersEntity.setLocationEntity(locationEntity);


        WindEntity windEntity = new WindEntity();
        windEntity.setWindDirection(entity.getWind().getDeg());
        windEntity.setWindSpeed(entity.getWind().getSpeed());
        parametersEntity.setWindEntity(windEntity);

        session.save(parametersEntity);
        session.save(windEntity);

        session.close();

    }
}
