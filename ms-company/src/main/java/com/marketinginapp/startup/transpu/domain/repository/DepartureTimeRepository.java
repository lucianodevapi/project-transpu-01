package com.marketinginapp.startup.transpu.domain.repository;

import com.marketinginapp.startup.transpu.domain.entity.DepartureTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartureTimeRepository extends JpaRepository<DepartureTime, Long> {

    @Query("select dt from DepartureTime dt where dt.day.id = ?1 and dt.status = true")
    List<DepartureTime> getAllByDayOrderByIdAsc(Long day);
}
