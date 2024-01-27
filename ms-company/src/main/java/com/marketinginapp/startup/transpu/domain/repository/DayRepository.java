package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.Day;
import com.marketinginapp.startup.transpu.utils.enums.EDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {

    Optional<Day> findByNameAndCompanyIdAndRouteId(EDay name, Long company, Long route);

    @Query("select d from Day d where d.name = ?1 and d.route.id = ?2 and d.company.id = ?3")
    Optional<Day> getByNameAndRouteAndCompany(EDay name, Long route, Long company);

    List<Day> findAllByRouteId(Long route);
}
