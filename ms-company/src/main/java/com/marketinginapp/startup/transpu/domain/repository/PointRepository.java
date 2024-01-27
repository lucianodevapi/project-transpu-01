package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Long> {

    List<Point> findAllByRouteIdOrderByIdAsc(Long route);

    @Query("select p from Point p where p.route.id = ?1 and p.path.district = ?2 order by p.id asc")
    List<Point> findAllByRouteIdAndDistrictOrderByIdAsc(Long route, String district);

    @Query("select p from Point p where p.route.id = ?1 and p.path.address = ?2 order by p.id asc")
    List<Point> findAllByRouteIdAndAddressOrderByIdAsc(Long route, String address);
}
