package com.marketinginapp.startup.transpu.domain.entity;

import com.marketinginapp.startup.transpu.utils.enums.ECompanyStatus;
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
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "10")
    private String name;

    @Column(name = "50")
    private String description;

    @Enumerated(EnumType.STRING)
    private ECompanyStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "route")
    private List<Path> paths;

    @OneToMany(mappedBy = "route")
    private List<Point> points;

    @OneToMany(mappedBy = "route")
    private List<RouteMap> routeMaps;

    @OneToMany(mappedBy = "route")
    private List<Day> days;

    @OneToMany(mappedBy = "route")
    private List<PointTime> pointTimes;
}
