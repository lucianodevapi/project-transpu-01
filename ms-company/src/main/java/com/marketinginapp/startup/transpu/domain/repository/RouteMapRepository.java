package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.RouteMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteMapRepository extends JpaRepository<RouteMap, Long> {

    List<RouteMap> findAllByRouteId(Long route);

}
