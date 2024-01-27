package com.marketinginapp.startup.transpu.domain.entity;

import com.marketinginapp.startup.transpu.utils.enums.ECompanyStatus;
import com.marketinginapp.startup.transpu.utils.enums.ETypeService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(length = 50)
    private String code;

    @Column(length = 250)
    private String profile;

    @Column(length = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_service")
    private ETypeService typeService;

    @Enumerated(EnumType.STRING)
    private ECompanyStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    @OneToMany(mappedBy = "company")
    private List<Route> routes;

    @OneToMany(mappedBy = "company")
    private List<PointTime> pointTimes;

    @OneToMany(mappedBy = "company")
    private List<Day> days;

    @OneToMany(mappedBy = "company")
    private List<DepartureTime> departureTimes;

    @OneToMany(mappedBy = "company")
    private List<Path> paths;

    @OneToMany(mappedBy = "company")
    private List<Point> points;

    @OneToMany(mappedBy = "company")
    private List<RouteMap> routeMaps;
}
