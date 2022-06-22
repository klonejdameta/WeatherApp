package repository;

import entity.ParametersEntity;
import lombok.ToString;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ToString


public class LocationRepository {
    private final EntityManager entityManager;

    public LocationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ParametersEntity> findByCityName(String cityName) {
        ArrayList result = (ArrayList) entityManager.createQuery("SELECT distinct o FROM ParametersEntity o " +
                        "WHERE locationEntity.cityName = :cityName group by locationEntity.cityName", ParametersEntity.class)
                .setParameter("cityName", cityName).getResultList();

        if (result.isEmpty()) {
            System.out.println("Location " + cityName + " does not exists");
        }
        return result;
    }


    public List<ParametersEntity> findLatitudeByCityName(String cityName) {
        entityManager.getTransaction().begin();
        ArrayList result = (ArrayList) entityManager.createQuery("SELECT distinct locationEntity.latitude FROM ParametersEntity o " +
                        "WHERE locationEntity.cityName = :cityName")
                .setParameter("cityName", cityName).getResultList();
        entityManager.getTransaction().commit();
        return result;
    }

    public List<ParametersEntity> findLongitudeByCityName(String cityName) {
        entityManager.getTransaction().begin();
        ArrayList result = (ArrayList) entityManager.createQuery("SELECT distinct locationEntity.longitude FROM ParametersEntity o " +
                        "WHERE locationEntity.cityName = :cityName")
                .setParameter("cityName", cityName).getResultList();
        entityManager.getTransaction().commit();
        return result;
    }

    public int editLocation(String cityName, Double longitude, Double latitude) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("update LocationEntity l set l.longitude = :longitude, l.latitude = :latitude where l.cityName = :cityName")
                .setParameter("cityName", cityName)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .executeUpdate();

        entityManager.getTransaction().commit();
        System.out.println("Update completed");
        return 1;
    }

    public ArrayList deleteCityRecords(String cityName) {
        entityManager.getTransaction().begin();
        ArrayList result = (ArrayList) entityManager.createQuery("DELETE FROM ParametersEntity o WHERE locationEntity in (select id from LocationEntity where cityName = :cityName)")
                .setParameter("cityName", cityName).getSingleResult();
        ArrayList result2 = (ArrayList) entityManager.createQuery("DELETE FROM LocationEntity o WHERE cityName = :cityName")
                .setParameter("cityName", cityName).getSingleResult();
        entityManager.getTransaction().commit();
        return result;
    }

}

