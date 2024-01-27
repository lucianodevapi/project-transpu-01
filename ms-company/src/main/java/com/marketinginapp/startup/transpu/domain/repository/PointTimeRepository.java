package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.PointTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointTimeRepository extends JpaRepository<PointTime, Long> {

    //

    // find all by route and all day
    //List<PointTime> findAllByRouteIdOrderByTimeAsc(Long route);

    // find all by route and one day
    List<PointTime> findAllByRouteIdAndDayIdOrderByTimeAsc(Long route, Long day);

    // find all by route and all day and point
    List<PointTime> findAllByRouteIdAndDepartureTimeIdAndPointIdOrderByTimeAsc(Long route, Long departureTime, Long point);

    // find all by route and all day and point
    List<PointTime> findAllByRouteIdAndDayIdAndPointIdOrderByTimeAsc(Long route, Long day, Long point);
}
