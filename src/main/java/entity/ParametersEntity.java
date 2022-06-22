package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString

public class ParametersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "humidity")
    private Integer humidity;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity = new LocationEntity();

    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "wind_id")
    private WindEntity windEntity;

}