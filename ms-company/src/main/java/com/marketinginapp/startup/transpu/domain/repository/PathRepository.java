package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PathRepository extends JpaRepository<Path, Long> {

    @Query("select p from Path p where p.company.id = ?1 and p.route.id = ?2 and p.city = ?3 and p.district = ?4 and p.address = ?5 order by p.id ")
    List<Path> findAllByAddress(Long company, Long route, String city, String district, String address);

    List<Path> findAllByRouteIdOrderByIdAsc(Long route);
}
