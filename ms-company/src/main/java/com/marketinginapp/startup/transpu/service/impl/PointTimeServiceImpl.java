package com.marketinginapp.startup.transpu.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marketinginapp.startup.transpu.api.payload.request.PointRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PointResponse;
import com.marketinginapp.startup.transpu.api.payload.response.PointTimeResponse;
import com.marketinginapp.startup.transpu.domain.entity.DepartureTime;
import com.marketinginapp.startup.transpu.domain.entity.Point;
import com.marketinginapp.startup.transpu.domain.entity.PointTime;
import com.marketinginapp.startup.transpu.domain.repository.*;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.PointService;
import com.marketinginapp.startup.transpu.service.PointTimeService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor

@Service
public class PointTimeServiceImpl implements PointTimeService {

    private final CompanyRepository companyRepository;
    private final RouteRepository routeRepository;
    private final DepartureTimeRepository departureTimeRepository;
    private final DayRepository dayRepository;
    private final PointRepository pointRepository;
    private final PointTimeRepository pointTimeRepository;

    @Override
    public PointTimeResponse save(PointTimeRequest request) {
        PointTime entity = new PointTime();
        try{
            entity = pointTimeRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "point time", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public PointTimeResponse update(PointTimeUpdateRequest request) {
        PointTime pointTime = pointTimeRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        PointTime pointTimeSaved = new PointTime();
        try {
            PointTime entity = pointTime;
            if(!entity.getTime().equals(departure(request.time()))){
                entity.setTime(departure(request.time()));
            }
            pointTimeSaved = pointTimeRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "point time" ));
        }
        return toResponse(pointTimeSaved);
    }

    @Override
    public PointTimeResponse findById(Long id) {
        PointTime entity = pointTimeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public PointTimeResponse updateStatus(Long id) {
        PointTime pointTime = pointTimeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        PointTime pointTimeSaved = new PointTime();
        try{
            PointTime entity = pointTime;
            entity.setStatus(!entity.isStatus());
            pointTimeSaved = pointTimeRepository.save(entity);
            return toResponse(pointTimeSaved);
        } catch (Exception exception){
            throw new RuntimeException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }

    }

    private PointTime toEntity(PointTimeRequest request){
        DepartureTime departureTime = departureTimeRepository.findById(request.departureTime())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        Point point = pointRepository.findById(request.point())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        return PointTime.builder()
                .time(departure(request.time()))
                .status(true)
                .company(departureTime.getCompany())
                .route(departureTime.getDay().getRoute())
                .day(departureTime.getDay())
                .departureTime(departureTime)
                .point(point)
                .build();
    }

    private PointTimeResponse toResponse(PointTime entity){
        return new PointTimeResponse(
                entity.getId(),
                ConverterDate.toString(entity.getTime()),
                entity.isStatus(),
                entity.getCompany().getId(),
                entity.getRoute().getId(),
                entity.getDay().getId(),
                entity.getDepartureTime().getId(),
                entity.getPoint().getId());
    }

    private LocalDateTime departure(String time){
        return LocalDateTime.parse(Constant.DAY_PATTERN + time);
    }



}
