package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "region")
    private String region;

    @Column(name = "country_name")
    private String countryName;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationEntity")
    private List<ParametersEntity> parametersEntity = new ArrayList<>();

}

