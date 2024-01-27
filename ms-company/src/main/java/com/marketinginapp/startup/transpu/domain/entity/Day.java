package com.marketinginapp.startup.transpu.domain.entity;

import com.marketinginapp.startup.transpu.utils.enums.EDay;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "day")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private EDay name;

    @Column(length = 70)
    private String description;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "day")
    private List<PointTime> pointTimes;

    @OneToMany(mappedBy = "day")
    private List<DepartureTime> departureTimes;
}
