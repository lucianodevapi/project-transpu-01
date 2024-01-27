package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeCopyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DepartureTimeResponse;
import com.marketinginapp.startup.transpu.domain.entity.Day;
import com.marketinginapp.startup.transpu.domain.entity.DepartureTime;
import com.marketinginapp.startup.transpu.domain.repository.DayRepository;
import com.marketinginapp.startup.transpu.domain.repository.DepartureTimeRepository;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.DepartureTimeService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Service
public class DepartureTimeImpl implements DepartureTimeService {

    private final DayRepository dayRepository;
    private final DepartureTimeRepository departureTimeRepository;

    @Override
    public DepartureTimeResponse save(DepartureTimeRequest request) {
        // TODO - find by company, route, day and departure_time
        DepartureTime entity = new DepartureTime();
        try{
            entity = departureTimeRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "departure time", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public DepartureTimeResponse update(DepartureTimeRequest request, Long id) {
        DepartureTime optionalEntity = departureTimeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        DepartureTime entitySaved = new DepartureTime();
        try {
            DepartureTime entity = optionalEntity;
            if(!request.time().isEmpty()){
                entity.setTime(departure(request.time()));
            }
            entitySaved = departureTimeRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "departure time"));
        }
        return toResponse(entitySaved);
    }

    @Override
    public DepartureTimeResponse findById(Long id) {
        DepartureTime entity = departureTimeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public boolean copy(DepartureTimeCopyRequest request) {
        return false;
    }

    @Override
    public DepartureTimeResponse updateStatus(Long id) {
        DepartureTime optionalEntity = departureTimeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        DepartureTime entitySaved = new DepartureTime();
        try{
            DepartureTime entity = optionalEntity;
            entity.setStatus(!entity.isStatus());
            entitySaved = departureTimeRepository.save(entity);
            return toResponse(entitySaved);
        } catch (Exception exception){
            throw new RuntimeException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<DepartureTimeResponse> findAllByDay(Long day) {
        Day dayEntity = dayRepository.findById(day)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, day))
                );
        List<DepartureTimeResponse> responses = new ArrayList<DepartureTimeResponse>();
        try{
            departureTimeRepository.getAllByDayOrderByIdAsc(day)
                    .forEach(departure -> { responses.add(toResponse(departure)); });
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private DepartureTime toEntity(DepartureTimeRequest request){
        Day day = dayRepository.findById(request.day())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        return DepartureTime.builder()
                .time(departure(request.time()))
                .status(true)
                .company(day.getCompany())
                .day(day)
                .build();
    }
    private DepartureTimeResponse toResponse(DepartureTime entity){
        return  new  DepartureTimeResponse(
                entity.getId(),
                ConverterDate.toString(entity.getTime()),
                entity.isStatus(),
                entity.getDay().getId());
    }

    private LocalDateTime departure(String time){
        return LocalDateTime.parse(Constant.DAY_PATTERN + time);
    }
}
