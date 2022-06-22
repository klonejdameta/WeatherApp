package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wind")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@ToString

public class WindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_direction")
    private Integer windDirection;

    @ToString.Exclude
    @OneToOne(mappedBy = "windEntity")
    private ParametersEntity parametersEntity;

}
